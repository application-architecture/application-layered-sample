package com.architecture.layered.presentation.mvc.query;

import com.architecture.layered.application.api.QueryUseCase;
import com.architecture.layered.domain.User;
import com.architecture.layered.presentation.common.dto.Mapper;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc/users/search")
public class SearchController {

    private final QueryUseCase service;

    public SearchController(QueryUseCase service) {
        this.service = service;
    }

    @GetMapping("/id")
    public String searchById(@RequestParam String id, Model model) {
        model.addAttribute("response", Mapper.toResponse(service.findById(id)));
        return "result/user-details";
    }

    @GetMapping("/name")
    public String searchByName(@RequestParam String name, Model model) {
        List<User> users = service.findByNameStartingWith(name);
        model.addAttribute("searchTerm", name);
        model.addAttribute(
                "userViews", users.stream()
                        .map(Mapper::toResponse)
                        .toList()
        );
        return "result/list";
    }

}
