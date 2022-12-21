package com.example.service.impl;

import com.example.mapper.OrderMapper;
import com.example.mapper.RoomMapper;
import com.example.mapper.TimeMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.Order;
import com.example.pojo.Time;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    TimeMapper timeMapper;

    //查询所有订单
    @Override
    public List<Order> selectAll(int userId) {
        return orderMapper.selectAll(userId);
    }

    //根据Id查询
    @Override
    public Order selectById(int id) {
        return orderMapper.selectById(id);
    }

    //添加订单
    @Override
    public int addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    //取消订单
    @Override
    public int cancelOrder(int id) {
        //取消订单后还需要退款(调用一次充值) 还需要同步更新time表，删除对应的数据
        if (orderMapper.cancelOrder(id)>0){  //影响数据行>0说明取消成功
            Order order = orderMapper.selectById(id);
            int userId = order.getUserId();
            Date startTime = order.getStartTime();
            int number = order.getNumber();
            //更新time表数据
            if (timeMapper.deleteTime(userId,startTime,number)>0){ //房间状态清空再退款
                //调用充值 实现退款
                return userMapper.recharge(userId,order.getPrice()) ;
            }
        }
        return 0;
    }

    @Override
    public int deleteOrder(int id) {
        return orderMapper.deleteOrder(id);
    }
}
