package com.roi.dao;

import com.roi.model.Hotel;

import java.util.List;

public interface HotelDao {
    public void addHotel(Hotel hotel);

    public void updateHotel(Hotel hotel);

    public void removeHotel(int id);

    public Hotel getHotelById(int id);

    public List<Hotel> getAllHotels();
}