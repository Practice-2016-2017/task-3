package com.roi.controller;

import com.roi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Controller for a starting page after logging in
 */
@Controller
public class WelcomeController {

    private final UserService userService;

    @Autowired
    public WelcomeController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Levels of access: "Admin", "Manager", "User"
     * @param name Username of client
     * @return jsp page depending on the level of access
     */
    @RequestMapping(value = "/welcome/{username}", method = RequestMethod.GET)
    @Transactional
    public String getRole(@PathVariable("username") String name) {
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
