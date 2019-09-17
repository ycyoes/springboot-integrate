package com.turing.springbootintegrate.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.springbootintegrate.common.utils.PageRequest;
import com.turing.springbootintegrate.common.utils.PageResult;
import com.turing.springbootintegrate.common.utils.PageUtils;
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

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<SysUser> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysMenus = sysUserMapper.selectPage();
        return new PageInfo<SysUser>(sysMenus);
    }
}