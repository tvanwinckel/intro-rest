package com.tvanwinckel.springREST.domain;

import java.util.List;

public interface PostRepository {

    List<Post> getAll();

    Post getById(PostId postId);

    List<Post> getAllPostOfUser(UserId userId);

    void add(Post post);

    void delete(PostId postId);
}
