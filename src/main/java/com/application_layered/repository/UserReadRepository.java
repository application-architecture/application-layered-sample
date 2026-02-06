package com.application_layered.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserReadRepository {

    Optional<UserView> findById(UUID id);

    List<UserView> findAll();

    List<UserView> findByNameStartingWith(String prefix);

}
