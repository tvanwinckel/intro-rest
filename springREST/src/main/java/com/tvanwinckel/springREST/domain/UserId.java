package com.tvanwinckel.springREST.domain;

import java.util.Random;

public record UserId(long value) {
    public static UserId create() {
        final Random random = new Random();
        return new UserId(random.nextLong());
    }
}
