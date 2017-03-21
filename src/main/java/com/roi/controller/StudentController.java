package com.roi.controller;

import com.roi.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

@Controller
public class StudentController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("user", "command", new User());
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("SpringWeb")User user,

                             ModelMap model) {
        model.addAttribute("name", user.getName());
        model.addAttribute("status", user.getStatus());
        model.addAttribute("id", user.getId());

        return "result";
    }
}