package com.tvanwinckel.springREST.domain;

public record UserName(String firstName, String lastName) {

    public String fullName() {
        return firstName + " " + lastName;
    }
}
