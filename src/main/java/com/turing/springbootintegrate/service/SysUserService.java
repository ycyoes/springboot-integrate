package com.turing.springbootintegrate.service;

import com.turing.springbootintegrate.model.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 查找所有用户
     * @return
     */
    List<SysUser> findAll();
    /**
     * 根据用户ID查找用户
     * @param userId
     * @return
     */
    SysUser findByUserId(Long userId);

}