package com.architecture.layered.application.api;

import com.architecture.layered.application.api.command.CreateUserCommand;
import com.architecture.layered.application.api.command.UpdateUserCommand;

/**
 * Defines write operations on users.
 * All methods are transactional.
 */
public interface CommandUseCase {

    /**
     * Creates a new user and returns the generated ID.
     */
    String createUser(CreateUserCommand command);

    /**
     * Updates an existing user.
     */
    void updateUser(UpdateUserCommand command);

    /**
     * Deletes a user by ID.
     */
    void deleteUser(String id);
}
