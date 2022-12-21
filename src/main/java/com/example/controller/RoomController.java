package com.example.controller;


import com.example.pojo.Order;
import com.example.pojo.Room;
import com.example.pojo.Time;
import com.example.service.OrderService;
import com.example.service.RoomService;
import com.example.service.UserService;
import com.example.utils.Res;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel/rooms")
public class RoomController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    UserService userService;
    @Autowired
    RoomService roomService;
    @Autowired
    OrderService orderService;

    //添加房间
    @PutMapping
    public Res addRoom(@RequestBody Room room){
        //设置成功标识符
        Boolean flag = false;
        if (roomService.addRoom(room)>0){
            flag = true;
        }
        return new Res(flag,flag?"添加成功^-^":"添加失败,房间号不能重复!");
    }

    //查询所有房间
    @PostMapping
    public Res selectByIds(@RequestBody Time time){
        return new Res(true,roomService.selectByIds(time));
    }

    //根据number查房间
    @GetMapping("{number}")
    public Res selectByNumber(@PathVariable int number){
        return new Res(true,roomService.selectByNumber(number));
    }

    //预定房间
    @PutMapping("{id}")
    public Res orderRoom(@PathVariable int id,@RequestBody Time time){
        //设置成功标识符
        Boolean flag = false;
        //余额不足
        if (roomService.orderRoom(id,time)==-1){
            //支付失败
            return new Res(flag,"余额不足,支付失败!");
        }
        return new Res(true,"预定成功^-^");
    }
}
