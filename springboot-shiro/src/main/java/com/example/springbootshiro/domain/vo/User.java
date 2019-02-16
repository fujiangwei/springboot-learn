package com.example.springbootshiro.domain.vo;

import com.example.springbootshiro.domain.SysUser;
import com.example.springbootshiro.enums.UserGenderEnum;
import com.example.springbootshiro.enums.UserStatusEnum;
import com.example.springbootshiro.enums.UserTypeEnum;
import com.example.springbootshiro.utils.PasswordUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = -3737736141782545763L;
    private SysUser sysUser;

    public User() {
        this.sysUser = new SysUser();
    }

    public User(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public User(String loginname, String password) {
        this();
        setUsername(loginname);
        if (!StringUtils.isEmpty(password)) {
            try {
                setPassword(PasswordUtil.encrypt(password, loginname));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @JsonIgnore
    public SysUser getSysUser() {
        return this.sysUser;
    }

    public Long getId() {
        return this.sysUser.getId();
    }

    public void setId(Long id) {
        this.sysUser.setId(id);
    }

    public String getNickname() {
        return this.sysUser.getNickname();
    }

    public void setNickname(String nickname) {
        this.sysUser.setNickname(nickname);
    }

    public String getMobile() {
        return this.sysUser.getMobile();
    }

    public void setMobile(String mobile) {
        this.sysUser.setMobile(mobile);
    }

    public String getUsername() {
        return this.sysUser.getUsername();
    }

    public void setUsername(String username) {
        this.sysUser.setUsername(username);
    }

    public String getPassword() {
        return this.sysUser.getPassword();
    }

    public void setPassword(String password) {
        this.sysUser.setPassword(password);
    }

    public String getEmail() {
        return this.sysUser.getEmail();
    }

    public void setEmail(String email) {
        this.sysUser.setEmail(email);
    }

    public String getQq() {
        return this.sysUser.getQq();
    }

    public void setQq(String qq) {
        this.sysUser.setQq(qq);
    }

    public Date getBirthday() {
        return this.sysUser.getBirthday();
    }

    public void setBirthday(Date birthday) {
        this.sysUser.setBirthday(birthday);
    }

    public Integer getGender() {
        return this.sysUser.getGender();
    }

    public void setGender(UserGenderEnum gender) {
        if (gender != null && gender.getCode() != -1) {
            this.sysUser.setGender(gender.getCode());
        }
    }

    public void setGender(Integer Gender) {
        this.sysUser.setGender(Gender);
    }

    public String getAvatar() {
        return this.sysUser.getAvatar();
    }

    public void setAvatar(String avatar) {
        this.sysUser.setAvatar(avatar);
    }

    public String getUserType() {
        return this.sysUser.getUserType();
    }

    public void setUserType(UserTypeEnum userTypeEnum) {
        if (null != userTypeEnum) {
            setUserType(userTypeEnum.toString());
        }
    }

    public void setUserType(String userType) {
        this.sysUser.setUserType(userType);
    }

    public UserTypeEnum getUserTypeEnum() {
        return UserTypeEnum.getByType(this.sysUser.getUserType());
    }

    public String getRegIp() {
        return this.sysUser.getRegIp();
    }

    public void setRegIp(String regIp) {
        this.sysUser.setRegIp(regIp);
    }

    public String getLastLoginIp() {
        return this.sysUser.getLastLoginIp();
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.sysUser.setLastLoginIp(lastLoginIp);
    }

    public Date getLastLoginTime() {
        return this.sysUser.getLastLoginTime();
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.sysUser.setLastLoginTime(lastLoginTime);
    }

    public Integer getLoginCount() {
        return this.sysUser.getLoginCount();
    }

    public void setLoginCount(Integer loginCount) {
        this.sysUser.setLoginCount(loginCount);
    }

    public String getRemark() {
        return this.sysUser.getRemark();
    }

    public void setRemark(String remark) {
        this.sysUser.setRemark(remark);
    }

    public Integer getStatus() {
        return this.sysUser.getStatus();
    }

    public void setStatus(Integer status) {
        this.sysUser.setStatus(status);
    }

    public UserStatusEnum getStatusEnum() {
        return UserStatusEnum.get(this.sysUser.getStatus());
    }

    public Date getCreateTime() {
        return this.sysUser.getCreateTime();
    }

    public void setCreateTime(Date regTime) {
        this.sysUser.setCreateTime(regTime);
    }

    public Date getUpdateTime() {
        return this.sysUser.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysUser.setUpdateTime(updateTime);
    }

}
