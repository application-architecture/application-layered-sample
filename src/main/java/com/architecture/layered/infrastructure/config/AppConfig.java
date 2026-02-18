package com.architecture.layered.infrastructure.config;

import com.architecture.layered.infrastructure.impl.id.SequentialIdGenerator;
import com.architecture.layered.infrastructure.impl.jdbc.JdbcReadRepository;
import com.architecture.layered.infrastructure.impl.jdbc.JdbcWriteRepository;
import com.architecture.layered.infrastructure.impl.jooq.JooqReadRepository;
import com.architecture.layered.infrastructure.impl.jooq.JooqWriteRepository;
import com.architecture.layered.application.impl.UserCommandService;
import com.architecture.layered.application.impl.UserQueryService;
import com.architecture.layered.application.api.CommandUseCase;
import com.architecture.layered.application.api.QueryUseCase;
import com.architecture.layered.infrastructure.api.IdGenerator;
import com.architecture.layered.infrastructure.api.ReadRepository;
import com.architecture.layered.infrastructure.api.WriteRepository;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
public class AppConfig {

    @Bean
    @Profile("jdbc")
    WriteRepository jdbcWriteRepository(JdbcClient jdbc) {
        return new JdbcWriteRepository(jdbc);
    }

    @Bean
    @Profile("jooq")
    WriteRepository jooqWriteRepository(DSLContext dsl) {
        return new JooqWriteRepository(dsl);
    }

    @Bean
    @Profile("jdbc")
    ReadRepository jdbcReadRepository(JdbcClient jdbc) {
        return new JdbcReadRepository(jdbc);
    }

    @Bean
    @Profile("jooq")
    ReadRepository jooqReadRepository(DSLContext dsl) {
        return new JooqReadRepository(dsl);
    }

    @Bean
    IdGenerator idGenerator() {
        return new SequentialIdGenerator();
    }

    @Bean
    QueryUseCase queryService(ReadRepository readRepository) {
        return new UserQueryService(readRepository);
    }

    @Bean
    CommandUseCase commandService(
            IdGenerator idGenerator,
            WriteRepository writeRepository
    ) {
        return new UserCommandService(idGenerator, writeRepository);
    }

}

