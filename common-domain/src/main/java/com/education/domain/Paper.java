package com.education.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 
 * @date 2020-11-20 10:16:41
 * @version 1.0
 * @description 
 * @ApiModel(value="Paper对象", description="")
 */
@EqualsAndHashCode(callSuper = false)
@TableName("paper")
@Accessors(fluent = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Paper extends Model<Paper> implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.NONE)
    private Integer id;

    @TableField("title")
    private String title;

    @TableField("options")
    private String options;

    @TableField("space")
    private String space;

    @TableField("correct")
    private String correct;

    @TableField("description")
    private String description;

    @TableField(exist = false)
    private String paperName;

    @TableField(exist = false)
    private String paperDescription;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getPaperDescription() {
        return paperDescription;
    }

    public void setPaperDescription(String paperDescription) {
        this.paperDescription = paperDescription;
    }

    public Paper(Integer id, String title, String options, String space, String correct, String description, String paperName, String paperDescription) {
        this.id = id;
        this.title = title;
        this.options = options;
        this.space = space;
        this.correct = correct;
        this.description = description;
        this.paperName = paperName;
        this.paperDescription = paperDescription;
    }

    public Paper() {
    }
}