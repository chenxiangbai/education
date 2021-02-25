package com.education.controller;


import com.education.annoation.AspectLog;
import com.education.bean.Result;
import com.education.config.mybatis.DBContextHolder;
import com.education.domain.User;
import com.education.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2020-11-20 10:16:41
 * @description 用户登录注册相关
 */
@RestController
@ResponseBody
public class UserController {
    @Autowired(required = false)
    IUserService iUserService;

    @ApiOperation("登录")
    @AspectLog("登录")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true)
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return iUserService.login(user);
    }

    @ApiOperation("注册")
    @AspectLog("注册")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true)
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return iUserService.register(user);
    }

    @ApiOperation("退出")
    @AspectLog("退出")
    @GetMapping("/logout")
    public Result logout() {
        return iUserService.logOut();
    }

//    @GetMapping("/test/{msg}")
//    public void test(@PathVariable("msg") String msg, HttpServletRequest request) throws Exception {
//       // WebSocketServer.sendMessageByToken(request.getHeader("token"), msg);
//        WebSocketServer.broadCastInfo(msg);
//    }

    /**
     * 切面日志测试
     *
     * @param name
     * @return
     */
    @ApiOperation("切面日志测试")
    @AspectLog("切面日志测试")
    @PostMapping("/test")
    public String test(@RequestBody Map name) {
        String db = DBContextHolder.getDataSource();
        return "当前数据源：" + (StringUtils.isEmpty(db) ? "默认数据源" : db);
    }
}
