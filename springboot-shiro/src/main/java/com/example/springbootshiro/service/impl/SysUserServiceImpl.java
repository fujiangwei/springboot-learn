package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.domain.SysUser;
import com.example.springbootshiro.domain.vo.User;
import com.example.springbootshiro.domain.vo.UserConditionVO;
import com.example.springbootshiro.enums.UserStatusEnum;
import com.example.springbootshiro.holder.RequestHolder;
import com.example.springbootshiro.mapper.SysUserMapper;
import com.example.springbootshiro.service.SysRoleService;
import com.example.springbootshiro.service.SysUserService;
import com.example.springbootshiro.utils.IpUtil;
import com.example.springbootshiro.utils.PasswordUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleService roleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User insert(User user) {
        Assert.notNull(user, "User不可为空！");
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        user.setRegIp(IpUtil.getRealIp(RequestHolder.getRequest()));
        user.setStatus(UserStatusEnum.NORMAL.getCode());
        sysUserMapper.insertSelective(user.getSysUser());
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertList(List<User> users) {
        Assert.notNull(users, "Users不可为空！");
        List<SysUser> sysUsers = new ArrayList<>();
        String regIp = IpUtil.getRealIp(RequestHolder.getRequest());
        for (User user : users) {
            user.setUpdateTime(new Date());
            user.setCreateTime(new Date());
            user.setRegIp(regIp);
            sysUsers.add(user.getSysUser());
        }
        sysUserMapper.insertList(sysUsers);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysUserMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(User user) {
        Assert.notNull(user, "User不可为空！");
        user.setUpdateTime(new Date());
        if (!StringUtils.isEmpty(user.getPassword())) {
            try {
                user.setPassword(PasswordUtil.encrypt(user.getPassword(), user.getUsername()));
            } catch (Exception e) {
                throw new IllegalArgumentException("密码加密失败");
            }
        }
        return sysUserMapper.updateByPrimaryKey(user.getSysUser()) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(User user) {
        Assert.notNull(user, "User不可为空！");
        user.setUpdateTime(new Date());
        if (!StringUtils.isEmpty(user.getPassword())) {
            try {
                user.setPassword(PasswordUtil.encrypt(user.getPassword(), user.getUsername()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("密码加密失败");
            }
        } else {
            user.setPassword(null);
        }
        return sysUserMapper.updateByPrimaryKeySelective(user.getSysUser()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */

    @Override
    public User getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(primaryKey);
        return null == sysUser ? null : new User(sysUser);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public User getOneByEntity(User entity) {
        Assert.notNull(entity, "User不可为空！");
        SysUser sysUser = sysUserMapper.selectOne(entity.getSysUser());
        return null == sysUser ? null : new User(sysUser);
    }

    @Override
    public List<User> listAll() {
        List<SysUser> sysUsers = sysUserMapper.selectAll();

        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for (SysUser sysUser : sysUsers) {
            users.add(new User(sysUser));
        }
        return users;
    }

    @Override
    public List<User> listByEntity(User user) {
        Assert.notNull(user, "User不可为空！");
        List<SysUser> sysUsers = sysUserMapper.select(user.getSysUser());
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for (SysUser su : sysUsers) {
            users.add(new User(su));
        }
        return users;
    }

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<User> findPageBreakByCondition(UserConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for (SysUser su : sysUsers) {
            users.add(new User(su));
        }
        PageInfo bean = new PageInfo<SysUser>(sysUsers);
        bean.setList(users);
        return bean;
    }

    /**
     * 更新用户最后一次登录的状态信息
     *
     * @param user
     * @return
     */
    @Override
    public User updateUserLastLoginInfo(User user) {
        if (user != null) {
            user.setLoginCount(user.getLoginCount() + 1);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.getRealIp(RequestHolder.getRequest()));
            user.setPassword(null);
            this.updateSelective(user);
        }
        return user;
    }

    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     */
    @Override
    public User getByUserName(String userName) {
        User user = new User(userName, null);
        return getOneByEntity(user);
    }

    /**
     * 通过角色Id获取用户列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<User> listByRoleId(Long roleId) {
        List<SysUser> sysUsers = sysUserMapper.listByRoleId(roleId);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for (SysUser su : sysUsers) {
            users.add(new User(su));
        }
        return users;
    }

}
