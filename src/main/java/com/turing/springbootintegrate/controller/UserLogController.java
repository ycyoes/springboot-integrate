package com.turing.springbootintegrate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turing.springbootintegrate.config.multisource.DataSource;
import com.turing.springbootintegrate.model.UserLog;
import com.turing.springbootintegrate.service.UserLogService;

@RestController
@RequestMapping("user/log")
public class UserLogController {
	
	@Autowired
	private UserLogService userLogService;
	
	@DataSource("slave")
	@PostMapping(value = "/save")
	public Object save(@RequestBody UserLog userLog) {
		String tableName = userLog.getTableName();
		userLogService.save(tableName, userLog);
		return 1;
	}
	
	@DataSource("slave")
	@GetMapping(value = "/findAll")
	public Object findAll(String tableName) {
		return userLogService.findALl(tableName);
	}
}
