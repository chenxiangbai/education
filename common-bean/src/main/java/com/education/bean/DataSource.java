package com.education.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @Author admin
 * @Date 2021-02-23 14:35
 * @Version 1.0
 * @Description
 */
@Data
@ToString
public class DataSource {
    String datasourceId;
    String url;
    String userName;
    String passWord;
    String code;
    String dbType;
    String driveClass;
}