package com.example.springbootshiro.shiro.realm;

import com.example.springbootshiro.domain.vo.Resources;
import com.example.springbootshiro.domain.vo.Role;
import com.example.springbootshiro.domain.vo.User;
import com.example.springbootshiro.enums.UserStatusEnum;
import com.example.springbootshiro.service.SysResourcesService;
import com.example.springbootshiro.service.SysRoleService;
import com.example.springbootshiro.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Shiro-密码输入错误的状态下重试次数的匹配管理
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private SysUserService userService;
    @Resource
    private SysResourcesService resourcesService;
    @Resource
    private SysRoleService roleService;

    /**
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("=================用户认证=================");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        User user = userService.getByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }
        if (user.getStatus() != null && UserStatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new LockedAccountException("帐号已被锁定，禁止登录！");
        }

        // principal参数使用用户Id，方便动态刷新用户权限
        return new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(username),
                getName()
        );
    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("=================用户授权=================");
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Long userId = user.getId();

        // 赋予角色
        List<Role> roleList = roleService.listRolesByUserId(userId);
        for (Role role : roleList) {
            info.addRole(role.getName());
        }

        // 赋予权限
        List<Resources> resourcesList = resourcesService.listByUserId(userId);
        if (!CollectionUtils.isEmpty(resourcesList)) {
            for (Resources resources : resourcesList) {
                String permission = resources.getPermission();
                System.out.println(resources.getName() + "   " + permission);
                if (!StringUtils.isEmpty(permission)) {
                    info.addStringPermission(permission);
                }
            }
        }
        return info;
    }

}
