package com.application_layered.service;

import com.application_layered.repository.UserReadRepository;
import com.application_layered.repository.UserView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserQueryService {

    private final UserReadRepository users;

    public UserQueryService(UserReadRepository users) {
        this.users = users;
    }

    public Optional<UserView> findById(UUID id) {
        return users.findById(id);
    }

    public List<UserView> findAll() {
        return users.findAll();
    }

    public List<UserView> findByNameStartingWith(String prefix) {
        return users.findByNameStartingWith(prefix);
    }

}
