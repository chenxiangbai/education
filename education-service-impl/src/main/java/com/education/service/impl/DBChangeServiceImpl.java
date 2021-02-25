package com.education.service.impl;

import com.education.bean.DataSource;
import com.education.config.mybatis.DBContextHolder;
import com.education.config.mybatis.DynamicDataSource;
import com.education.service.DBChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author admin
 * @Date 2021-02-23 15:02
 * @Version 1.0
 * @Description 切换数据源实现类
 */
@Service
public class DBChangeServiceImpl implements DBChangeService {

    @Autowired
    private DynamicDataSource dynamicDataSource;
    /**
     * 数据库连接缓存池
     */
    public static ConcurrentHashMap<String, DataSource> dbList = new ConcurrentHashMap<String, DataSource>();

    @Override
    public boolean createDb(DataSource dataSource) throws Exception {
        formatDataSource(dataSource);
        dbList.put(dataSource.getDatasourceId(), dataSource);
        dynamicDataSource.delDatasources(dataSource.getDatasourceId());
        return false;
    }

    @Override
    public boolean delDb(String dataSourceId) throws Exception {
        return false;
    }

    @Override
    public boolean changeDb(String datasourceId) throws Exception {
        //默认切换到主数据源,进行整体资源的查找
        DBContextHolder.clearDataSource();
        String key = datasourceId(datasourceId);
        DataSource dataSource = null;
        if (dbList.containsKey(key)) {
            dataSource = dbList.get(key);
            dynamicDataSource.createDataSourceWithCheck(dataSource);
            //切换到该数据源
            DBContextHolder.setDataSource(dataSource.getDatasourceId());
        }
        return false;

    }

    private void formatDataSource(DataSource dataSource) {
        dataSource.setDbType("com.alibaba.druid.pool.DruidDataSource");
        String datasourceId = datasourceId(dataSource.getDatasourceId());
        dataSource.setDatasourceId(datasourceId);
    }

    private String datasourceId(String dataSourceId) {
        return dataSourceId;
    }

}