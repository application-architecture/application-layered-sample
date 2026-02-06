package com.application_layered.controller.mvc;

import com.application_layered.domain.User;
import com.application_layered.repository.UserView;
import com.application_layered.service.UserQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping({"", "/", "/users"})
public class UserViewController {

    private final UserQueryService service;

    public UserViewController(UserQueryService service) {
        this.service = service;
    }

    @GetMapping("/searchForm")
    public String showSearchForm(
            Model model,
            @ModelAttribute("error") String error,
            @ModelAttribute("message") String message
    ) {
        model.addAttribute("error", error);
        model.addAttribute("message", message);

        return "form/main-page-search";
    }

    @GetMapping("/newOrEdit")
    public String showUpdateAndCreateForm(
            @RequestParam(required = false) String name, Model model
    ) {
        UserView user = service.findByExactName(name).orElse(new UserView());
        model.addAttribute("user", user);
        return "form/create-edit-form";
    }

    @GetMapping("/confirm-delete")
    public String confirmDelete(@RequestParam String name, Model model) {
        Optional<User> userOpt = service.findByNameStartingWith(name);
        if (personOpt.isPresent()) {
            model.addAttribute("person", personOpt.get());
            return "form/delete-confirm-form";
        } else {
            model.addAttribute("message", "Клиент с именем '" + name + "' не найден");
            return "form/main-page-search";
        }
    }

}
