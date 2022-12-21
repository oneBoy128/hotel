package com.example.pojo;

import lombok.Data;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Data
public class Time {
    Integer id;
    Integer userId;
    Integer roomId;
    Integer number;
    Date    startTime;
    Date    endTime;
    Integer days;

    //实现日期+i的方法
    public Date addDate(Date date,int i){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,i);
        //将calender的time转换成sql.date
        java.util.Date utilDate = (java.util.Date)calendar.getTime();
        Date newDate = new Date(utilDate.getTime());
        return newDate;
    }
}
