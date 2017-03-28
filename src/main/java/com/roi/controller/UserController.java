package com.roi.controller;

import com.roi.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user() {
        return new ModelAndView("user", "command", new User());
    }

    @RequestMapping(value = "/adduser.htm", method = RequestMethod.POST)
    public String addSUser(User user, ModelMap model) {
        model.addAttribute("login", user.getLogin());
        model.addAttribute("age", user.getAge());
        model.addAttribute("id", user.getId());

        return "result";
    }
}