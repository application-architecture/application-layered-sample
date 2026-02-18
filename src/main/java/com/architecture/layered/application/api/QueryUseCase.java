package com.architecture.layered.application.api;

import com.architecture.layered.domain.User;

import java.util.List;

public interface QueryUseCase {

    User findById(String id);

    List<User> findAll();

    List<User> findByNameStartingWith(String prefix);

}
