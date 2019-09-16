package com.example.springbootdruidmultsource.service.impl;

import com.example.springbootdruidmultsource.domain.master.SysUser;
import com.example.springbootdruidmultsource.mapper.master.SysUserMapper;
import com.example.springbootdruidmultsource.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/1/31
 * @time: 21:56
 * @modifier:
 * @since:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> listUser() {
        return sysUserMapper.selectUserList();
    }

    @Override
    @Transactional
    public void update(SysUser sysUser) {
        sysUserMapper.update(sysUser);
//        int i = 10 / 0;
    }
}
