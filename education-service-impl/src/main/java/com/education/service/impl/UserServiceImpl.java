package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.bean.Result;
import com.education.bean.ResultCode;
import com.education.domain.User;
import com.education.mapper.UserMapper;
import com.education.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @version 1.0
 * @date 2020-11-20 10:16:41
 * @description
 */
@Service("UserServiceImpl")
@Primary
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    HttpServletRequest request;

    @Override
    public Result login(@Nullable User user) {
        if (user.getName().equals(String.valueOf(request.getSession().getAttribute("user")))) {
            return new Result();
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        if (StringUtils.isNotEmpty(user.getPassword()) && baseMapper.selectOne(wrapper) != null) {
            request.getSession().setAttribute("user", user.getName());
            return new Result();
        }
        ;
        return new Result(false);
    }

    @Override
    public Result logOut() {
        request.getSession().removeAttribute("user");
        return new Result();
    }

    @Override
    public Result register(@Nullable User user) {
        if(StringUtils.isNotEmpty(user.getName())&&StringUtils.isNotEmpty(user.getPassword())){
            if(baseMapper.selectById(user.getName())!=null){
                return new Result(ResultCode.ERROR,"用户已存在");
            };
            return new Result("注册成功");
        };
        return new Result(ResultCode.ERROR,"用户账号或密码不能为空");
    }
}
