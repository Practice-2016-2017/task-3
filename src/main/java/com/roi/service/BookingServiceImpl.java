package com.roi.service;


import com.roi.dao.BookingDao;
import com.roi.dao.HotelDao;
import com.roi.model.Booking;
import com.roi.model.Hotel;
import com.roi.model.Room;
import com.roi.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private static final Logger log = Logger.getLogger(BookingService.class);

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
        log.info("Adding booking to room "+room.getRoomId()+" by user "+user.getUsername()+" on date "+date.toString());
        bookingDao.saveAndFlush(booking);
    }

    @Override
    @Transactional
    public List<Booking> getBookingByRoom(Room room) {
        log.info("Getting bookings for room "+room.getRoomId());
        List<Booking> bookingList = bookingDao.findAll();
        bookingList.removeIf(booking -> !booking.getRoom().getRoomId().equals(room.getRoomId()));
        return bookingList;
    }

    @Override
    @Transactional
    public List<Hotel> getHotelsByDate(Date date) {
        log.info("Getting list of hotels available for date "+date.toString());
        bookingDao.findAll();
        List<Hotel> hotels = hotelDao.findAll();
        hotels.removeIf(hotel -> getRoomByDateAndHotel(date, hotel).isEmpty());
        return hotels;

    }


    @Override
    @Transactional
    public List<Room> getRoomByDateAndHotel(Date date, Hotel hotel) {
        java.sql.Date dateSQL = new java.sql.Date(date.getTime());
        log.info("Prepairing for getting rooms in hotel "+hotel.getInfo()+" for date "+date.toString());
        List<Room> rooms = roomService.getRoomsInHotel(hotel);
        List<Booking> bookingList = bookingDao.findAll();
        ArrayList<Integer> roomsToRemove = new ArrayList<>();
        log.info("Creating a list of available rooms in hotel "+hotel.getInfo()+" for date "+date.toString());
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
        log.info("Getting all bookings");
        return bookingDao.findAll();
    }

    @Override
    @Transactional
    public List<Booking> getBookingsByUser(User user){
        List<Booking> bookingList = bookingDao.findAll();
        Iterator iter = bookingList.iterator();
        while (iter.hasNext()) {
            Booking booking = (Booking) iter.next();
            if(! booking.getUser().getId().equals(user.getId()))
                iter.remove();
        }
        return bookingList;
    }

    @Override
    @Transactional
    public void removeBooking(int id) {
        log.info("Removing  booking with id "+id);
        bookingDao.delete(id);
    }

}
