package com.example.service;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    //用户注册
    int addUser(User user);

    //用户登录
    User login( User user);

    //编辑用户信息
    int editInfo(User user);

    //用户充值
    int recharge(int id,int num);

    //查询余额
    int selectBalance(int id);
    //用户支付
    int pay(int id, int num);
}
