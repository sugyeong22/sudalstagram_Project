package com.sudal.sudalstagram.comment.repository;

import com.sudal.sudalstagram.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //WHERE 'postId`  = #{}
    public List<Comment> findByPostId(long postId);
}
