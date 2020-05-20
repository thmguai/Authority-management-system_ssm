package com.it.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodingPasswordUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static void main(String[] args) {
        String encode = bCryptPasswordEncoder.encode("456");
        System.out.println(encode);
    }
}
