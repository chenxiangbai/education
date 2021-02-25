package com.education.service.impl;

import com.education.bean.RedisObj;
import com.education.bean.Result;
import com.education.bean.ResultCode;
import com.education.service.RedisService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author admin
 * @version 1.0
 * @date 2020-06-08 10:52
 * @description
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Result setValue(RedisObj obj) {
        try {
            redisTemplate.opsForValue().set(obj.getKey(), obj.getValue(),obj.getSeconds(),obj.getTimeUnit());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.ERROR.result(e.getMessage());
        }
        return ResultCode.SUCCESS.result();
    }

    @Override
    public Result getValue(String key) {
        try {
            if(key==null){
                return new Result(null,ResultCode.SUCCESS);
            }
            return new Result(redisTemplate.opsForValue().get(key),ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResultCode.ERROR.result(e.getMessage());
        }

    }

    @Override
    public Result putHash(RedisObj<Map> obj) {
        try{
            redisTemplate.opsForHash().putAll(obj.getKey(),obj.getValue());
            redisTemplate.expire(obj.getKey(), obj.getSeconds(), obj.getTimeUnit());
        }catch (Exception e){
            return ResultCode.ERROR.result(e.getMessage());
        }
        return ResultCode.SUCCESS.result();
    }

    @Override
    public Result getHash(String key) {
        try{
            if(key==null){
                return new Result(null,ResultCode.SUCCESS);
            }
            return new Result(redisTemplate.opsForHash().entries(key),ResultCode.SUCCESS);
        }catch (Exception e){
            return ResultCode.ERROR.result(e.getMessage());
        }
    }

    @Override
    public Result getHashKeys(String key) {
        try{
            if(key==null){
                return new Result(null,ResultCode.SUCCESS);
            }
            return new Result(redisTemplate.opsForHash().keys(key),ResultCode.SUCCESS);
        }catch (Exception e){
            return ResultCode.ERROR.result(e.getMessage());
        }
    }

    @Override
    public Result getHashValues(String key) {
        try{
            if(key==null){
                return new Result(null,ResultCode.SUCCESS);
            }
            return new Result(redisTemplate.opsForHash().values(key),ResultCode.SUCCESS);
        }catch (Exception e){
            return ResultCode.ERROR.result(e.getMessage());
        }
    }

    @Override
    public Result getHash(String key, Object hashKey) {
        try{
            if(key==null){
                return new Result(null,ResultCode.SUCCESS);
            }
            return new Result(redisTemplate.opsForHash().get(key,hashKey),ResultCode.SUCCESS);
        }catch (Exception e){
            return ResultCode.ERROR.result(e.getMessage());
        }
    }

    @Override
    public Boolean hasHashKey(String key, Object hashKey) {
        if(StringUtils.isEmpty(key)|| ObjectUtils.isEmpty(hashKey)){
            return false;
        }
        return redisTemplate.hasKey(key);
    }

    @Override
    public Boolean hasKey(String key) {
        if(StringUtils.isEmpty(key)){
            return false;
        }
        return redisTemplate.hasKey(key);
    }
}
