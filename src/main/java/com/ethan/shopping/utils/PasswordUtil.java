package com.ethan.shopping.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUtil {
    private static final String algorithm = "MD5";
    private static final String salt = "gp30#?>2a";
    private static final Integer times = 3;

    public static String MD5(String password){
        SimpleHash simpleHash = new SimpleHash(algorithm, password, salt, times);
        return simpleHash.toString();
    }

    public static void main(String[] args) {
        String str = PasswordUtil.MD5("1234");
        System.out.println(str);
    }
}
