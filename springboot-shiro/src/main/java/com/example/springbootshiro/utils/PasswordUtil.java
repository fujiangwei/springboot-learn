package com.example.springbootshiro.utils;

import com.example.springbootshiro.constants.CommonConstants;

public class PasswordUtil {

    /**
     * AES 加密
     *
     * @param password 未加密的密码
     * @param salt     盐值，默认使用用户名就可
     * @return
     * @throws Exception
     */
    public static String encrypt(String password, String salt) throws Exception {
        return AesUtil.encrypt(Md5Util.MD5(salt + CommonConstants.SALT_SECURITY_KEY), password);
    }

    /**
     * AES 解密
     *
     * @param encryptPassword 加密后的密码
     * @param salt            盐值，默认使用用户名就可
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptPassword, String salt) throws Exception {
        return AesUtil.decrypt(Md5Util.MD5(salt + CommonConstants.SALT_SECURITY_KEY), encryptPassword);
    }
}
