package com.example.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Center {
    Integer balance;
    Object obj;

    public Center(){}

    public Center(int balance,List<Order> orders){
        this.balance = balance;
        this.obj = orders;
    }
}
