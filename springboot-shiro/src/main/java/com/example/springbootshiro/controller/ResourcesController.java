package com.example.springbootshiro.controller;

import com.example.springbootshiro.domain.vo.PageResult;
import com.example.springbootshiro.domain.vo.ResourceConditionVO;
import com.example.springbootshiro.domain.vo.Resources;
import com.example.springbootshiro.domain.vo.ResponseVO;
import com.example.springbootshiro.enums.ResponseStatusEnum;
import com.example.springbootshiro.holder.SpringContextHolder;
import com.example.springbootshiro.service.SysResourcesService;
import com.example.springbootshiro.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resources")
public class ResourcesController {

    @Autowired
    private SysResourcesService resourcesService;

    @RequiresPermissions("resources")
    @PostMapping("/list")
    public PageResult getAll(ResourceConditionVO vo) {
        PageInfo<Resources> pageInfo = resourcesService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("role:allotResource")
    @PostMapping("/resourcesWithSelected")
    public ResponseVO resourcesWithSelected(Long rid) {
        return ResultUtil.success(null, resourcesService.queryResourcesListWithSelected(rid));
    }

    @RequiresPermissions("resource:add")
    @PostMapping(value = "/add")
    public ResponseVO add(Resources resources) {
        resourcesService.insert(resources);
        //更新权限
        this.updatePermission();
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"resource:batchDelete", "resource:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            resourcesService.removeByPrimaryKey(id);
        }

        //更新权限
        this.updatePermission();
        return ResultUtil.success("成功删除 [" + ids.length + "] 个资源");
    }

    private void updatePermission() {
        ShiroFilterFactoryBean shirFilter = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
        synchronized (shirFilter) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shirFilter.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shirFilter.getFilterChainDefinitionMap().clear();
            shirFilter.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shirFilter.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
        }
    }

    private Map<String, String> loadFilterChainDefinitions() {
        /*
            配置访问权限
            - anon:所有url都都可以匿名访问
            - authc: 需要认证才能进行访问（此处指所有非匿名的路径都需要登陆才能访问）
            - user:配置记住我或认证通过可以访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/error", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        // 加载数据库中配置的资源权限列表
        List<Resources> resourcesList = resourcesService.listUrlAndPermission();
        for (Resources resources : resourcesList) {
            if (!StringUtils.isEmpty(resources.getUrl()) && !StringUtils.isEmpty(resources.getPermission())) {
                String permission = "perms[" + resources.getPermission() + "]";
                filterChainDefinitionMap.put(resources.getUrl(), permission);
            }
        }
        // 本例子中并不存在什么特别关键的操作，所以直接使用user认证。如果有朋友是参考本例子的shiro开发其他安全功能（比如支付等）时，建议针对这类操作使用authc权限 by yadong.zhang
        filterChainDefinitionMap.put("/**", "user");
        return filterChainDefinitionMap;
    }

    @RequiresPermissions("resource:edit")
    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.resourcesService.getByPrimaryKey(id));
    }

    @RequiresPermissions("resource:edit")
    @PostMapping("/edit")
    public ResponseVO edit(Resources resources) {
        try {
            resourcesService.updateSelective(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("资源修改失败！");
        }
        return ResultUtil.success(ResponseStatusEnum.SUCCESS);
    }
}
