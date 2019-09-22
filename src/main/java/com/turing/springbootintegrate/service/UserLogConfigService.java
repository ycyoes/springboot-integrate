package com.turing.springbootintegrate.service;

import java.util.List;

import com.turing.springbootintegrate.model.UserLogConfig;

public interface UserLogConfigService {
	
	/**
	 * 保存用户日志配置
	 * @param userLogConfig
	 */
	void save(UserLogConfig userLogConfig);
	
	/**
	 * 查找全部用户日志配置
	 * @return
	 */
	List<UserLogConfig> findAll();
}
