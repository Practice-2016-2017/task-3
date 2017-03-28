package com.roi.dao;


import com.roi.model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelDaoImpl implements HotelDao {

    private static final Logger logger = LoggerFactory.getLogger(HotelDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addHotel(Hotel hotel) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(hotel);
        logger.info("Hotel successfully saved. Hotel details: " + hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(hotel);
        logger.info("Hotel successfully update. Hotel details: " + hotel);
    }

    @Override
    public void removeHotel(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Hotel hotel = (Hotel) session.load(Hotel.class, new Integer(id));

        if (hotel != null) {
            session.delete(hotel);
        }
        logger.info("Hotel successfully removed. Hotel details: " + hotel);
    }

    @Override
    public Hotel getHotelById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Hotel hotel = (Hotel) session.load(Hotel.class, new Integer(id));
        logger.info("Hotel successfully loaded. Hotel details: " + hotel);

        return hotel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Hotel> getAllHotels() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Hotel> hotelList = session.createQuery("from Hotel").list();
        for (Hotel hotel : hotelList) {
            logger.info("Hotel list: " + hotel);
        }

        return hotelList;
    }
}
