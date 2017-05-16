package com.roi.service;

import com.roi.model.Hotel;
import com.roi.model.Room;

import java.util.List;

/**
 * Created by alexander-k on 16.05.17.
 */
public interface RoomService {
    List<Room> getRoomByHotel(Hotel hotel);
    void addRoomToHotel(Hotel hotel);

}
