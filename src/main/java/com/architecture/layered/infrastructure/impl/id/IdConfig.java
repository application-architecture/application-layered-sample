package com.architecture.layered.infrastructure.impl.id;

import com.architecture.layered.infrastructure.api.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class IdConfig {

    @Bean
    IdGenerator idGenerator() {
        return new SequentialIdGenerator();
    }
}
