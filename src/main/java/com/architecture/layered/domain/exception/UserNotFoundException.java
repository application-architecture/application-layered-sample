package com.architecture.layered.domain.exception;

/**
 * Thrown when a requested user does not exist in the data store.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User not found: " + id);
    }
}
