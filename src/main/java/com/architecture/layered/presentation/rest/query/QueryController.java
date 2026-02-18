package com.architecture.layered.presentation.rest.query;

import com.architecture.layered.application.api.QueryUseCase;
import com.architecture.layered.domain.User;
import com.architecture.layered.presentation.common.dto.Mapper;
import com.architecture.layered.presentation.common.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public final class QueryController {

    private final QueryUseCase service;

    public QueryController(QueryUseCase service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getDetails(@PathVariable String id) {
        return Mapper.toResponse(service.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Response> list(@RequestParam(required = false) String namePrefix) {

        List<User> users = (namePrefix == null)
                ? service.findAll()
                : service.findByNameStartingWith(namePrefix);

        return users.stream().map(Mapper::toResponse).toList();
    }

}
