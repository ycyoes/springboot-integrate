package com.turing.springbootintegrate.controller;

import com.alibaba.fastjson.JSON;
import com.turing.springbootintegrate.common.utils.excel.ExcelImport;
import com.turing.springbootintegrate.common.utils.excel.ExportExcel;
import com.turing.springbootintegrate.common.utils.lang.DateUtils;
import com.turing.springbootintegrate.model.SysUser;
import com.turing.springbootintegrate.service.SysUserService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class ExcelController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/importExcel", method = RequestMethod.GET)
    public String importExcel() throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException {
        File file = new File("E:/test.xlsx");
        ExcelImport excelImport = new ExcelImport(file, 1, 0);
        List<SysUser> userList = excelImport.getDataList(SysUser.class);
        System.out.println(userList.size());
        userList.stream().forEach(user -> {
            System.out.println("用户名：" + user.getName() + " 昵称: " + user.getNickName() +
                    " 电话： " + user.getMobile() + " 邮箱： " + user.getEmail());

        });
        return JSON.toJSONString(userList);
    }

    @RequestMapping(value = "export", method=RequestMethod.GET)
    public String exportFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "用户数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            new ExportExcel("用户数据", SysUser.class).setDataList(sysUserService.findAll()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            System.out.println("导出用户失败！失败信息："+e.getMessage());
        }
        return "export";
    }
}

