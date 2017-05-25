package com.roi.service;

import com.roi.model.Hotel;
import com.roi.model.Room;
import com.roi.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by User on 26.05.2017.
 */

public class BookingServiceImplTest {
    @Autowired
    private BookingServiceImpl bookingService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private HotelServiceImpl hotelService;

    @Before
    public void setUp() throws Exception {
        System.out.println(" Before test()");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(" afterTest()");
    }


    @Test
    public void addBookingToRoomTest() throws Exception {
        System.out.println("Test for adding booking to room");
        System.out.println("");
        Room room = new Room();
        User user1 = new User();
        User user2 = new User();
        user1 = userService.findByUsername("karych_72");
        user2 = userService.findByUsername("Nika1996");
        Hotel hotel = new Hotel();
        hotel = hotelService.getHotelById(1);
        Date date = new Date(2015, 10, 15);
        room.setHotel(hotel);
        room = roomService.getRoomById(1);
        bookingService.addBookingToRoom(user1, date, room);
        assertTrue(bookingService.checkBookingByRoomAndDate(room.getRoomId(), date));
        try {
            bookingService.addBookingToRoom(user2, date, room);
        } catch(Exception e) {
            fail("Wrong, test failed");
        }
        bookingService.removeBooking(1);
    }

}