package com.redis.transaction.service;

/**
 * Created by jiangwenping on 17/3/21.
 */
public interface IRGTRedisService {
    public void expire(String key,int seconds);
    public boolean deleteKey(String key);
    public boolean setNxString(String key, String value, int seconds) throws Exception;
    public boolean setHnxString(String key, String field, String value) throws Exception;
    public void setString(String key,String value);
    public void setString(String key, String value, int seconds);
    public String getString(String key);
    public String getString(String key, int seconds);
    public boolean exists(String key);
}
