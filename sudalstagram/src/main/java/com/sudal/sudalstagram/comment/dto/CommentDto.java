package com.sudal.sudalstagram.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDto {

    private long id;

    private long postId;
    private long userId;

    private String loginId;
    private String contents;

}
