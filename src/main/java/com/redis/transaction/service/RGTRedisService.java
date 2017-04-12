package com.redis.transaction.service;

import com.redis.log.Loggers;
import com.redis.util.TimeUtil;
import org.slf4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;

/**
 * Created by jiangwenping on 16/11/26.
 * 提供redis读取服务
 */
public class RGTRedisService implements IRGTRedisService {

    protected static Logger logger = Loggers.redisLogger;
    /*
     * 数据源
     */
    private JedisPool jedisPool;
    /**
     * 设置连接池
     */
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /*
     * 正常返还链接
     */
    private void returnResource(Jedis jedis) {
        try {
            jedisPool.returnResource(jedis);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }
    /*
     * 释放错误链接
     */
    private void returnBrokenResource(Jedis jedis,String name,Exception exception){
        logger.error(TimeUtil.getDateString(new Date())+":::::"+name);
        if (jedis != null) {
            try {
                jedisPool.returnBrokenResource(jedis);
            } catch (Exception e) {
                logger.error(e.toString(), e);
            }
        }
        if(exception != null){
            logger.error(exception.toString(), exception);
        }
    }

    /**
     * 释放成功链接
     * @param success
     * @param jedis
     */
    private void releaseReidsSource(boolean success, Jedis jedis){
        if (success && jedis != null) {
            returnResource(jedis);
        }
    }

    /**
     * 释放非正常链接
     * @param jedis
     * @param key
     * @param string
     * @param e
     */
    private void releasBrokenReidsSource(Jedis jedis, String key, String string, Exception e, boolean deleteKeyFlag){
        returnBrokenResource(jedis, string, e);
        if(deleteKeyFlag){
            expire(key, 0);
        }
    }

    /**
     * 设置缓存生命周期
     * @param key
     * @param seconds
     */
    @Override
    public void expire(String key,int seconds){
        Jedis jedis = null;
        boolean sucess = true;
        try {
            jedis=jedisPool.getResource();
            jedis.expire(key, seconds);
        } catch (Exception e) {
            sucess = false;
            returnBrokenResource(jedis, "expire:"+key, e);
        } finally {
            if (sucess && jedis != null) {
                returnResource(jedis);
            }
        }
    }

    /**
     * 删除key
     * @param key
     */
    @Override
    public boolean deleteKey(String key){
        Jedis jedis = null;
        boolean success = true;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            success = false;
            releasBrokenReidsSource(jedis, key, "deleteKey", e, false);
        } finally {
            releaseReidsSource(success, jedis);
        }

        return success;
    }

    /**
     * 设置
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean setNxString(String key, String value, int seconds) throws Exception{
        Jedis jedis = null;
        boolean success = true;
        boolean result = false;
        try {
            jedis = jedisPool.getResource();
            result = (jedis.setnx(key, value) != 0);
            if(seconds > -1){
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            success = false;
            releasBrokenReidsSource(jedis, key, "setNxString", e, false);
            throw e;
        } finally {
            releaseReidsSource(success, jedis);
        }

        return result;

    }

    /**
     * 设置
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean setHnxString(String key, String field, String value) throws Exception{
        Jedis jedis = null;
        boolean success = true;
        boolean result = false;
        try {
            jedis = jedisPool.getResource();
            result = (jedis.hsetnx(key, field, value) != 0);
        } catch (Exception e) {
            success = false;
            releasBrokenReidsSource(jedis, key, "setHnxString", e, false);
            throw e;
        } finally {
            releaseReidsSource(success, jedis);
        }

        return result;

    }

    @Override
    public void setString(String key,String value){
        setString(key, value, -1);
    }

    @Override
    public void setString(String key, String value, int seconds) {
        Jedis jedis = null;
        boolean sucess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            if(seconds>-1){
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            sucess = false;
            returnBrokenResource(jedis, "setString", e);
            expire(key, 0);
        } finally {
            if (sucess && jedis != null) {
                returnResource(jedis);
            }
        }
    }

    @Override
    public String getString(String key){
        return getString(key, -1);
    }

    @Override
    public String getString(String key, int seconds){
        Jedis jedis = null;
        boolean sucess = true;
        String rt = null;
        try {
            jedis = jedisPool.getResource();
            rt = jedis.get(key);
            if(seconds > -1){
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            sucess = false;
            returnBrokenResource(jedis, "getString", e);
        } finally {
            if (sucess && jedis != null) {
                returnResource(jedis);
            }
        }
        return rt;
    }

    @Override
    public boolean exists(String key){
        Jedis jedis = null;
        boolean sucess = true;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            sucess = false;
            returnBrokenResource(jedis, "exists:"+key, e);
        } finally {
            if (sucess && jedis != null) {
                returnResource(jedis);
            }
        }
        return false;
    }
}
