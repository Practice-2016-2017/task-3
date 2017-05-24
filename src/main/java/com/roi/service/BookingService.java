package com.roi.service;


import com.roi.model.Booking;
import com.roi.model.Hotel;
import com.roi.model.Room;
import com.roi.model.User;

import java.util.Date;
import java.util.List;

public interface BookingService {

    /**
     * Search for booking with given Id in database
     *
     * @param id ID of searched booking
     *
     * @return booking with requested id
     */
    Booking getBookingById(int id);

    /**
     * Add booking for given date, hotel and room
     *
     * @param user given user
     * @param room given room
     * @param date given Date
     *
     */
    void addBookingToRoom(User user, Date date, Room room);

    /**
     * Get all bookings with given date
     *
     * @param date given date
     *
     * @return List of bookings with given date
     */
    List<Hotel> getHotelsByDate(Date date);

    /**
     * Get all rooms of given hotel without attached bookings for given date
     *
     * @param date given date
     * @param hotel given hotel
     *
     * @return List of rooms of given hotel without attached bookings for given date
     */
    List<Room> getRoomByDateAndHotel(Date date, Hotel hotel);

    /**
     * Get all bookings of given user
     *
     * @param user given user
     *
     * @return List of bookings of given user
     */
    List<Booking> getBookingsByUser(User user);

    /**
     * Remove booking with given id
     *
     * @param id given id
     *
     */
    void removeBooking(int id);

    /**
     * Check whether booking with given room and date doesn't exist
     *
     * @param roomId Id of given Room
     *
     * @return true if booking doesn't exist, false otherwise
     */
    Boolean checkBookingByRoomAndDate(int roomId, Date date);
}
