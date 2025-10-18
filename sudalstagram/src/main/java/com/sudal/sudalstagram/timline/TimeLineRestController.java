package com.sudal.sudalstagram.timline;

import com.sudal.sudalstagram.timline.service.TimelineService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            , @RequestParam MultipartFile imageFile
            , HttpSession session
    ){
        long userid = (Long) session.getAttribute("userId");


        Map<String, String> resultMap = new HashMap<>();

        if(timelineService.createtimeline(userid,contents,imageFile)){
            resultMap.put("result","success");
        } else{
            resultMap.put("result","fail");
        }
        return resultMap;
    }

    @DeleteMapping("/remove")
    public Map<String, String> removePost(
            @RequestParam long id
            , HttpSession session
    ){
        Map<String, String> resultMap = new HashMap<>();

        long userId = (long)session.getAttribute("userId");

        if(timelineService.deletePost(id, userId)){
            resultMap.put("result","success");
        }else{
            resultMap.put("result","fail");
        }

        return resultMap;
    }








}
