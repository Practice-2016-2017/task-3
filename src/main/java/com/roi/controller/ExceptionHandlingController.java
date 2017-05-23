package com.roi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

/**
 * Controller for handling exceptions
 */

@Controller
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Data")
    @ExceptionHandler(NestedServletException.class)
    public String notDeleted(Model model) {
        model.addAttribute("errorText", "This hotel does not exist anymore!");
        return "redirect:/users/";
    }

    @RequestMapping(value = "/errorPage/{errorText}")
    public String errorPage(Model model, @PathVariable("errorText") String errorText) {
        model.addAttribute("errorText",errorText);

        return "errorPage";
    }

}
