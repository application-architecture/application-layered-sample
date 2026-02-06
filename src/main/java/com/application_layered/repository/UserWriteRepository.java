package com.application_layered.repository;

import com.application_layered.domain.User;

import java.util.UUID;

public interface UserWriteRepository {

    void save(User user);
    void deleteById(UUID id);

}
