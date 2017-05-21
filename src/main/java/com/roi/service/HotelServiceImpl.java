package com.roi.service;

import com.roi.dao.HotelDao;
import com.roi.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link HotelService}
 */
@Service
public class HotelServiceImpl implements HotelService {
    private static final Logger log = LoggerFactory.getLogger(HotelService.class);

    @Autowired
    public HotelServiceImpl(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }


    private final HotelDao hotelDao;


    @Override
    public void addHotel(Hotel hotel) {
        log.info("Adding new hotel " + hotel.getInfo());
        hotelDao.saveAndFlush(hotel);
    }



    @Override
    public void removeHotel(int id) {
        log.info("Removing hotel with id " + id);
        hotelDao.delete(id);
    }

    @Override
    @Transactional
    public Hotel getHotelById(int id) {
        log.info("Looking for hotel with id " + id);
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
        log.info("Looking for hotel with info " + info);
        return hotelDao.findByInfo(info);
    }
}
