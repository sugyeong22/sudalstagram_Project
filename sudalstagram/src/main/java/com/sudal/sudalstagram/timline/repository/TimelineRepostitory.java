package com.sudal.sudalstagram.timline.repository;

import com.sudal.sudalstagram.timline.domain.Timeline;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelineRepostitory extends JpaRepository<Timeline, Long> {

    public List<Timeline> findByUserId(long userId, Sort sort);


}
