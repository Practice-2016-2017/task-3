package com.roi.controller;

import com.roi.model.Hotel;
import com.roi.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class adminController {

    @Autowired
    private HotelService hotelService;



    @RequestMapping(value = "hotels", method = RequestMethod.GET)
    public String getAllHotels(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("getAllHotels", this.hotelService.getAllHotels());

        return "hotels";
    }

    @RequestMapping(value = "/hotels/add", method = RequestMethod.POST)
    public String addHotel(@RequestParam("info") String str) {
        Hotel hotel = new Hotel(str);
        this.hotelService.addHotel(hotel);


        return "redirect:/hotels/";
    }

    @RequestMapping("/remove/{hotelId}")
    public String removeHotel(@PathVariable("hotelId") int id) {
        this.hotelService.removeHotel(id);

        return "redirect:/hotels/";
    }

    @RequestMapping("edit/{hotelId}")
    public String editHotel(@PathVariable("hotelId") int id, Model model) {
        model.addAttribute("hotel", this.hotelService.getHotelById(id));
        model.addAttribute("getAllHotels", this.hotelService.getAllHotels());

        return "/WEB-INF/hotels.jsp";
    }
}
