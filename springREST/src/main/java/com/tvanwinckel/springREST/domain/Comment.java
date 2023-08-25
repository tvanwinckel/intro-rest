package com.tvanwinckel.springREST.domain;

import com.tvanwinckel.springREST.application.requests.CommentRequest;

public record Comment(CommentId id, String body, UserId postedBy, PostId commentedOn) {

    public static Comment of(final CommentRequest request) {
        final CommentId commentId = CommentId.create();
        return new Comment(commentId, request.body(), request.postedBy(), request.commentedOn());
    }
}
