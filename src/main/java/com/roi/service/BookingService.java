package com.roi.service;


import com.roi.model.Booking;
import com.roi.model.Hotel;
import com.roi.model.Room;
import com.roi.model.User;

import java.util.Date;
import java.util.List;

public interface BookingService {
    Booking getBookingById(int id);
    void addBookingToRoom(User user, Date date, Room room);
    List<Hotel> getHotelsByDate(Date date);
    List<Room> getRoomByDateAndHotel(Date date, Hotel hotel);
    List<Booking> getBookingsByUser(User user);
    void removeBooking(int id);
    Boolean checkBookingByRoomAndDate(int roomId, Date date);
}
