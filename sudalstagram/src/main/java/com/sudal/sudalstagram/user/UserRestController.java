package com.sudal.sudalstagram.user;

import com.sudal.sudalstagram.user.domain.User;
import com.sudal.sudalstagram.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 API
    @PostMapping("/join-process")
    public Map<String, String> join(
        @RequestParam String loginId
        , @RequestParam String password
        , @RequestParam String name
        , @RequestParam String email
    ){

        Map<String, String> resultMap = new HashMap<>();

        if(userService.createUser(loginId, password, name, email)){
            resultMap.put("result","success");
        }else{
            resultMap.put("result","fail");
        }
        return resultMap;
    }

    // 중복확인 API
    @GetMapping("/duplicate-id")
    public Map<String, Boolean> isDulicatiedId(@RequestParam String loginId){

        Map<String, Boolean> resultMap = new HashMap<>();

        if(userService.isDuplicateId(loginId)){
            resultMap.put("isDuplicate",true);
        } else{
            resultMap.put("isDuplicate",false);
        }
        return resultMap;
    }

    // 로그인 API
    @PostMapping("/login-process")
    public Map<String, String> login(
            @RequestParam String loginId
            , @RequestParam String password
    ){
        User user = userService.getUser(loginId, password);

        Map<String, String> resultMap = new HashMap<>();

        if(user != null){
            resultMap.put("result","success");
        } else {
            resultMap.put("result","fail");
        }

        return resultMap;
    };






}

