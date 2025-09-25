package com.sudal.sudalstagram.user.service;

import com.sudal.sudalstagram.common.SHAHashingEncoder;
import com.sudal.sudalstagram.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    //@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public boolean createUser(
            String loginId
            , String password
            , String name
            , String email
    ){
        String encodedpassword = SHAHashingEncoder.encode(password);

        int count = userRepository.insertUser(loginId,encodedpassword,name,email);

        if(count == 1){
            return true;
        } else {
            return false;
        }
    }

    // 중복확인
    public boolean isDuplicateId(String loginId){
        int count = userRepository.selectCountByLoginId(loginId);

        if(count == 0 ){
            return false;
        } else{
            return true;
        }
    }

}
