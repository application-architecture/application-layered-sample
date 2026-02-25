package com.architecture.layered.application.api.command;

import java.time.LocalDate;

public record CreateUserCommand(String name, LocalDate birthDate) {}
