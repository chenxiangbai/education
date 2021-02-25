package com.education.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author 
 * @date 2020-11-20 10:16:41
 * @version 1.0
 * @description 
 * @ApiModel(value="User对象", description="")
 */
@EqualsAndHashCode(callSuper = false)
@TableName("user")
//@Accessors(fluent = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "name", type = IdType.AUTO)
    private String name;

    @TableField("password")
    private String password;

    @TableField("role")
    private Integer role;

    @Override
    protected Serializable pkVal() {
        return this.name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public User() {
    }

    public User(String name, String password, Integer role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}