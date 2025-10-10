package com.sudal.sudalstagram.timline.service;

import com.sudal.sudalstagram.common.FileManager;
import com.sudal.sudalstagram.timline.domain.Timeline;
import com.sudal.sudalstagram.timline.dto.TimeDto;
import com.sudal.sudalstagram.timline.repository.TimelineRepostitory;
import com.sudal.sudalstagram.user.domain.User;
import com.sudal.sudalstagram.user.service.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimelineService {

    private final TimelineRepostitory timelineRepostitory;
    private final UserService userService;

    public TimelineService(TimelineRepostitory timelineRepostitory, UserService userService) {
        this.timelineRepostitory = timelineRepostitory;
        this.userService = userService;
    }

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
    public List<TimeDto> getAllPostList(){
        // 테이블에서 조회된 정보를 사용하기 편한 용도로 만들어서 리턴하는것이 서비스의 주 목적

        List<Timeline> timelineList =  timelineRepostitory.findAll(Sort.by("id").descending());

        List<TimeDto> timeDtoList = new ArrayList<>();
        // 전체를 재조립 해야하니까 반복문 사용
        for(Timeline time:timelineList){

           User user = userService.getUserById(time.getUserId());

           TimeDto timeDto = TimeDto.builder()
                   .id(time.getId())
                   .userId(time.getUserId())
                   .contents(time.getContents())
                   .loginId(user.getLoginId())
                   .imagePath(time.getImagePath())
                   .build();

           timeDtoList.add(timeDto);
       }

        return timeDtoList;


    }











}
