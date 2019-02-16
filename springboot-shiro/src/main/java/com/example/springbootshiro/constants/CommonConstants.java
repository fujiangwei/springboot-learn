package com.example.springbootshiro.constants;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/13
 * @time: 21:42
 * @modifier:
 * @since:
 */
public class CommonConstants {

    /**
     * 安全密码(UUID生成)，作为盐值用于用户密码的加密
     */
    public static final String SALT_SECURITY_KEY = "684123f8f17944e8b0a531045453e1f1";

    /**
     * 程序默认的错误状态码
     */
    public static final int DEFAULT_ERROR_CODE = 500;

    /**
     * 程序默认的成功状态码
     */
    public static final int DEFAULT_SUCCESS_CODE = 200;

    /**
     * User 的 session key;
     */
    public static final String USER_SESSION_KEY = "user";
}
