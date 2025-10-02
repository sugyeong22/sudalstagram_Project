package com.sudal.sudalstagram.timline.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class TimeDto {

    // 프라이머리키
    private long id; //게시글
    private long userId; // 작성자

    private String loginId;
    private String contents;



}
