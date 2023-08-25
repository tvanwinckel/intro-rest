package com.tvanwinckel.springREST.application.requests;

import com.tvanwinckel.springREST.domain.UserId;

public record PostRequest(String title, String body, UserId userId) {

}
