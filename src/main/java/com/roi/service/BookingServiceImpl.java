package com.roi.service;


import com.roi.dao.BookingDao;
import com.roi.dao.HotelDao;
import com.roi.dao.RoomDao;
import com.roi.model.Booking;
import com.roi.model.Hotel;
import com.roi.model.Room;
import com.roi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
        Iterator iter = bookingList.iterator();
        while (iter.hasNext()) {
            Booking booking = (Booking) iter.next();
            if (!booking.getRoom().getRoomId().equals(room.getRoomId()))
                iter.remove();
        }
        return bookingList;
    }

    @Override
    @Transactional
    public List<Hotel> getHotelsByDate(Date date){
        List<Booking> bookingList = bookingDao.findAll();
        List<Hotel> hotels = hotelDao.findAll();
        ArrayList<Integer> hotelsToRemove = new ArrayList<Integer>();
        Iterator iter = hotels.iterator();
        while (iter.hasNext()) {
            Hotel hotel = (Hotel) iter.next();
            if(getRoomByDateAndHotel(date,hotel).isEmpty()){
                iter.remove();
            }
        }

        return hotels;

        }


    @Override
    @Transactional
    public List<Room> getRoomByDateAndHotel(Date date, Hotel hotel){
        List<Room> rooms = roomService.getRoomsInHotel(hotel);
        List<Booking> bookingList = bookingDao.findAll();
        ArrayList<Integer> roomsToRemove = new ArrayList<Integer>();

        Iterator iter = bookingList.iterator();

        while (iter.hasNext()) {
            Booking booking = (Booking) iter.next();
            Date date1 = booking.getDate();

            if( (!roomsToRemove.contains(booking.getRoom().getRoomId())) & date1.getDay()==date.getDay() & date1.getYear()==date.getYear() & date1.getMonth()==date.getMonth()){
                roomsToRemove.add(booking.getRoom().getRoomId());
            }

        }

        iter = rooms.iterator();
        while (iter.hasNext()) {
            Room room = (Room) iter.next();
            if(roomsToRemove.contains(room.getRoomId())){
                iter.remove();
            }

        }

        return rooms;

    }

    @Override
    @Transactional
    public List<Booking> getAllBookings() {
        return bookingDao.findAll();
    }
}
