package com.tvanwinckel.springREST.domain;

import java.util.Random;

public record PostId(long value) {

    public static PostId create() {
        final Random random = new Random();
        return new PostId(random.nextLong());
    }
}
