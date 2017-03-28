package com.roi.controller;

import com.roi.model.Hotel;
import com.roi.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class adminController {
    private HotelService hotelService;

    @Autowired(required = true)
    @Qualifier(value = "hotelService")
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @RequestMapping(value = "hotels", method = RequestMethod.GET)
    public String getAllHotels(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("getAllHotels", this.hotelService.getAllHotels());

        return "hotels";
    }

    @RequestMapping(value = "/hotels/add", method = RequestMethod.POST)
    public String addHotel(@ModelAttribute("hotel") Hotel hotel) {
        if (hotel.getHotelId() == 0) {
            this.hotelService.addHotel(hotel);
        } else {
            this.hotelService.updateHotel(hotel);
        }

        return "redirect:/hotels";
    }

    @RequestMapping("/remove/{hotelId}")
    public String removeHotel(@PathVariable("hotelId") int id) {
        this.hotelService.removeHotel(id);

        return "redirect:/hotels";
    }

    @RequestMapping("edit/{hotelId}")
    public String editHotel(@PathVariable("hotelId") int id, Model model) {
        model.addAttribute("hotel", this.hotelService.getHotelById(id));
        model.addAttribute("getAllHotels", this.hotelService.getAllHotels());

        return "hotels";
    }
}
