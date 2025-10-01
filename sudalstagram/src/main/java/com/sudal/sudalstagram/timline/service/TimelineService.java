package com.sudal.sudalstagram.timline.service;

import com.sudal.sudalstagram.timline.domain.Timeline;
import com.sudal.sudalstagram.timline.repository.TimelineRepostitory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimelineService {

    private final TimelineRepostitory timelineRepostitory;

    public TimelineService(TimelineRepostitory timelineRepostitory) {
        this.timelineRepostitory = timelineRepostitory;
    }

    // 게시글 저장 API
    public boolean createtimeline(long userId, String contents){
        Timeline timeline = Timeline.builder()
                .userId(userId)
                .contents(contents)
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
    public List<Timeline> getAllPostList(){
       return timelineRepostitory.findAll(Sort.by("id").descending());
    }








}
