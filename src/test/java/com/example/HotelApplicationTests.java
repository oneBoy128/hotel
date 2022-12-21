package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootTest
class HotelApplicationTests {

    //日期加减测试
    @Test
    void contextLoads() {
        Date date = Date.valueOf("2022-12-31");
        int i = 1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,i);
        //将calender的time转换成sql.date
        java.util.Date utilDate = (java.util.Date)calendar.getTime();
        Date newDate = new Date(utilDate.getTime());
        System.out.println(newDate);
    }

}
