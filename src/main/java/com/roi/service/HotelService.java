package com.roi.service;

import com.roi.model.Hotel;

import java.util.List;

public interface HotelService {

    /**
     * Add given hotel to database
     *
     * @param hotel given hotel
     *
     */
    void addHotel(Hotel hotel);

    /**
     * Remove hotel with given id
     *
     * @param id given hotel id
     */
    void removeHotel(int id);

    /**
     * Get hotel with given id
     *
     * @param id given hotel id
     *
     * @return hotel with given id if it exists, null otherwise
     */
    Hotel getHotelById(int id);

    /**
     * Get all hotels in database
     *
     * @return List of all hotels
     */
    List<Hotel> getAllHotels();

    /**
     * Get hotel with given info
     *
     * @param info given hotel info
     *
     * @return hotel with given id if it exists, null otherwise
     */
    Hotel findByInfo(String info);


}
