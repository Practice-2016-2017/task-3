package com.roi.controller;

import com.roi.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("user", "command", new User());
    }
    @RequestMapping("/adduser.htm")
    public String insert(HttpServletRequest req, User student)
    {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "result";
    }
}