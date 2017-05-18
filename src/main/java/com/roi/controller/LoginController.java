package com.roi.controller;

import com.roi.model.Hotel;
import com.roi.model.User;
import com.roi.service.HotelService;
import com.roi.service.RoomService;
import com.roi.service.SecurityService;
import com.roi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * controller for {@link com.roi.model.User}
 */

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private com.roi.validator.UserValidator userValidator;

    @Autowired
    private RoomService roomService;

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    @Transactional
    public String manager(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Hotel currentHotel = userService.findByUsername(name).getAttachedHotel();
        model.addAttribute("attachedHotel", currentHotel.getInfo());
        model.addAttribute("Rooms", roomService.getRoomByHotel(currentHotel));
        return "manager";
    }


    @RequestMapping(value = "/tourist", method = RequestMethod.GET)
    public String tourist(Model model) {
        model.addAttribute("getAllRooms", this.roomService.getAllRooms());
        model.addAttribute("getAllHotels", this.hotelService.getAllHotels());
        return "tourist";
    }
}
