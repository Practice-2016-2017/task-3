package com.roi.service;

import com.roi.dao.HotelDao;
import com.roi.model.Hotel;
import com.roi.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class HotelServiceImpl implements HotelService {



    @Autowired
    private HotelDao hotelDao;


    @Override
    public void addHotel(Hotel hotel) {
        hotelDao.saveAndFlush(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        hotelDao.saveAndFlush(hotel);

    }


    @Override
    public void removeHotel(int id) {
        hotelDao.delete(id);
    }

    @Override
    @Transactional
    public Hotel getHotelById(int id) {
        return hotelDao.findOne(id);
    }



    @Override
    @Transactional
    public List<Hotel> getAllHotels() {

        List<Hotel> hotelList = hotelDao.findAll();
        return hotelList;
    }

    @Override
    public Hotel findByInfo(String info) {
        return hotelDao.findByInfo(info);
    }
}
