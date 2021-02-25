package com.education.service;

import com.education.bean.RedisObj;
import com.education.bean.Result;

import java.util.Map;

/**
 * @Author admin
 * @Date 2021-02-24 9:11
 * @Version 1.0
 * @Description
 */
public interface RedisService {
    /**
     * setValue 方法
     * @param obj
     * @return Result
     */
    public Result setValue(RedisObj obj);

    /**
     * getValue 方法
     * @param key
     * @return
     */
    public Result getValue(String key);

    /**
     * putHash 方法
     * @param obj
     * @return
     */
    public Result putHash(RedisObj<Map> obj);

    /**
     * getHash 方法
     * @param key
     * @return
     */
    public Result getHash(String key);

    /**
     * getHashKeys 方法
     * @param key
     * @return
     */
    public Result getHashKeys(String key);

    /**
     * getHashValues 方法
     * @param key
     * @return
     */
    public Result getHashValues(String key);

    /**
     * getHash 方法
     * @param key
     * @param hashKey
     * @return
     */
    public Result getHash(String key,Object hashKey);

    /**
     * 判断是否存在
     * @param key
     * @return
     */
    public Boolean hasHashKey(String key,Object hashKey);

    /**
     * 判断是否存在
     * @param key
     * @return
     */
    public Boolean hasKey(String key);
}
