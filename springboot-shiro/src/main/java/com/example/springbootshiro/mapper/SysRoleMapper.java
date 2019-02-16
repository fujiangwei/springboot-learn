package com.example.springbootshiro.mapper;

import com.example.springbootshiro.domain.SysRole;
import com.example.springbootshiro.domain.vo.RoleConditionVO;
import com.example.springbootshiro.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    List<SysRole> findPageBreakByCondition(RoleConditionVO vo);

    /**
     * 该节代码参考自http://blog.csdn.net/poorcoder_/article/details/71374002
     * 感谢网友
     *
     * @param userId
     * @return
     */
    List<SysRole> queryRoleListWithSelected(Integer userId);

    List<SysRole> listRolesByUserId(Long userId);
}
