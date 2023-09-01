package com.tvanwinckel.RestClient.infrastructure.objects;

public record UserName(String firstName, String lastName) {

    public String fullName() {
        return firstName + " " + lastName;
    }
}
