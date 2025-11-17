package com.tec.app.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Hashed {


    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }


}
