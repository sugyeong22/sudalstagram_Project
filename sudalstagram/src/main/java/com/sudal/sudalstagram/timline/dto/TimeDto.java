package com.sudal.sudalstagram.timline.dto;

import com.sudal.sudalstagram.comment.domain.Comment;
import com.sudal.sudalstagram.comment.dto.CommentDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
public class TimeDto {

    // 관련된 프라이머리키
    private long id; //게시글
    private long userId; // 작성자

    private String loginId; // 작성자
    private String contents; // 게시글 내용

    private String imagePath;

    private int likeCount;
    private boolean isLike;

    private List<CommentDto> commentList;




}
