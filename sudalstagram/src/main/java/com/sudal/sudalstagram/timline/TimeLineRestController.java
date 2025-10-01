package com.sudal.sudalstagram.timline;

import com.sudal.sudalstagram.timline.service.TimelineService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/post")
@RestController
public class TimeLineRestController {

    private final TimelineService timelineService;

    public TimeLineRestController(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    // DB에 게시글 작성하여 저장 API
    @PostMapping("/write-process")
    public Map<String, String> writePost(
            @RequestParam String contents
            , HttpSession session
    ){
        long userid = (Long) session.getAttribute("userId");


        Map<String, String> resultMap = new HashMap<>();

        if(timelineService.createtimeline(userid,contents)){
            resultMap.put("result","success");
        } else{
            resultMap.put("result","fail");
        }
        return resultMap;
    }
}
