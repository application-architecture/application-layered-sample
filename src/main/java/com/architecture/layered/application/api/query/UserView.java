package com.architecture.layered.application.api.query;

import java.time.LocalDate;

public record UserView(String id, String name, LocalDate birthDate) {}
