package com.roi.controller;

import com.roi.model.Room;
import com.roi.model.User;
import com.roi.service.BookingService;
import com.roi.service.HotelService;
import com.roi.service.RoomService;
import com.roi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Controller
public class TouristController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date dateUtil;

    @RequestMapping(value = "/tourist/addBooking", method = RequestMethod.POST)
    public String addHotel(@RequestParam("date") String date, @RequestParam("rooms") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findByUsername(name);
        Room room = roomService.getRoomById(id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateUtil = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date dateSQL = new java.sql.Date(dateUtil.getTime());
        bookingService.addBookingToRoom(user, dateSQL, room);
        return "redirect:/tourist/";
    }

}
