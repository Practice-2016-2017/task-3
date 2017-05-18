package com.roi.controller;

import com.roi.service.HotelService;
import com.roi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WelcomeController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/welcome/{username}", method = RequestMethod.GET)
    @Transactional
    public String getRole(Model model, @PathVariable("username") String name) {
        switch (this.userService.getRoleByUsername(name)) {
            case "ROLE_ADMIN": {
                return "redirect:/admin/";
            }
            case "ROLE_MANAGER": {
                return "redirect:/manager/";
            }
            default: {
                return "redirect:/tourist/";
            }
        }
    }
}
