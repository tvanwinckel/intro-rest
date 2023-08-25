package com.tvanwinckel.springREST.infrastructure.repository;

import com.tvanwinckel.springREST.domain.Post;
import com.tvanwinckel.springREST.domain.PostId;
import com.tvanwinckel.springREST.domain.PostNotFoundException;
import com.tvanwinckel.springREST.domain.PostRepository;
import com.tvanwinckel.springREST.domain.UserId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tvanwinckel.springREST.infrastructure.repository.InMemoryCommentRepository.FIRST_COMMENT_ID;
import static com.tvanwinckel.springREST.infrastructure.repository.InMemoryCommentRepository.SECOND_COMMENT_ID;
import static com.tvanwinckel.springREST.infrastructure.repository.InMemoryCommentRepository.THIRD_COMMENT_ID;
import static com.tvanwinckel.springREST.infrastructure.repository.InMemoryUserRepository.FIRST_USER_ID;
import static com.tvanwinckel.springREST.infrastructure.repository.InMemoryUserRepository.SECOND_USER_ID;
import static com.tvanwinckel.springREST.infrastructure.repository.InMemoryUserRepository.THIRD_USER_ID;

@Repository
public class InMemoryPostRepository implements PostRepository {

    public static final PostId EMPTY_POST_ID = PostId.create();
    public static final PostId NO_COMMENT_POST_ID = PostId.create();
    public static final PostId POST_ID_OF_POST_WITH_COMMENTS = PostId.create();
    final List<Post> posts = new ArrayList<>();

    public InMemoryPostRepository() {
        init();
    }

    @Override
    public List<Post> getAll() {
        return posts;
    }

    @Override
    public Post getById(final PostId postId) {
        final Optional<Post> post = posts.stream().filter(p -> p.id().equals(postId)).findFirst();
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new PostNotFoundException();
        }
    }

    @Override
    public List<Post> getAllPostOfUser(final UserId userId) {
        return posts.stream().filter(post -> post.postedBy().equals(userId)).toList();
    }

    @Override
    public void add(final Post post) {
        posts.add(post);
    }

    @Override
    public void delete(final PostId postId) {
        final Post post = getById(postId);
        posts.remove(post);
    }

    private void init() {
        final Post emptyPost = new Post(EMPTY_POST_ID, "Empty Post", "", List.of(), FIRST_USER_ID);
        final Post noComments = new Post(NO_COMMENT_POST_ID, "No Comments", "This is a post with a title and a body. But nobody has commented on it yet.", List.of(), SECOND_USER_ID);
        final Post postWithComment = new Post(POST_ID_OF_POST_WITH_COMMENTS, "Post With Comments", "This is a post with a title, a body and comments.", List.of(FIRST_COMMENT_ID, SECOND_COMMENT_ID, THIRD_COMMENT_ID), THIRD_USER_ID);

        posts.add(emptyPost);
        posts.add(noComments);
        posts.add(postWithComment);
    }
}
