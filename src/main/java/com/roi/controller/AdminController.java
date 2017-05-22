package com.roi.controller;

import com.roi.model.Hotel;
import com.roi.model.Role;
import com.roi.model.User;
import com.roi.service.HotelService;
import com.roi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

/**
 * Controller for admin page
 */
@Controller
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final HotelService hotelService;

    private final UserService userService;

    @Autowired
    public AdminController(HotelService hotelService, UserService userService) {
        this.hotelService = hotelService;
        this.userService = userService;
    }


    /**
     * Add the hotel with received info
     * @param str Info of the hotel to add
     * @return Admin page
     */
    @RequestMapping(value = "/users/addH", method = RequestMethod.POST)
    public String addHotel(@RequestParam("info") String str) {
        log.info("Starting to add new hotel " + str);
        Hotel hotel = new Hotel(str);
        this.hotelService.addHotel(hotel);
        log.info("Done successfully  ");
        return "redirect:/users/";
    }


    /**
     * Remove the hotel by ID
     * @param id ID of the Hotel to remove
     * @return admin page
     */
    @RequestMapping("/remove/{hotelId}")
    public String removeHotel(@PathVariable("hotelId") int id) {
        log.info("Starting to remove hotel with id" + id);
        this.hotelService.removeHotel(id);
        log.info("Done successfully  " );

        return "redirect:/users/";
    }


    /**
     * Add attributes new user, all users, new hotel, all hotels, all users not admins to the model
     * @param model Model to add attributes
     * @return Admin page
     */
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("getAllUsers", this.userService.getAllUsers());
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("getAllHotelId", this.hotelService.getAllHotels());
        model.addAttribute("getAllNotAdmins", this.userService.getAllNotAdmins());
        log.info("Starting to get all users" );
        return "users";
    }


    /**
     * Remove the user with chosen ID
     * @param id ID of user (not admin) to remove
     * @return Admin page
     */
    @RequestMapping("/removeUser/{id}")
    public String removeUser(@PathVariable("id") long id) {
        log.info("Starting to remove user with id" + id);
        this.userService.removeUser(id);
        log.info("Done successfully  " );
        return "redirect:/users/";
    }


    /**
     * Add a manager with chosen username and hotel
     * @param hotelInfo Hotel to add manager
     * @param username Name of user to add hotel
     * @return Admin page
     */
    @RequestMapping(value = "/users/addManager", method = RequestMethod.POST)
    @Transactional
    public String addManager(@RequestParam("HotelInfo") String hotelInfo, @RequestParam("Username") String username) {
        User user = this.userService.findByUsername(username);
        Hotel hotel = this.hotelService.findByInfo(hotelInfo);
        user.setAttachedHotel(hotel);
        Role role = new Role();
        role.setId(3);
        role.setName("ROLE_MANAGER");
        user.getRoles().add(role);
        log.info("Starting to add Manager" + username + " to the hotel" + hotelInfo);
        this.userService.editUser(user);
        log.info("Done successfully  " );

        return "redirect:/users";

    }

}
