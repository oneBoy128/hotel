package com.example.mapper;

import com.example.pojo.Time;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

@Mapper
public interface TimeMapper {

    //添加日期表
    int add(@Param("time")Time time);

    //查询全部表
    List<Time> selectAll();

    //查询非法startTime查询 返回不可用房间Id
    List<Integer> selectRooms(@Param("dates")List<Date> dates);

    //根据userId startTime number删除time表
    int deleteTime(@Param("userId")int userId,@Param("startTime")Date startTime,@Param("number")int number);

}
