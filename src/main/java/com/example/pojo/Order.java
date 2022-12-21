package com.example.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Order {
    Integer id;
    Integer number;
    String condition;
    String description;
    Integer price;
    Integer userId;
    Date    startTime;
    Date    endTime;

    public Order(){}

    public Order(int number,int price,String description, int userId,Date startTime,Date endTime){
        this.number = number;
        this.price = price;
        this.description =description;
        this.userId = userId;
        this.startTime =startTime;
        this.endTime = endTime;
    }
}
