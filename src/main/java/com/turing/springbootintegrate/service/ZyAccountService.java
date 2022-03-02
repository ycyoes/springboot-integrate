package com.turing.springbootintegrate.service;

import com.turing.springbootintegrate.model.ZyAccount;
import com.turing.springbootintegrate.model.ZyUserDHRel;

import java.util.List;

public interface ZyAccountService {

	
	/**
	 * 查找全部待同步账号信息
	 * @param roleName 角色名称
	 * @return
	 */
	List<ZyAccount> getPendingAccount(String roleName);

	/**
	 * 将短号使用状态置为1：已使用
	 * @param duanHao 待修改状态的短号
	 * @return
	 */
	int updateStatus(String duanHao);

	/**
	 * 插入用户与短号的关联关系
	 * @param zyUserDHRel	待插入数据
	 * @return
	 */
	int insertUserDHRel(ZyUserDHRel zyUserDHRel);
	
}
