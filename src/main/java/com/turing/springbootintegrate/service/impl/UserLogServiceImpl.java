package com.turing.springbootintegrate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.springbootintegrate.dao.UserLogMapper;
import com.turing.springbootintegrate.model.UserLog;
import com.turing.springbootintegrate.service.UserLogService;

@Service
public class UserLogServiceImpl implements UserLogService{

	@Autowired
	private UserLogMapper userLogMapper;
	
	@Override
	public void save(String tableName, UserLog userLog) {
		// TODO Auto-generated method stub
		//插入
		userLogMapper.insertSelective(tableName, userLog);
	}

	@Override
	public List<UserLog> findALl(String tableName) {
		// TODO Auto-generated method stub
		return userLogMapper.selectAll(tableName);
	}

}
