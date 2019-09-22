package com.turing.springbootintegrate.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.turing.springbootintegrate.dao.UserLogConfigMapper;
import com.turing.springbootintegrate.dao.UserLogMapper;
import com.turing.springbootintegrate.model.UserLogConfig;
import com.turing.springbootintegrate.service.UserLogConfigService;

@Service
public class UserLogConfigServiceImpl implements UserLogConfigService{
	
	@Autowired
	private UserLogConfigMapper userLogConfigMapper;
	
	@Autowired
	private UserLogMapper userLogMapper;

	@Override
	public void save(UserLogConfig userLogConfig) {
		// TODO Auto-generated method stub
		Assert.notNull(userLogConfig.getId(), "id不能为空");
		
		//添加配置时，创建日志存储表
		String tableName = userLogConfig.getTableName();
		if (userLogMapper.existTable(tableName) > 0) {	//表存在时删除
			userLogMapper.dropTable(tableName);
		} else {
			//表不存在时插入配置
			int result = userLogConfigMapper.insertSelective(userLogConfig);
			System.out.println("user log config insert result: " + result);
		}
		userLogMapper.createTable(tableName);
	}

	@Override
	public List<UserLogConfig> findAll() {
		// TODO Auto-generated method stub
		return userLogConfigMapper.selectAll();
	}
	
	
}
