package com.architecture.layered.presentation.mvc;

import com.architecture.layered.domain.UserNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.architecture.layered.presentation.mvc")
public class GlobalMvcExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleNotFound(UserNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "form/main-page-search";
    }
}
