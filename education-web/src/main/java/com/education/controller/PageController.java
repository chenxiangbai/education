package com.education.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author admin
 * @Date 2020-11-20 14:13
 * @Version 1.0
 * @Description 页面跳转控制
 */
@Controller
public class PageController {
    @ApiOperation("首页跳转")
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @ApiOperation("登录页跳转")
    @GetMapping("/login")
    public String login() {
        return "redirect:/login.html";
    }

    @ApiOperation("数据源监控及sql调优 账号：admin 密码：123456")
    @GetMapping("/datasource")
    public String druid() {
        return "redirect:/druid/index.html";
    }

    @ApiOperation("api测试页面跳转")
    @GetMapping("/api")
    public String api() {
        return "redirect:/swagger-ui.html";
    }
}
