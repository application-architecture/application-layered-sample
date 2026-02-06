package com.application_layered.repository;

import java.util.UUID;
import java.time.LocalDate;

public record UserView(UUID id, String name, LocalDate birthDate) {}
