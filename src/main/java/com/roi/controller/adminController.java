package com.roi.controller;

import com.roi.model.Hotel;
import com.roi.model.Role;
import com.roi.model.User;
import com.roi.service.HotelService;
import com.roi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Controller
public class AdminController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;


    /*@RequestMapping(value = "hotels", method = RequestMethod.GET)
    public String getAllHotels(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("getAllHotels", this.hotelService.getAllHotels());

        return "users";
    }*/

    @RequestMapping(value = "/users/addH", method = RequestMethod.POST)
    public String addHotel(@RequestParam("info") String str) {
        Hotel hotel = new Hotel(str);
        this.hotelService.addHotel(hotel);
        return "redirect:/users/";
    }


    @RequestMapping("/remove/{hotelId}")
    public String removeHotel(@PathVariable("hotelId") int id) {
        this.hotelService.removeHotel(id);

        return "redirect:/users/";
    }

    /*@RequestMapping("edit/{hotelId}")
    public String editHotel(@PathVariable("hotelId") int id, Model model) {
        model.addAttribute("hotel", this.hotelService.getHotelById(id));
        model.addAttribute("getAllHotels", this.hotelService.getAllHotels());

        return "/WEB-INF/hotels.jsp";
    }*/



    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("getAllUsers", this.userService.getAllUsers());
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("getAllHotelId", this.hotelService.getAllHotels());
        return "users";
    }


    @RequestMapping("/removeUser/{id}")
    public String removeUser(@PathVariable("id") long id) {
        this.userService.removeUser(id);

        return "redirect:/users/";
    }

    @RequestMapping(value = "/users/addManager", method = RequestMethod.POST)
    @Transactional
    public String addManager(@RequestParam("HotelInfo") String hotelInfo,@RequestParam("Username") String username){
        User user = this.userService.findByUsername(username);
        Hotel hotel = this.hotelService.findByInfo(hotelInfo);
        user.setAttachedHotel(hotel);
        Role role =  new Role();
        role.setId(3);
        role.setName("ROLE_MANAGER");
        user.getRoles().add(role);
        this.userService.editUser(user);

        return "redirect:/users/";

    }

}
