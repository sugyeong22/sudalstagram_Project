package com.sudal.sudalstagram.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAHashingEncoder {

    // sha를 통한 해싱
    public static String encode(String message) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("sha");

            byte[] bytes = message.getBytes();

            messageDigest.update(bytes);

            byte[] digest = messageDigest.digest();

            String result = "";
            for(int i = 0; i < digest.length; i++) {
                // 16진수 값으로 변환해서 붙혀넣음
                result += Integer.toHexString(digest[i] & 0xff);
            }

            return result;

        } catch (NoSuchAlgorithmException e) {

            return null;
        }

    }


}
