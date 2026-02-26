package com.architecture.layered.application.api;

import com.architecture.layered.application.api.query.UserView;

import java.util.List;

/**
 * Defines read operations on users.
 * All methods are executed within a read-only transaction.
 */
public interface QueryUseCase {

    /**
     * Returns a user by ID. Throws {@link com.architecture.layered.domain.exception.UserNotFoundException}
     * if no user exists with the given ID.
     */
    UserView findById(String id);

    /**
     * Returns all users ordered by ID.
     */
    List<UserView> findAll();

    /**
     * Returns users whose name starts with the given prefix, case-insensitive, ordered by name.
     */
    List<UserView> findByNameStartingWith(String prefix);
}