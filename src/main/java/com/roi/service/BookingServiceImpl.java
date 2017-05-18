package com.roi.service;


import com.roi.dao.BookingDao;
import com.roi.model.Booking;
import com.roi.model.Room;
import com.roi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

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
    public List<Booking> getAllBookings() {
        return bookingDao.findAll();
    }
}
