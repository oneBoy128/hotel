package com.example.service;

import com.example.pojo.Room;
import com.example.pojo.Time;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomService {
    //添加房间
    int addRoom(Room room);

    //查询所有房间
    List<Room> selectAll();

    //根据Ids查房间
    List<Room> selectByIds(Time time);

    //根据number查房间
    Room selectByNumber(int number);

    //根据Id查房间
    Room selectById(int id);

    //预定房间
    int orderRoom(int id,Time time);
}
