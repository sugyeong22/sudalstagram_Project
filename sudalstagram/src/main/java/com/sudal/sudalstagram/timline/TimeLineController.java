package com.sudal.sudalstagram.timline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class TimeLineController {

    @GetMapping("/timeline")
    public String timeline(){
        return "post/timeline";
    }

    @GetMapping("write")
    public String postForm(){
        return "post/write";
    }
}
