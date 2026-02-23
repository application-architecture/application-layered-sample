package com.architecture.layered.e2e.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.test.context.jdbc.Sql;

@AutoConfigureRestTestClient
@Sql({"/test-schema.sql", "/test-data.sql"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestQueryControllerE2ETest {

    @Autowired
    private RestTestClient client;

    @Test
    void shouldFindById() {
        client.get().uri("/api/users/" + 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Alice")
                .jsonPath("$.birthDate").isEqualTo("1990-01-01");
    }

    @Test
    void shouldReturn404ForUnknownUser() {
        client.get().uri("/api/users/99")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.detail").isEqualTo("User not found: 99");
    }

    @Test
    void shouldReturnAllUsers() {
        client.get().uri("/api/users")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(3)
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo("Alice")
                .jsonPath("$[0].birthDate").isEqualTo("1990-01-01")
                .jsonPath("$[1].id").isEqualTo(2)
                .jsonPath("$[1].name").isEqualTo("Bob")
                .jsonPath("$[1].birthDate").isEqualTo("1985-05-20")
                .jsonPath("$[2].id").isEqualTo(3)
                .jsonPath("$[2].name").isEqualTo("Anna")
                .jsonPath("$[2].birthDate").isEqualTo("2000-03-15");
    }

    @Test
    void shouldFindByPrefix() {
        client.get().uri("/api/users?namePrefix=An")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].id").isEqualTo(3)
                .jsonPath("$[0].name").isEqualTo("Anna")
                .jsonPath("$[0].birthDate").isEqualTo("2000-03-15");
    }

}
