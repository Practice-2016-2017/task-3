package com.roi.controller;

import com.roi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);

    private final UserService userService;

    @Autowired
    public WelcomeController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Levels of access: "Admin", "Manager", "User"
     *
     * @param name Username of client
     * @return jsp page depending on the level of access
     */
    @RequestMapping(value = "/welcome/{username}", method = RequestMethod.GET)
    @Transactional
    public String getRole(@PathVariable("username") String name) {
        log.info("Starting to get role with name:  " + name);

        switch (this.userService.getRoleByUsername(name)) {
            case "ROLE_ADMIN": {
                log.info("Done successfully  ");
                return "redirect:/admin/";
            }
            case "ROLE_MANAGER": {
                log.info("Done successfully  ");
                return "redirect:/manager/";
            }
            default: {
                log.info("Done successfully  ");
                return "redirect:/tourist/";
            }

        }

    }
}
