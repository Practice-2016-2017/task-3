package com.roi.service;


import com.roi.dao.BookingDao;
import com.roi.dao.HotelDao;
import com.roi.model.Booking;
import com.roi.model.Hotel;
import com.roi.model.Room;
import com.roi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private RoomService roomService;

    @Override
    @Transactional
    public void addBookingToRoom(User user, Date date, Room room) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setDate(date);
        booking.setRoom(room);
        bookingDao.saveAndFlush(booking);
    }

    @Override
    @Transactional
    public List<Booking> getBookingByRoom(Room room) {
        List<Booking> bookingList = bookingDao.findAll();
        bookingList.removeIf(booking -> !booking.getRoom().getRoomId().equals(room.getRoomId()));
        return bookingList;
    }

    @Override
    @Transactional
    public List<Hotel> getHotelsByDate(Date date) {
        bookingDao.findAll();
        List<Hotel> hotels = hotelDao.findAll();
        hotels.removeIf(hotel -> getRoomByDateAndHotel(date, hotel).isEmpty());

        return hotels;

    }


    @Override
    @Transactional
    public List<Room> getRoomByDateAndHotel(Date date, Hotel hotel) {
        java.sql.Date dateSQL = new java.sql.Date(date.getTime());
        List<Room> rooms = roomService.getRoomsInHotel(hotel);
        List<Booking> bookingList = bookingDao.findAll();
        ArrayList<Integer> roomsToRemove = new ArrayList<>();
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
    public List<Booking> getAllBookings() {
        return bookingDao.findAll();
    }
}
