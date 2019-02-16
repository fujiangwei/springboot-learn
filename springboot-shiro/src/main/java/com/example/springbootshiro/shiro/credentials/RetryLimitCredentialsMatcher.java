package com.example.springbootshiro.shiro.credentials;

import com.example.springbootshiro.constants.CommonConstants;
import com.example.springbootshiro.domain.vo.User;
import com.example.springbootshiro.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Shiro-密码输入错误的状态下重试次数的匹配管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
public class RetryLimitCredentialsMatcher extends CredentialsMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryLimitCredentialsMatcher.class);

    /**
     * 用户登录次数计数  redisKey 前缀
     */
    private static final String SHIRO_LOGIN_COUNT = "shiro_login_count_";
    /**
     * 用户登录是否被锁定    一小时 redisKey 前缀
     */
    private static final String SHIRO_IS_LOCK = "shiro_is_lock_";

    @Autowired
    private SysUserService userService;

    Map<String, Integer> countMap = new HashMap<String, Integer>();

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        System.out.println("=================RetryLimitCredentialsMatcher.doCredentialsMatch=================");
        User shiroUser = (User) info.getPrincipals().getPrimaryPrincipal();
        Long userId = shiroUser.getId();
        User user = userService.getByPrimaryKey(userId);
        String username = user.getUsername();
        // 访问一次，计数一次
        String loginCountKey = SHIRO_LOGIN_COUNT + username;
        String isLockKey = SHIRO_IS_LOCK + username;
        countMap.put(loginCountKey, 1);

        if (countMap.get(loginCountKey) > 5) {
            throw new ExcessiveAttemptsException("帐号[" + username + "]已被禁止登录！");
        }

        // 计数大于5时，设置用户被锁定一小时
        Integer loginCount = countMap.get(loginCountKey);
        int retryCount = (5 - loginCount);
        if (retryCount <= 0) {
            throw new ExcessiveAttemptsException("由于密码输入错误次数过多，帐号[" + username + "]已被禁止登录！");
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if (!matches) {
            String msg = retryCount <= 0 ? "您的账号一小时内禁止登录！" : "您还剩" + retryCount + "次重试的机会";
            throw new AccountException("帐号或密码不正确！" + msg);
        }

        //清空登录计数
        countMap.remove(loginCountKey);
        try {
            userService.updateUserLastLoginInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 当验证都通过后，把用户信息放在session里
        // 注：User必须实现序列化
        SecurityUtils.getSubject().getSession().setAttribute(CommonConstants.USER_SESSION_KEY, user);
        return true;
    }
}
