package com.roi.controller;

import com.roi.service.HotelService;
import com.roi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anatoly Shelikhovsky
 * Date: 15.05.2017
 * Time: 22:45
 * hotel
 */
@Controller
public class WelcomeController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/welcome/{username}", method = RequestMethod.GET)
    public String getRole(Model model, @PathVariable("username") String name) {
        model.addAttribute("getRole", this.userService.getRoleByUsername(name));
        return "redirect:/welcome/";
    }
}
