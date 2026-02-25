package com.architecture.layered.application.api.command;

import java.time.LocalDate;

public record UpdateUserCommand(String id, String name, LocalDate birthDate) {}
