package com.example.mapper;

import com.example.pojo.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomMapper {
    //添加房间
    int addRoom(@Param("room")Room room);
    //查询所有房间
    List<Room> selectAll();
    //根据number查房间
    Room selectByNumber(@Param("number") int number);
    //根据id查房间
    Room selectById(@Param("id") int id);

    //根据非法Ids查所有合法房间
    List<Room> selectByIllegalIds(@Param("roomsId")List<Integer> roomsId);

    //预定房间
    int orderRoom(@Param("id") int id,@Param("room") Room room);

}
