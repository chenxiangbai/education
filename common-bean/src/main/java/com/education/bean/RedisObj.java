package com.education.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @version 1.0
 * @date 2020-06-08 14:59
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class RedisObj<T> implements Serializable {
    private String key;
    private T value;
    private long seconds = 300;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public RedisObj(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
