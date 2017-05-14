package com.roi.service;

import com.roi.model.Hotel;
import com.roi.model.User;

import java.util.List;

public interface HotelService {
    void addHotel(Hotel hotel);
    void updateHotel(Hotel hotel);
    void removeHotel(int id);
    Hotel getHotelById(int id);
    List<Hotel> getAllHotels();


}
