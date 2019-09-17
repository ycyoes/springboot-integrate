package com.turing.springbootintegrate.dao;

import com.turing.springbootintegrate.model.SysUser;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 查询全部
     * @return
     */
    List<SysUser> findAll();

    /**
     * 分页查询用户
     * @return
     */
    List<SysUser> selectPage();
}
