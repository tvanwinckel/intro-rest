package com.tvanwinckel.springREST.domain;

import com.tvanwinckel.springREST.application.requests.PostRequest;

import java.util.List;

public record Post(PostId id, String title, String body, List<CommentId> comments, UserId postedBy) {

    public static Post of(final PostRequest request) {
        final PostId id = PostId.create();
        return new Post(id, request.title(), request.body(), List.of(), request.userId());
    }
}
