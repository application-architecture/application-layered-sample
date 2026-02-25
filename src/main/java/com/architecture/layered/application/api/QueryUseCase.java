package com.architecture.layered.application.api;

import com.architecture.layered.application.api.query.UserView;

import java.util.List;

public interface QueryUseCase {
    UserView findById(String id);
    List<UserView> findAll();
    List<UserView> findByNameStartingWith(String prefix);
}
