package com.education.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 
 * @date 2020-11-20 10:16:41
 * @version 1.0
 * @description 
 * @ApiModel(value="Relevance对象", description="")
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("relevance")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Relevance extends Model<Relevance> {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("paper")
    private String paper;

    @TableField("description")
    private String description;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}