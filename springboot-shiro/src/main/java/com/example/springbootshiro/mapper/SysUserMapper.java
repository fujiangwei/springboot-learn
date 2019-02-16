package com.example.springbootshiro.mapper;

import com.example.springbootshiro.domain.SysUser;
import com.example.springbootshiro.domain.vo.UserConditionVO;
import com.example.springbootshiro.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> findPageBreakByCondition(UserConditionVO vo);

    List<SysUser> listByRoleId(Long roleId);

}
