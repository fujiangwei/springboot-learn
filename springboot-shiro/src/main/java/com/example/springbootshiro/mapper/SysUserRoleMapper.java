package com.example.springbootshiro.mapper;

import com.example.springbootshiro.domain.SysUserRole;
import com.example.springbootshiro.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<Integer> findUserIdByRoleId(Integer roleId);
}
