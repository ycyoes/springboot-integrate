package com.turing.springbootintegrate.service;

import java.util.List;

import com.turing.springbootintegrate.model.UserLog;

public interface UserLogService {
	/**
	 * 保存用户日志
	 * @param tableName
	 * @param userLog
	 */
	void save(String tableName, UserLog userLog); 
	
	/**
	 * 查找全部用户日志
	 * @param tableName
	 * @return
	 */
	List<UserLog> findALl(String tableName);
	
}
