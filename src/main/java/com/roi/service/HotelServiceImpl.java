package com.roi.service;

import com.roi.dao.HotelDao;
import com.roi.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hotelDao;

    public void setHotelDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @Override
    @Transactional
    public void addHotel(Hotel hotel) {
        this.hotelDao.addHotel(hotel);
    }

    @Override
    @Transactional
    public void updateHotel(Hotel hotel) {
        this.hotelDao.updateHotel(hotel);
    }

    @Override
    public void removeHotel(int id) {
        this.hotelDao.removeHotel(id);
    }

    @Override
    @Transactional
    public Hotel getHotelById(int id) {
        return this.hotelDao.getHotelById(id);
    }

    @Override
    @Transactional
    public List<Hotel> getAllHotels() {
        return this.hotelDao.getAllHotels();
    }
}
