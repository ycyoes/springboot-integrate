package com.turing.springbootintegrate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turing.springbootintegrate.config.multisource.DataSource;
import com.turing.springbootintegrate.model.UserLogConfig;
import com.turing.springbootintegrate.service.UserLogConfigService;

@RestController
@RequestMapping("/user/log/config")
public class UserLogConfigController {
	
	@Autowired
	private UserLogConfigService userLogConfigService;
	
	@DataSource("slave")
	@PostMapping(value = "/save")
	public Object save(@RequestBody UserLogConfig userLogConfig) {
		userLogConfigService.save(userLogConfig);
		return 1;
	}
	
	@DataSource("slave")
	@GetMapping(value = "/findALl")
	public Object findALl() {
		return userLogConfigService.findAll();
	}
}
