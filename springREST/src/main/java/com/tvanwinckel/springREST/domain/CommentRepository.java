package com.tvanwinckel.springREST.domain;

import java.util.List;

public interface CommentRepository {

    List<Comment> getAll();

    Comment getById(CommentId commentId);

    List<Comment> getByUser(UserId userId);

    void add(Comment comment);

    void delete(CommentId commentId);
}
