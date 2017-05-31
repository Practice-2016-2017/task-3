package com.roi.service;

import com.roi.dao.BookingDao;
import com.roi.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by User on 26.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {


    @Mock
    RoomService roomService;

    @Mock
    BookingDao bookingDao;

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
        User mockuser1= Mockito.mock(User.class);
        mockuser1.setId((long) 123);
        when(mockuser1.getId()).thenReturn(Long.valueOf("123"));
        //User mockuser2= mock(User.class);
        Date mockdate = new Date(2017, 10, 23);
        Room mockroom = Mockito.mock(Room.class);
        Role mockrole = Mockito.mock(Role.class);
        when(mockrole.getId()).thenReturn(1);
    //  when(mockrole.getUsers()).thenReturn((Set<User>) mockuser1);
        when(mockrole.getName()).thenReturn("ROLE_TOURIST");
        Hotel mockhotel = Mockito.mock(Hotel.class);
        when(mockhotel.getHotelId()).thenReturn(1);
        when(mockhotel.getInfo()).thenReturn("pribalton");
     //   when(mockhotel.getUsers()).thenReturn((Set<User>) mockuser1);
     //   when(mockhotel.getRoom()).thenReturn((Set<Room>) mockroom);
        when(mockroom.getRoomId()).thenReturn(1);
        when(mockroom.getRoomNum()).thenReturn(4);
        when(mockroom.getHotel()).thenReturn(mockhotel);
        when(mockuser1.getConfirmPassword()).thenReturn("uimi");
        when(mockuser1.getPassword()).thenReturn("uimi");
     //   when(mockuser1.getRoles()).thenReturn((Set<Role>) mockrole);
        when(mockuser1.getAttachedHotel()).thenReturn(mockhotel);
        System.out.println(" userId: " + mockuser1.getId() + ", RoomId: " + mockroom.getRoomId()+ ", date: " + mockdate.toString());
        BookingServiceImpl mockbook = mock(BookingServiceImpl.class);
        mockbook.addBookingToRoom(mockuser1, mockdate, mockroom);
        verify(mockbook).addBookingToRoom(mockuser1, mockdate, mockroom);
        assertEquals(false, mockbook.checkBookingByRoomAndDate(mockroom.getRoomId(), mockdate));
    }
    @Test
    public void grdh () throws Exception {
        System.out.println(" Test2");
        Date date = new Date();
        Hotel mockhotel = mock(Hotel.class);

        BookingServiceImpl mockbook = mock(BookingServiceImpl.class);
        mockbook.getRoomByDateAndHotel(date, mockhotel);
        verify(mockbook).getRoomByDateAndHotel(date, mockhotel);

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