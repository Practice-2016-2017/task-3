package com.roi.service;

import com.roi.dao.HotelDao;
import com.roi.model.Hotel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    private static final Logger log = Logger.getLogger(HotelService.class);

    @Override
    public Hotel findByHotelInfo(String hotelInfo) {
        log.info("Finding hotel by info "+hotelInfo);
        return hotelDao.findByInfo(hotelInfo);
    }

    @Autowired
    private HotelDao hotelDao;


    @Override
    public void addHotel(Hotel hotel) {
        log.info("Adding new hotel "+hotel.getInfo());
        hotelDao.saveAndFlush(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        log.info("Updating hotel "+hotel.getInfo());
        hotelDao.saveAndFlush(hotel);

    }


    @Override
    public void removeHotel(int id) {
        log.info("Removing hotel with id "+id);
        hotelDao.delete(id);
    }

    @Override
    @Transactional
    public Hotel getHotelById(int id) {
        log.info("Looking for hotel with id "+id);
        return hotelDao.findOne(id);
    }

    @Override
    @Transactional
    public List<Hotel> getAllHotels() {
        log.info("Getting all hotels");
        return hotelDao.findAll();
    }

    @Override
    public Hotel findByInfo(String info) {
        log.info("Looking for hotel with info "+info);
        return hotelDao.findByInfo(info);
    }
}
