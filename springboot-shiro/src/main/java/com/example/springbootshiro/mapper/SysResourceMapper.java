package com.example.springbootshiro.mapper;

import com.example.springbootshiro.domain.SysResources;
import com.example.springbootshiro.domain.vo.ResourceConditionVO;
import com.example.springbootshiro.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysResourceMapper extends BaseMapper<SysResources> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    List<SysResources> findPageBreakByCondition(ResourceConditionVO vo);

    List<SysResources> listUserResources(Map<String, Object> map);

    /**
     * 该节代码参考自http://blog.csdn.net/poorcoder_/article/details/71374002
     * 感谢网友
     *
     * @param rid
     * @return
     */
    List<SysResources> queryResourcesListWithSelected(Long rid);

    List<SysResources> listUrlAndPermission();

    List<SysResources> listAllAvailableMenu();

    List<SysResources> listMenuResourceByPid(Long pid);

    List<SysResources> listByUserId(Long userId);
}
