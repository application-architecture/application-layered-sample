package com.application_layered.domain;

import java.util.UUID;
import java.time.LocalDate;

public record User(UUID id, String name, LocalDate birthDate){

    public static User create(String name, LocalDate birthDate) {
        return new User(UUID.randomUUID(), name, birthDate);
    }

    public static User restore(UUID id, String name, LocalDate birthDate) {
        return new User(id, name, birthDate);
    }

}

