package com.sudal.sudalstagram.timline.service;

import com.sudal.sudalstagram.comment.domain.Comment;
import com.sudal.sudalstagram.comment.dto.CommentDto;
import com.sudal.sudalstagram.comment.service.CommentService;
import com.sudal.sudalstagram.common.FileManager;
import com.sudal.sudalstagram.like.service.LikeService;
import com.sudal.sudalstagram.timline.domain.Timeline;
import com.sudal.sudalstagram.timline.dto.TimeDto;
import com.sudal.sudalstagram.timline.repository.TimelineRepostitory;
import com.sudal.sudalstagram.user.domain.User;
import com.sudal.sudalstagram.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

// 필수 멤버변수에 대한 초기화 생성자
// final 이 붙은것들만 생성자로 만듬
@RequiredArgsConstructor
@Service
public class TimelineService {

    private final TimelineRepostitory timelineRepostitory;
    private final UserService userService;
    private final LikeService likeService;
    private final CommentService commentService;

//    public TimelineService(
//            TimelineRepostitory timelineRepostitory
//            , UserService userService
//            , LikeService likeService
//            , CommentService commentService) {
//        this.timelineRepostitory = timelineRepostitory;
//        this.userService = userService;
//        this.likeService = likeService;
//        this.commentService = commentService;
//    }

    // 게시글 저장 API
    public boolean createtimeline(
            long userId
            , String contents
            , MultipartFile file){

        String imagePath = FileManager.saveFile(userId, file);

        Timeline timeline = Timeline.builder()
                .userId(userId)
                .contents(contents)
                .imagePath(imagePath)
                .build();

        try{
            timelineRepostitory.save(timeline);
        } catch(DataAccessException e){
            return false;
        }
        return true;
    }

    // 사용자의 게시글 리스트 조회
    public List<Timeline> getPostList(long userId){
        return timelineRepostitory.findByUserId(userId, Sort.by("id").descending());
    }

    // 전체 게시글 리스트 조회
    public List<TimeDto> getAllPostList(long userId){
        // 테이블에서 조회된 정보를 사용하기 편한 용도로 만들어서 리턴하는것이 서비스의 주 목적

        List<Timeline> timelineList =  timelineRepostitory.findAll(Sort.by("id").descending());

        List<TimeDto> timeDtoList = new ArrayList<>();
        // 전체를 재조립 해야하니까 반복문 사용
        for(Timeline time:timelineList){

           User user = userService.getUserById(time.getUserId());

            int likeCont = likeService.getLikeContByPostId(time.getId());
            boolean isLike = likeService.isLikeByPostIdAndUserId(time.getId(), userId);

            List<CommentDto> commnetList = commentService.getCommentListByPostId(time.getId());

            TimeDto timeDto = TimeDto.builder()
                   .id(time.getId())
                   .userId(time.getUserId())
                   .contents(time.getContents())
                   .loginId(user.getLoginId())
                   .imagePath(time.getImagePath())
                    .likeCount(likeCont)
                    .commentList(commnetList)
                    .isLike(isLike)
                   .build();

           timeDtoList.add(timeDto);
       }

        return timeDtoList;


    }











}
