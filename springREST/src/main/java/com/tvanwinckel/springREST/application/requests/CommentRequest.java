package com.tvanwinckel.springREST.application.requests;

import com.tvanwinckel.springREST.domain.PostId;
import com.tvanwinckel.springREST.domain.UserId;

public record CommentRequest(String body, UserId postedBy, PostId commentedOn) {
}
