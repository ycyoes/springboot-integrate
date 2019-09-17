package com.turing.springbootintegrate.service.impl;

import java.util.List;

import com.turing.springbootintegrate.dao.SysUserMapper;
import com.turing.springbootintegrate.model.SysUser;
import com.turing.springbootintegrate.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

    @Override
    public SysUser findByUserId(Long userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }
}