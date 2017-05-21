package com.roi.service;


import com.roi.model.Booking;
import com.roi.model.Hotel;
import com.roi.model.Room;
import com.roi.model.User;

import java.util.Date;
import java.util.List;

public interface BookingService {
    void addBookingToRoom(User user, Date date, Room room);
    List<Booking> getBookingByRoom(Room room);
    List<Booking> getAllBookings();
    List<Hotel> getHotelsByDate(Date date);
    List<Room> getRoomByDateAndHotel(Date date, Hotel hotel);
    List<Booking> getBookingsByUser(User user);
    void removeBooking(int id);
}
