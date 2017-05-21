package com.roi.service;

import com.roi.model.Hotel;

import java.util.List;

public interface HotelService {
    void addHotel(Hotel hotel);
    void removeHotel(int id);
    Hotel getHotelById(int id);
    List<Hotel> getAllHotels();
    Hotel findByInfo(String info);


}
