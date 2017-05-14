package com.roi.service;

import com.roi.dao.HotelDao;
import com.roi.model.Hotel;
import com.roi.model.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

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
}
