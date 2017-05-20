package com.roi.controller;

import com.roi.service.RoomService;
import com.roi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;


/**
 * Controller for a manager page
 */
@Controller
public class ManagerController {


    private final UserService userService;

    private final RoomService roomService;

    @Autowired
    public ManagerController(UserService userService, RoomService roomService) {
        this.userService = userService;
        this.roomService = roomService;
    }


    /**
     * Add room to the attached hotel of the current manager and add his username to the model
     * @param model Model to add attributes
     * @return Manager page
     */
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
