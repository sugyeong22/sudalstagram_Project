package com.sudal.sudalstagram.comment.service;

import com.sudal.sudalstagram.comment.domain.Comment;
import com.sudal.sudalstagram.comment.repository.CommentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public boolean createComment(long postId, long userId, String contents){

        Comment comment = Comment.builder()
                .postId(postId)
                .userId(userId)
                .contents(contents)
                .build();

        try {
            commentRepository.save(comment);
        }catch (DataAccessException e){
            return false;
        }

        return true;


    }
}
