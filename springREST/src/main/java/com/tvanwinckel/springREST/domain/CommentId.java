package com.tvanwinckel.springREST.domain;

import java.util.Random;

public record CommentId(long value) {
    public static CommentId create() {
        final Random random = new Random();
        return new CommentId(random.nextLong());
    }
}
