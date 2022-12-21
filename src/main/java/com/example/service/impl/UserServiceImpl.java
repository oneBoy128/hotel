package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    //用户注册
    @Override
    public int addUser(User user) {
        String username = user.getUsername();
        //判断username是否重复
        User u = userMapper.selectByUsername(username);
        //查到说明username重复
        if (u != null){
            return 0;
        }
        return userMapper.addUser(user);
    }
    //用户登录
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    //编辑用户信息
    @Override
    public int editInfo(User user) {
        return userMapper.editInfo(user);
    }

    //用户充值
    @Override
    public int recharge(int id, int num) {
        return userMapper.recharge(id,num);
    }

    //查询余额
    @Override
    public int selectBalance(int id) {
        return userMapper.selectBalance(id);
    }

    //用户支付
    @Override
    public int pay(int id,int num){
        int balance = userMapper.selectBalance(id);
        if (balance>=num){
            balance -= num;
            return userMapper.pay(id,balance);
        }
        return 0;
    }
}
