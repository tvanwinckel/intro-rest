package com.tvanwinckel.springREST.infrastructure.repository;

import com.tvanwinckel.springREST.domain.Comment;
import com.tvanwinckel.springREST.domain.CommentId;
import com.tvanwinckel.springREST.domain.CommentNotFoundException;
import com.tvanwinckel.springREST.domain.CommentRepository;
import com.tvanwinckel.springREST.domain.UserId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tvanwinckel.springREST.infrastructure.repository.InMemoryPostRepository.POST_ID_OF_POST_WITH_COMMENTS;
import static com.tvanwinckel.springREST.infrastructure.repository.InMemoryUserRepository.FIFTH_USER_ID;
import static com.tvanwinckel.springREST.infrastructure.repository.InMemoryUserRepository.FIRST_USER_ID;
import static com.tvanwinckel.springREST.infrastructure.repository.InMemoryUserRepository.FOURTH_USER_ID;

@Repository
public class InMemoryCommentRepository implements CommentRepository {

    public static final CommentId FIRST_COMMENT_ID = CommentId.create();
    public static final CommentId SECOND_COMMENT_ID = CommentId.create();
    public static final CommentId THIRD_COMMENT_ID = CommentId.create();
    private final List<Comment> comments = new ArrayList<>();

    public InMemoryCommentRepository() {
        init();
    }

    @Override
    public List<Comment> getAll() {
        return comments;
    }

    @Override
    public Comment getById(final CommentId commentId) {
        final Optional<Comment> comment = comments.stream().filter(c -> c.id().equals(commentId)).findFirst();
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new CommentNotFoundException();
        }
    }

    @Override
    public List<Comment> getByUser(final UserId userId) {
        return comments.stream().filter(c -> c.postedBy().equals(userId)).toList();
    }

    @Override
    public void add(final Comment comment) {
        comments.add(comment);
    }

    @Override
    public void delete(final CommentId commentId) {
        final Comment comment = getById(commentId);
        comments.remove(comment);
    }

    private void init() {
        final Comment first = new Comment(FIRST_COMMENT_ID, "First!", FIFTH_USER_ID, POST_ID_OF_POST_WITH_COMMENTS);
        final Comment second = new Comment(SECOND_COMMENT_ID, "Good post, keep it up.", FIRST_USER_ID, POST_ID_OF_POST_WITH_COMMENTS);
        final Comment third = new Comment(THIRD_COMMENT_ID, "What a bunch of nonsense", FOURTH_USER_ID, POST_ID_OF_POST_WITH_COMMENTS);

        comments.add(first);
        comments.add(second);
        comments.add(third);
    }
}
