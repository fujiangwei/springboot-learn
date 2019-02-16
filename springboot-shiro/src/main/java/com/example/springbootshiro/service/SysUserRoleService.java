package com.example.springbootshiro.service;

import com.example.springbootshiro.domain.vo.UserRole;

public interface SysUserRoleService extends AbstractService<UserRole, Long> {

    /**
     * 添加用户角色
     *
     * @param userId
     * @param roleIds
     */
    void addUserRole(Long userId, String roleIds);

    /**
     * 根据用户ID删除用户角色
     *
     * @param userId
     */
    void removeByUserId(Long userId);
}
