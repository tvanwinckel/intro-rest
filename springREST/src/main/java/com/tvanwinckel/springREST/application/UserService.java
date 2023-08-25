package com.tvanwinckel.springREST.application;

import com.tvanwinckel.springREST.application.requests.UserRequest;
import com.tvanwinckel.springREST.domain.User;
import com.tvanwinckel.springREST.domain.UserId;
import com.tvanwinckel.springREST.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User getById(final UserId userId) {
        return userRepository.getById(userId);
    }

    public User create(final UserRequest request) {
        final User newUser = User.of(request);
        userRepository.add(newUser);
        return newUser;
    }

    public void delete(final UserId userId) {
        userRepository.delete(userId);
    }
}
