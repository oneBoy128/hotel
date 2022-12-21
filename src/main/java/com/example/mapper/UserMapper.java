package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    //注册用户
    int addUser(@Param("user") User user);
    //根据用户名查询
    User selectByUsername(@Param("username") String username);

    //用户登录
    User login(@Param("user") User user);

    //用户充值
    int recharge(@Param("id") int id, @Param("num") int num);

    //查询余额
    int selectBalance(int id);
    //用户支付
    int pay(@Param("id") int id, @Param("num") int num);

    //编辑用户信息
    int editInfo(@Param("user") User user);

}
