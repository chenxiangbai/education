package com.education.service;

import com.education.bean.Result;
import com.education.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 
 * @date 2020-11-20 10:16:41
 * @version 1.0
 * @description 
 */
public interface IUserService extends IService<User> {
    /**
     * 登录
     * @param user
     * @return
     */
    Result login(User user);

    /**
     * 注销
     * @return
     */
    Result logOut();

    /**
     * 注册
     * @param user
     * @return
     */
    Result register(User user);

}
