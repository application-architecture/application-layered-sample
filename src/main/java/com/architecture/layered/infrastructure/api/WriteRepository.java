package com.architecture.layered.infrastructure.api;

import com.architecture.layered.domain.User;

/**
 * Port for writing users to the data store.
 * Implementations are provided per infrastructure profile: jdbc, jooq, jpa.
 */
public interface WriteRepository {

    /**
     * Persists a new user.
     */
    void save(User user);

    /**
     * Updates an existing user.
     * Throws {@link com.architecture.layered.domain.exception.UserNotFoundException}
     * if no user exists with the given ID.
     */
    void update(User user);

    /**
     * Deletes a user by ID.
     * Throws {@link com.architecture.layered.domain.exception.UserNotFoundException}
     * if no user exists with the given ID.
     */
    void deleteById(String id);
}
