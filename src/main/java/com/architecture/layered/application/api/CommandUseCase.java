package com.architecture.layered.application.api;

import com.architecture.layered.domain.User;

public interface CommandUseCase {

    String createUser(User user);

    void updateUser(String id, User user);

    void deleteUser(String id);

}
