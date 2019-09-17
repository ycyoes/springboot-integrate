package com.turing.springbootintegrate.controller;

import com.turing.springbootintegrate.common.utils.PageRequest;
import com.turing.springbootintegrate.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping(value = "/findByUserId")
    public Object findByUserId(@RequestParam Long userId) {
        return sysUserService.findByUserId(userId);
    }

    @GetMapping(value = "/findAll")
    public Object findAll() {
        return sysUserService.findAll();
    }

    @PostMapping(value = "/findPage")
    public Object findPage(@RequestBody PageRequest pageQuery) {
        return sysUserService.findPage(pageQuery);
    }
}
