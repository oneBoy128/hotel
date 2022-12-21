package com.example.controller;

import com.example.pojo.Center;
import com.example.pojo.User;
import com.example.service.OrderService;
import com.example.service.UserService;
import com.example.utils.Res;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel/users/center")
public class OrderController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    //查询所有订单  需要返回订单信息和 余额
    @GetMapping
    public Res selectAll(){
        int userId = (int)request.getSession().getAttribute("userId");
        int balance = userService.selectBalance(userId);
        Center center = new Center(balance, orderService.selectAll(userId));
        return new Res(true,center);
    }

    //根据orderId查询order信息
    @GetMapping("{id}")
    public Res selectById(@PathVariable int id){
        return new Res(true,orderService.selectById(id));
    }

    //取消订单
    @PutMapping("{id}")
    public Res cancelOrder(@PathVariable int id ){
        boolean flag = false;
        if (orderService.cancelOrder(id)>0){
            flag = true;
        }
        return new Res(flag,flag?"取消成功^_^":"取消失败-_-!");
    }

    //删除订单
    @DeleteMapping("{id}")
    public Res deleteOrder(@PathVariable int id){
        boolean flag = false;
        if (orderService.deleteOrder(id)>0){
            flag = true;
        }
        return new Res(flag,flag?"删除成功^_^":"删除失败-_-!");
    }

    //编辑用户信息
    @PutMapping()
    public Res editInfo(@RequestBody User user){
        //获取当前用户id
        int userId = (int)request.getSession().getAttribute("userId");
        user.setId(userId);
        boolean flag = false;
        if (userService.editInfo(user)>0){
            flag = true;
        }
        return new Res(flag,flag?"修改成功^_^":"修改失败-_-!");
    }
}
