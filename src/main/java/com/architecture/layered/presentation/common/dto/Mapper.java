package com.architecture.layered.presentation.common.dto;

import com.architecture.layered.domain.User;

import java.time.LocalDate;

public final class Mapper {

    public static User toDomain(Request request) {
        return User.withoutId(request.name(), LocalDate.parse(request.birthDate()));
    }

    public static Response toResponse(User user) {
        return new Response(user.id(), user.name(), user.birthDate());
    }

}
