package com.sudal.sudalstagram.like.repository;

import com.sudal.sudalstagram.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {


}
