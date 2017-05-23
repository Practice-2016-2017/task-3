package com.roi.service;


import com.roi.dao.BookingDao;
import com.roi.dao.HotelDao;
import com.roi.model.Booking;
import com.roi.model.Hotel;
import com.roi.model.Room;
import com.roi.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Implementation of {@link BookingService}
 */
@Service
public class BookingServiceImpl implements BookingService {
    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    private final BookingDao bookingDao;

    private final HotelDao hotelDao;

    private final RoomService roomService;

    @Autowired
    public BookingServiceImpl(BookingDao bookingDao, HotelDao hotelDao, RoomService roomService) {
        this.bookingDao = bookingDao;
        this.hotelDao = hotelDao;
        this.roomService = roomService;
    }

    @Override
    @Transactional
    public void addBookingToRoom(User user, Date date, Room room) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setDate(date);
        booking.setRoom(room);
        log.info("Adding booking to room " + room.getRoomId() + " by user " + user.getUsername() + " on date " + date.toString());
        bookingDao.saveAndFlush(booking);
    }


    @Override
    @Transactional
    public List<Hotel> getHotelsByDate(Date date) {
        log.info("Getting list of hotels available for date " + date.toString());
        bookingDao.findAll();
        List<Hotel> hotels = hotelDao.findAll();
        hotels.removeIf(hotel -> getRoomByDateAndHotel(date, hotel).isEmpty());
        return hotels;

    }


    @Override
    @Transactional
    public List<Room> getRoomByDateAndHotel(Date date, Hotel hotel) {
        java.sql.Date dateSQL = new java.sql.Date(date.getTime());
        log.info("Preparing for getting rooms in hotel " + hotel.getInfo() + " for date " + date.toString());
        List<Room> rooms = roomService.getRoomByHotel(hotel);
        List<Booking> bookingList = bookingDao.findAll();
        ArrayList<Integer> roomsToRemove = new ArrayList<>();
        log.info("Creating a list of available rooms in hotel " + hotel.getInfo() + " for date " + date.toString());
        for (Booking booking : bookingList) {
            Date date1 = booking.getDate();
            if ((!roomsToRemove.contains(booking.getRoom().getRoomId())) && dateSQL.equals(date1)/*date1.getDay() == date.getDay() && date1.getYear() == date.getYear() && date1.getMonth() == date.getMonth()*/) {
                roomsToRemove.add(booking.getRoom().getRoomId());
            }
        }
        rooms.removeIf(room -> roomsToRemove.contains(room.getRoomId()));
        return rooms;

    }


    @Override
    @Transactional
    public List<Booking> getBookingsByUser(User user) {
        List<Booking> bookingList = bookingDao.findAll();
        bookingList.removeIf(booking -> !booking.getUser().getId().equals(user.getId()));
        return bookingList;
    }

    @Override
    @Transactional
    public void removeBooking(int id) {
        log.info("Removing  booking with id " + id);
        bookingDao.delete(id);
    }

    @Override
    @Transactional
    public Boolean checkBookingByRoomAndDate(int roomId, Date date) {
        List<Booking> bookingList = bookingDao.findAll();
        bookingList.removeIf(booking -> (!booking.getRoom().getRoomId().equals(roomId) || !(booking.getDate().getYear() == date.getYear() &  booking.getDate().getDay() == date.getDay() & booking.getDate().getMonth() == date.getMonth())));
        return bookingList.isEmpty();
    }

    @Override
    @Transactional
    public Booking getBookingById(int id){
        return bookingDao.findOne(id);
    }

}
