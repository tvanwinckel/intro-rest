package com.tvanwinckel.springREST.application;

import com.tvanwinckel.springREST.application.requests.PostRequest;
import com.tvanwinckel.springREST.domain.Post;
import com.tvanwinckel.springREST.domain.PostId;
import com.tvanwinckel.springREST.domain.PostRepository;
import com.tvanwinckel.springREST.domain.UserId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }

    public Post getById(final PostId postId) {
        return postRepository.getById(postId);
    }

    public List<Post> getAllPostsOfUser(final UserId userId) {
        return postRepository.getAllPostOfUser(userId);
    }

    public Post create(final PostRequest request) {
        final Post newPost = Post.of(request);
        postRepository.add(newPost);
        return newPost;
    }

    public void delete(final PostId postId) {
        postRepository.delete(postId);
    }
}
