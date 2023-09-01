package com.tvanwinckel.springREST.infrastructure.controller;

import com.tvanwinckel.springREST.application.UserService;
import com.tvanwinckel.springREST.application.requests.UserRequest;
import com.tvanwinckel.springREST.domain.User;
import com.tvanwinckel.springREST.domain.UserId;
import com.tvanwinckel.springREST.domain.UserNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Cacheable("users")
    public ResponseEntity<List<User>> getAll() {
        final List<User> users = userService.getAll();

        final CacheControl cacheControl = CacheControl
                .maxAge(5, TimeUnit.SECONDS)
                .mustRevalidate();

        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ALLOW, "GET");
        headers.add(HttpHeaders.ALLOW, "POST");
        headers.add(HttpHeaders.ALLOW, "DELETE");

        return ResponseEntity.ok().cacheControl(cacheControl).headers(headers).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") final long id) {
        final UserId userId = new UserId(id);
        final User user = userService.getById(userId);

        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ALLOW, "GET");

        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> add(@RequestBody UserRequest userRequest) {
        final User createdUser = userService.create(userRequest);

        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ALLOW, "POST");

        return new ResponseEntity<>(createdUser, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final long id) {
        final UserId userId = new UserId(id);
        userService.delete(userId);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleUserNotFound() {
        return "404 - User not found";
    }
}
