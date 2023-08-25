package com.tvanwinckel.springREST.application;

import com.tvanwinckel.springREST.application.requests.CommentRequest;
import com.tvanwinckel.springREST.domain.Comment;
import com.tvanwinckel.springREST.domain.CommentId;
import com.tvanwinckel.springREST.domain.CommentRepository;
import com.tvanwinckel.springREST.domain.UserId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(final CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAll() {
        return commentRepository.getAll();
    }

    public Comment getById(final CommentId commentId) {
        return commentRepository.getById(commentId);
    }

    public List<Comment> getAllMadeBy(final UserId userId) {
        return commentRepository.getByUser(userId);
    }

    public Comment create(final CommentRequest request) {
        final Comment comment = Comment.of(request);
        commentRepository.add(comment);
        return comment;
    }

    public void delete(final CommentId commentId) {
        commentRepository.delete(commentId);
    }
}
