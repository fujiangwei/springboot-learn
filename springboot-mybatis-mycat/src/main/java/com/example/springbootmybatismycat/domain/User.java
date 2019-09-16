package com.example.springbootmybatismycat.domain;

import java.io.Serializable;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/1/31
 * @time: 21:42
 * @modifier:
 * @since:
 */
public class User implements Serializable {

    private static final long serialVersionUID = 5088697673359856350L;

    private Integer userId;

    private String userName;

    private Integer companyId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
