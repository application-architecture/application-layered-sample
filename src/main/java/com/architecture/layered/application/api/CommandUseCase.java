package com.architecture.layered.application.api;

import com.architecture.layered.application.api.command.CreateUserCommand;
import com.architecture.layered.application.api.command.UpdateUserCommand;

public interface CommandUseCase {
    String createUser(CreateUserCommand command);
    void updateUser(UpdateUserCommand command);
    void deleteUser(String id);
}
