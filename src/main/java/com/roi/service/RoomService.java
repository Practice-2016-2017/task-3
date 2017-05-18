package com.roi.service;

import com.roi.model.Hotel;
import com.roi.model.Room;

import java.util.List;


public interface RoomService {
    List<Room> getRoomByHotel(Hotel hotel);
    void addRoomToHotel(Hotel hotel);
    List<Room> getAllRooms();
    Room getRoomById(int id);

}
