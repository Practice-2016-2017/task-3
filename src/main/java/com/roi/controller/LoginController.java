package com.roi.controller;

import com.roi.model.Hotel;
import com.roi.model.User;
import com.roi.service.*;
import com.roi.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;

/**
 * controller for {@link com.roi.model.User}
 */

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private final UserService userService;

    private final SecurityService securityService;

    private final com.roi.validator.UserValidator userValidator;

    private final RoomService roomService;

    private final HotelService hotelService;

    private final BookingService bookingService;

    @Autowired
    public LoginController(UserService userService, SecurityService securityService, UserValidator userValidator, RoomService roomService, HotelService hotelService, BookingService bookingService) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.roomService = roomService;
        this.hotelService = hotelService;
        this.bookingService = bookingService;
    }

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
        log.info("Starting registration");
        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());
        log.info("Done successfully  ");
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
        log.info("Starting to log in as admin");
        return "admin";
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    @Transactional
    public String manager(Model model) {
        log.info("Starting to log in as manager");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Hotel currentHotel = userService.findByUsername(name).getAttachedHotel();
        model.addAttribute("attachedHotel", currentHotel.getInfo());
        model.addAttribute("Rooms", roomService.getRoomByHotel(currentHotel));
        return "manager";
    }


    @RequestMapping(value = "/tourist", method = RequestMethod.GET)
    public String tourist(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = userService.findByUsername(name);
        log.info("Starting to log in as tourist");
        model.addAttribute("getUserBookings", this.bookingService.getBookingsByUser(user));
        model.addAttribute("getAllRooms", this.roomService.getAllRooms());
        model.addAttribute("getAllHotels", this.hotelService.getAllHotels());
        return "tourist";
    }
}
