package com.roi.controller;

import com.roi.model.User;
import com.roi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

import javax.transaction.Transactional;

/**
 * Controller for handling exceptions
 */

@Controller
public class ExceptionHandlingController {

    private final UserService userService;


    @Autowired
    public ExceptionHandlingController(UserService userService) {
        this.userService = userService;
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Data")
    @ExceptionHandler(NestedServletException.class)
    public String notDeleted(Model model) {
        model.addAttribute("errorText", "This hotel does not exist anymore!");
        return "redirect:/users/";
    }

    @RequestMapping(value = "/errorPage/{errorText}")
    @Transactional
    public String errorPage(Model model, @PathVariable("errorText") String errorText) {
        model.addAttribute("errorText",errorText);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        if(userService.getRoleByUsername(name).equals("ROLE_ADMIN"))
            model.addAttribute("linkBack","users");
        else
            model.addAttribute("linkBack", "tourist");
        return "errorPage";
    }

}
