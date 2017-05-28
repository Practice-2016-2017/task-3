package com.roi.service;

import com.roi.model.Booking;
import com.roi.model.Hotel;
import com.roi.model.Room;
import com.roi.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
    public void iterator_will_return_hello_world() {
        //подготавливаем
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        //выполняем
        String result = i.next()+" "+i.next();
        //сравниваем
        assertEquals("Hello World", result);
    }
    @Test
    public void abT () throws Exception{
        System.out.println(" Test");
        User mockuser1= mock(User.class);
        //User mockuser2= mock(User.class);
        Date mockdate = mock(Date.class);
        Room mockroom = mock(Room.class);
        BookingServiceImpl mockbook = mock(BookingServiceImpl.class);
        mockbook.addBookingToRoom(mockuser1, mockdate, mockroom);
        assertTrue(mockbook.checkBookingByRoomAndDate(mockroom.getRoomId(), mockdate));
    }

  /*  @Test
    public void addBookingToRoomTest() throws Exception {
        System.out.println("Test for adding booking to room");
        System.out.println("");
        Room room = new Room();
        User user1 = new User();
        User user2 = new User();
        Booking booking = new Booking();
        user1 = userService.findByUsername("karych_72");
        user2 = userService.findByUsername("Nika1996");
        Hotel hotel = new Hotel();
        hotel = hotelService.getHotelById(1);
        Date date = new Date(2015, 10, 15);
        room.setHotel(hotel);
        room = roomService.getRoomById(1);
        bookingService.addBookingToRoom(user1, date, room);
        booking.setRoom(room);
        booking.setDate(date);
        assertTrue(bookingService.checkBookingByRoomAndDate(room.getRoomId(), date));
        try {
            bookingService.addBookingToRoom(user2, date, room);
        } catch(Exception e) {
            fail("Wrong, test failed");
        }
        bookingService.removeBooking(booking.getBookingId());
    }
*/
}