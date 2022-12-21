package com.example.service.impl;

import com.example.mapper.OrderMapper;
import com.example.mapper.RoomMapper;
import com.example.mapper.TimeMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.Order;
import com.example.pojo.Room;
import com.example.pojo.Time;
import com.example.service.OrderService;
import com.example.service.RoomService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    TimeMapper timeMapper;

    //添加房间
    @Override
    public int addRoom(Room room) {
        //判断房间号是否重复
        if(roomMapper.selectByNumber(room.getNumber()) == null){
            return roomMapper.addRoom(room);
        }
        return 0;
    }

    //查询全部房间
    @Override
    public List<Room> selectAll() {
        return roomMapper.selectAll();
    }


    //根据Ids查房间  需要判断是否已过可用时间
    @Override
    public List<Room> selectByIds(Time time) {
        int days = time.getDays();
        //定义应该排除的日期集合
        List<Date> dates = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            dates.add(time.addDate(time.getStartTime(),i));
        }
        if (!dates.isEmpty()){
            List<Integer> roomsId = timeMapper.selectRooms(dates);
            if (!roomsId.isEmpty()){
                //根据roomId查询
                return roomMapper.selectByIllegalIds(roomsId);
            }
        }
        return roomMapper.selectAll();
    }

    //根据number查房间
    @Override
    public Room selectByNumber(int number) {
        return roomMapper.selectByNumber(number);
    }

    //根据id查房间
    @Override
    public Room selectById(int id) {
        return roomMapper.selectById(id);
    }

    //预定房间
    @Override
    public int orderRoom(int id,Time time) {
        Room r = roomMapper.selectById(id);
        int days = time.getDays();
        //执行支付 计算费用
        int expenses = r.getPrice()*days;
        //获取userId
        int userId = (int)request.getSession().getAttribute("userId");
        if ( userService.pay(userId,expenses)==0){
            return -1;  //余额不足
        }
        //添加日期表
        time.setUserId(userId);
        time.setRoomId(id);
        time.setNumber(r.getNumber());
        timeMapper.add(time);
        //同步添加到订单中
        Order order = new Order(r.getNumber(),r.getPrice(),r.getDescription(),userId,time.getStartTime(),time.getEndTime());
        return orderMapper.addOrder(order);
    }
}
