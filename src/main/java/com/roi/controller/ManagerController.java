package com.roi.controller;

import com.roi.model.Hotel;
import com.roi.model.Role;
import com.roi.model.Room;
import com.roi.model.User;
import com.roi.service.HotelService;
import com.roi.service.RoomService;
import com.roi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Controller
public class ManagerController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;




    @RequestMapping(value = "/manager/addRoom", method = RequestMethod.GET)
    @Transactional
    public String manager(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("username", name);

        roomService.addRoomToHotel(userService.findByUsername(name).getAttachedHotel());

        return "redirect:/manager/";
    }

}
