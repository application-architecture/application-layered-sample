package com.architecture.layered.infrastructure.impl.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, String> {
    List<UserJpaEntity> findAllByOrderById();
    List<UserJpaEntity> findByNameStartingWithIgnoreCaseOrderByName(String prefix);
}
