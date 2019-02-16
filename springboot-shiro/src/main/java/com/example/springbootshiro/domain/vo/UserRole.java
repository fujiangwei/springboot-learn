package com.example.springbootshiro.domain.vo;

import com.example.springbootshiro.domain.SysUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class UserRole {

    private SysUserRole sysUserRole;

    public UserRole() {
        this.sysUserRole = new SysUserRole();
    }

    public UserRole(SysUserRole sysUserRole) {
        this.sysUserRole = sysUserRole;
    }

    @JsonIgnore
    public SysUserRole getSysUserRole() {
        return this.sysUserRole;
    }

    public Long getUserId() {
        return this.sysUserRole.getUserId();
    }

    public void setUserId(Long userId) {
        this.sysUserRole.setUserId(userId);
    }

    public Long getRoleId() {
        return this.sysUserRole.getRoleId();
    }

    public void setRoleId(Long roleId) {
        this.sysUserRole.setRoleId(roleId);
    }

    public Date getCreateTime() {
        return this.sysUserRole.getCreateTime();
    }

    public void setCreateTime(Date regTime) {
        this.sysUserRole.setCreateTime(regTime);
    }

    public Date getUpdateTime() {
        return this.sysUserRole.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysUserRole.setUpdateTime(updateTime);
    }
}
