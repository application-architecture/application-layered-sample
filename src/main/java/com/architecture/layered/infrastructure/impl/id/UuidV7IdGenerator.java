package com.architecture.layered.infrastructure.impl.id;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;
import com.architecture.layered.infrastructure.api.IdGenerator;

/**
 * Package-private.
 */
final class UuidV7IdGenerator implements IdGenerator {

    private static final
        TimeBasedEpochGenerator GENERATOR =
            Generators.timeBasedEpochGenerator();


    @Override
    public String nextId() {
        return GENERATOR.generate().toString();
    }
}
