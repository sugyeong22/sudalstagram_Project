package com.sudal.sudalstagram.like.service;

import com.sudal.sudalstagram.like.domain.Like;
import com.sudal.sudalstagram.like.repository.LikeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public boolean like(long postId, long userId){

        Like like = Like.builder()
                .postId(postId)
                .userId(userId)
                .build();

        try{
            likeRepository.save(like);
        } catch (DataAccessException e){
            return false;
        }

        return true;
    }

    public int getLikeContByPostId(long postId){
        return likeRepository.countByPostId(postId);
    }

    public boolean isLikeByPostIdAndUserId(long postId, long userId){
        return likeRepository.existsByPostIdAndUserId(postId, userId);
    }



}
