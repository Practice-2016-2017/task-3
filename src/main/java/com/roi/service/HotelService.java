package com.roi.service;

import com.roi.model.Hotel;
import com.roi.model.Room;

import java.util.List;
import java.util.Set;

public interface HotelService {
    void addHotel(Hotel hotel);
    void updateHotel(Hotel hotel);
    void removeHotel(int id);
    Hotel getHotelById(int id);
    List<Hotel> getAllHotels();
    Hotel findByInfo(String info);


}
