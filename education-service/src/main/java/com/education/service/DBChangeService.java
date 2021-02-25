package com.education.service;

import com.education.bean.DataSource;

/**
 * @Author admin
 * @Date 2021-02-23 15:00
 * @Version 1.0
 * @Description 动态数据源切换
 */
public interface DBChangeService {
    boolean createDb(DataSource dataSource)throws Exception;
    boolean delDb(String dataSourceId)throws Exception;
    boolean changeDb(String datasourceId) throws Exception;
}
