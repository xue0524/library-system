package com.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    // MD5加密（加盐防破解）
    public static String encrypt(String password) {
        // 盐值可自定义
        String salt = "library_system_2026";
        return DigestUtils.md5Hex(password + salt);
    }

    // 验证密码
    public static boolean verify(String inputPwd, String dbPwd) {
        return encrypt(inputPwd).equals(dbPwd);
    }  
}




