package com.example.pojo;

import lombok.Data;

@Data
public class User {
    Integer id;
    String username;
    String password;
    Integer balance;
    String gender;
    String email;
}
