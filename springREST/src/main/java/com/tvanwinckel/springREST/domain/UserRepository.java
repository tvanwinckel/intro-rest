package com.tvanwinckel.springREST.domain;

import java.util.List;

public interface UserRepository {

    List<User> getAll();

    User getById(UserId userId);

    void add(User user);

    void delete(UserId userId);
}
