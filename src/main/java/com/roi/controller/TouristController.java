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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/tourist/addBooking/{chosenDate}", method = RequestMethod.POST)
    public String addHotel(@RequestParam("chooseRoom") int id, @PathVariable("chosenDate") String date) {
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

    @RequestMapping(value = "/tourist/choose", method = RequestMethod.POST)
    public String tourist(Model model, @RequestParam("date") String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateUtil = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date dateSQL = new java.sql.Date(dateUtil.getTime());

        model.addAttribute("getHotelsByDate", this.bookingService.getHotelsByDate(dateSQL));
        //model.addAttribute("getRoomsInHotel", this.roomService.getRoomByHotel(hotelService.findByHotelInfo(hotelInfo)));
        model.addAttribute("chosenDate",dateSQL.toString());
        return "tourist";
    }





   @RequestMapping(value = "/tourist/hotelchoose/{chosenDate}")
    public String chooseHotel(Model model, @RequestParam("choosehotel") int hotelId, @PathVariable("chosenDate") String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateUtil = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date dateSQL = new java.sql.Date(dateUtil.getTime());

        model.addAttribute("getAvailableRooms", bookingService.getRoomByDateAndHotel(dateSQL, hotelService.getHotelById(hotelId)));
        return "tourist";
    }
}
