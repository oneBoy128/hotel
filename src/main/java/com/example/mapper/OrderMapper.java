package com.example.mapper;

import com.example.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    //查询所有订单
    List<Order> selectAll(@Param("userId") int userId);

    //根据id查询订单信息
    Order selectById(@Param("id") int id);

    //添加订单
    int addOrder(@Param("order")Order order);

    //取消订单
    int cancelOrder(@Param("id") int id);

    //删除订单
    int deleteOrder(@Param("id") int id);
}
