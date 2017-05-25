package com.roi.service;

import com.roi.model.Hotel;
import com.roi.model.Room;

import java.util.List;


public interface RoomService {

    /**
     * Get all rooms attached to the given hotel
     *
     * @param hotel given hotel
     * @return hotel with given id if it exists, null otherwise
     */
    List<Room> getRoomByHotel(Hotel hotel);

    /**
     * Add new room to the given hotel
     *
     * @param hotel given hotel
     */
    void addRoomToHotel(Hotel hotel);

    /**
     * Get all rooms from database
     *
     * @return list of all rooms in database
     */
    List<Room> getAllRooms();

    /**
     * Get room with given id
     *
     * @param id given room id
     * @return room with given id if it exists, null otherwise
     */
    Room getRoomById(int id);

}

