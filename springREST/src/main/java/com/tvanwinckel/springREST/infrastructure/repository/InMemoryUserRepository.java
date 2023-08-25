package com.tvanwinckel.springREST.infrastructure.repository;

import com.tvanwinckel.springREST.domain.User;
import com.tvanwinckel.springREST.domain.UserId;
import com.tvanwinckel.springREST.domain.UserName;
import com.tvanwinckel.springREST.domain.UserNotFoundException;
import com.tvanwinckel.springREST.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository {

    public static final UserId FIRST_USER_ID = UserId.create();
    public static final UserId SECOND_USER_ID = UserId.create();
    public static final UserId THIRD_USER_ID = UserId.create();
    public static final UserId FOURTH_USER_ID = UserId.create();
    public static final UserId FIFTH_USER_ID = UserId.create();
    private final List<User> users = new ArrayList<>();

    public InMemoryUserRepository() {
        init();
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(final UserId userId) {
        final Optional<User> user = users.stream().filter(u -> u.id().equals(userId)).findFirst();
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void add(final User user) {
          users.add(user);
    }

    @Override
    public void delete(final UserId userId) {
        final User user = getById(userId);
        users.remove(user);
    }

    private void init() {
        final User first = new User(FIRST_USER_ID, new UserName("Lucy", "Carlyle"));
        final User second = new User(SECOND_USER_ID, new UserName("George", "Cubbins"));
        final User third = new User(THIRD_USER_ID, new UserName("Anthony", "Lockwood"));
        final User fourth = new User(FOURTH_USER_ID, new UserName("Marissa", "Fittes"));
        final User fifth = new User(FIFTH_USER_ID, new UserName("Quill", "Kipps"));

        users.add(first);
        users.add(second);
        users.add(third);
        users.add(fourth);
        users.add(fifth);
    }
}
