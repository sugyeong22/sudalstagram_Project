package com.sudal.sudalstagram.comment.service;

import com.sudal.sudalstagram.comment.domain.Comment;
import com.sudal.sudalstagram.comment.dto.CommentDto;
import com.sudal.sudalstagram.comment.repository.CommentRepository;
import com.sudal.sudalstagram.user.domain.User;
import com.sudal.sudalstagram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;


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

    public List<CommentDto> getCommentListByPostId(long postId){
        List<Comment> commentList = commentRepository.findByPostId(postId);

        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment:commentList){

            User user = userService.getUserById(comment.getUserId());

            CommentDto commentDto = CommentDto.builder()
                    .id(comment.getId())
                    .postId(comment.getPostId())
                    .userId(comment.getUserId())
                    .contents(comment.getContents())
                    .loginId(user.getLoginId())
                    .build();

            commentDtoList.add(commentDto);
        }

        return commentDtoList;
    }

    public void deleteByPostId(long postId){
        commentRepository.deleteByPostId(postId);
    }



}
