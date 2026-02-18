package com.architecture.layered.presentation.common.dto;

import java.time.LocalDate;

public record Response(String id, String name, LocalDate birthDate) {}
