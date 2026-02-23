package com.architecture.layered.integration.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/test-schema.sql", "/test-data.sql"})
class RestQueryIntegrationTest {

    @Autowired MockMvc mvc;

    // findById — happy path
    @Test
    void shouldFindById() throws Exception {
        mvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.birthDate").value("1990-01-01"));
    }

    // findById — не найден
    @Test
    void shouldReturn404WhenUserNotFound() throws Exception {
        mvc.perform(get("/api/users/99"))
                .andExpect(status().isNotFound());
    }

    // findAll — возвращает всех отсортированных по id
    @Test
    void shouldReturnAllUsers() throws Exception {
        mvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[2].id").value("3"));
    }

    // findByPrefix — находит совпадения без учёта регистра
    @Test
    void shouldFindByPrefix() throws Exception {
        mvc.perform(get("/api/users").param("namePrefix", "A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Anna"));
    }

    // findByPrefix — регистронезависимость
    @Test
    void shouldFindByPrefixCaseInsensitive() throws Exception {
        mvc.perform(get("/api/users").param("namePrefix", "a"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    // findByPrefix — нет совпадений
    @Test
    void shouldReturnEmptyListWhenNoPrefixMatch() throws Exception {
        mvc.perform(get("/api/users").param("namePrefix", "ZZZ"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // findAll без параметра и с пустым префиксом — одинаковый результат
    @Test
    void shouldReturnAllUsersWhenPrefixIsEmpty() throws Exception {
        mvc.perform(get("/api/users").param("namePrefix", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }
}
