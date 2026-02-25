package com.architecture.layered.infrastructure.api;

import com.architecture.layered.domain.User;

public interface WriteRepository {

    void save(User user);
    void update(User user);
    void deleteById(String id);

}
