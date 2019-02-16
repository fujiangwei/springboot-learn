package com.example.springbootshiro.service;

import com.example.springbootshiro.domain.vo.Role;
import com.example.springbootshiro.domain.vo.RoleConditionVO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysRoleService extends AbstractService<Role, Long> {

    /**
     * 获取ztree使用的角色列表
     *
     * @param uid
     * @return
     */
    List<Map<String, Object>> queryRoleListWithSelected(Integer uid);

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Role> findPageBreakByCondition(RoleConditionVO vo);

    /**
     * 获取用户的角色
     *
     * @param userId
     * @return
     */
    List<Role> listRolesByUserId(Long userId);
}
