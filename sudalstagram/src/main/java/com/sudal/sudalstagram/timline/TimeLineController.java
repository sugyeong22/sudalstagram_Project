package com.sudal.sudalstagram.timline;

import com.sudal.sudalstagram.timline.dto.TimeDto;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import com.sudal.sudalstagram.timline.domain.Timeline;
import com.sudal.sudalstagram.timline.service.TimelineService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/post")
@Controller
public class TimeLineController {

    private final TimelineService timelineService;

    public TimeLineController(TimelineService timelineService) {
        this.timelineService = timelineService;
    }


    // 사용자가 작성한 게시물 모음
    @GetMapping("/mypage")
    public String mypage(
            Model model
            , HttpSession session
    ){
        long userId = (Long) session.getAttribute("userId");
        List<Timeline> postList = timelineService.getPostList(userId);
        model.addAttribute("postList", postList);

        return "post/mypage";
    }

    @GetMapping("/timeline")
    public String timeline(
            Model model){
        List<TimeDto> postAllList = timelineService.getAllPostList();
        model.addAttribute("postAllList", postAllList);
        return "post/timeline";
    }

    @GetMapping("write")
    public String postForm(){
        return "post/write";
    }
}
