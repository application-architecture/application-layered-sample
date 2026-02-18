package com.architecture.layered.infrastructure.api;

import com.architecture.layered.domain.User;

public interface WriteRepository {

    void save(User user);
    void update(String id, User user);
    void deleteById(String id);

}
