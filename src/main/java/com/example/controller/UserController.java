package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.Res;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel/users")
public class UserController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    private UserService userService;

    //用户注册
    @PostMapping
    public Res addUser(@RequestBody User user){
        //设置成功标识符
        Boolean flag = false;
        if (userService.addUser(user)>0){
            flag = true;
        }
        return new Res(flag,flag?"注册成功^-^":"注册失败,用户名不能重复喔!");
    }

    //用户登录
    @PostMapping ("/login")
    public Res login(@RequestBody User user){
        Boolean flag = false;
        //若用户名和密码正确则判定登录成功
        User u = userService.login(user);
        if (null !=u && u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())){
            flag = true;
            //将id存入session域中
            HttpSession session = request.getSession();
            session.setAttribute("userId",u.getId());
        }
        return new Res(flag,u,flag?"登录成功^_^":"登录失败-_-!");
    }

    //用户充值
    @PostMapping("{num}")
    public Res recharge(@PathVariable int num){
        Boolean flag = false;
        int userId = (int)request.getSession().getAttribute("userId");
        if (userService.recharge(userId,num)>0){
            flag =true;
        }
        return new Res(flag,flag?"交易成功^_^":"交易失败-_-!");
    }

    //支付
    @PutMapping("{num}")
    public Res pay(@PathVariable int num){
        //设置标识符号
        Boolean flag = false;
        int userId = (int)request.getSession().getAttribute("userId");
        if (userService.pay(userId,num)>0){
            flag = true;
        }
        return new Res(flag,flag?"交易成功^_^":"交易失败，余额不足-_-!");
    }


}
