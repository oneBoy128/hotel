package com.example.service;

import com.example.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    //查询所有订单
    List<Order> selectAll(int userId);
    //根据id查询
    Order selectById(int id);

    //添加订单
    int addOrder(Order order);

    //取消订单
    int cancelOrder(int id);

    //删除订单
    int deleteOrder(int id);
}
