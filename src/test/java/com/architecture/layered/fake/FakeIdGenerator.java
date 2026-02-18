package com.architecture.layered.fake;

import com.architecture.layered.infrastructure.api.IdGenerator;

public class FakeIdGenerator implements IdGenerator {
    @Override
    public String nextId() {
        return "42"; // фиксированный ID для предсказуемого теста
    }
}
