package com.tvanwinckel.springREST.domain;

import com.tvanwinckel.springREST.application.requests.UserRequest;

public record User(UserId id, UserName userName) {


    public static User of(final UserRequest request) {
        final UserName name = new UserName(request.firstName(), request.lastName());
        final UserId userId = UserId.create();
        return new User(userId, name);
    }
}
