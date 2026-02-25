// @formatter:off
package com.architecture.layered.presentation.common.dto;

import com.architecture.layered.application.api.command.CreateUserCommand;import com.architecture.layered.application.api.command.UpdateUserCommand;import com.architecture.layered.application.api.query.UserView;

import java.time.LocalDate;

public final class Mapper {

    public static CreateUserCommand toCreateCommand(CreateUserRequest request) {
        return new CreateUserCommand(
                request.name(),
                LocalDate.parse(request.birthDate())
        );
    }

    public static UpdateUserCommand toUpdateCommand(UpdateUserRequest request) {
        return new UpdateUserCommand(
                request.id(),
                request.name(),
                LocalDate.parse(request.birthDate())
        );
    }

    public static Response toResponse(UserView view) {
        return new Response(view.id(), view.name(), view.birthDate());
    }

}
