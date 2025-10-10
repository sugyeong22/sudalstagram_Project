package com.sudal.sudalstagram.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public static final String FILE_UPLOAD_PATH ="C:\\Users\\오수경\\Desktop\\코딩자료\\mega_study\\springProject\\upload\\sudalStagram";

    public static String saveFile(long userId, MultipartFile file){

        if(file == null){
            return null;
        }

        // 디렉토리(폴더) 를 만들어서 파일 저장
        // 디렉토리 이름 : 사용자 정보 + 시간 정보 (ex) 2_343556788
        // UNIX TIME : 1970년 1월 1일 0시 0분 0초 이후로 흐른 시간 (millisecond 단위)

        String directoryName = "/" + userId + "_" + System.currentTimeMillis();

        // 디렉토리 만들기
        // 전체 디렉토리 경로
        String directoryPath = FILE_UPLOAD_PATH + directoryName;

        File directory = new File(directoryPath);

        if(!directory.mkdir()){
            // 디렉토리 생성 실패
            return null;
        }

        String filePath = directoryPath + "/" + file.getOriginalFilename();

        try {
            // 실제 파일 내용 다루는것임
            byte[] bytes = file.getBytes();
            // 경로를 다루는 객체로 변환
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

        } catch (IOException e) {
            return null;
        }

        //C:\Users\오수경\Desktop\코딩자료\mega_study\springProject\\upload\memo/2_343556788/test.png
        //  /images/2_343556788/test.png

        // 클라이언트가 접근 할 수 있는 url 을 return
        return "/images" + directoryName + "/" + file.getOriginalFilename();


    }
}

