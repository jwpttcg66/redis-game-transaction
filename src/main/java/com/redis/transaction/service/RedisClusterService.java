package com.redis.transaction.service;

import com.redis.log.Loggers;
import com.redis.transaction.service.cluster.JedisClusterFactory;
import com.redis.util.TimeUtil;
import org.slf4j.Logger;

import java.util.Date;

/**
 * Created by jiangwenping on 17/3/21.
 * redis集群服务
 */
public class RedisClusterService implements IRedisService {

    protected static Logger logger = Loggers.redisLogger;

    private JedisClusterFactory jedisClusterFactory;

    @Override
    public void expire(String key, int seconds) {
        try {
            jedisClusterFactory.getObject().expire(key, seconds);
        } catch (Exception e) {
            logger.error(TimeUtil.getDateString(new Date()) + ":::::" + "expire" + key, e);
        }
    }

    @Override
    public boolean deleteKey(String key) {
        boolean flag = false;
        try {
            jedisClusterFactory.getObject().del(key);
            flag = true;
        } catch (Exception e) {
            logger.error(TimeUtil.getDateString(new Date()) + ":::::" + "deleteKey" + key, e);
        }
        return flag;
    }

    @Override
    public boolean setNxString(String key, String value, int seconds) throws Exception {
        boolean flag = false;
        try {
            flag = (jedisClusterFactory.getObject().setnx(key, value) != 0);
            if (seconds > -1) {
                jedisClusterFactory.getObject().expire(key, seconds);
            }
        } catch (Exception e) {
            logger.error(TimeUtil.getDateString(new Date()) + ":::::" + "setNxString" + key, e);
        }
        return flag;
    }

    @Override
    public boolean setHnxString(String key, String field, String value) throws Exception {
        boolean flag = false;
        try {
            flag = (jedisClusterFactory.getObject().hsetnx(key, field, value) != 0);
        } catch (Exception e) {
            logger.error(TimeUtil.getDateString(new Date()) + ":::::" + "setNxString" + key, e);
        }
        return flag;
    }

    @Override
    public void setString(String key, String value) {
        try {
            jedisClusterFactory.getObject().set(key, value);
        } catch (Exception e) {
            logger.error(TimeUtil.getDateString(new Date()) + ":::::" + "setString" + key, e);
        }
    }

    @Override
    public void setString(String key, String value, int seconds) {
        try {
            jedisClusterFactory.getObject().set(key, value);
            if (seconds > -1) {
                jedisClusterFactory.getObject().expire(key, seconds);
            }
        } catch (Exception e) {
            logger.error(TimeUtil.getDateString(new Date()) + ":::::" + "setString" + key, e);
        }
    }

    @Override
    public String getString(String key) {
        String flag = null;
        try {
            flag = jedisClusterFactory.getObject().get(key);
        } catch (Exception e) {
            logger.error(TimeUtil.getDateString(new Date()) + ":::::" + "getString" + key, e);
        }
        return flag;
    }

    @Override
    public String getString(String key, int seconds) {
        String flag = null;
        try {
            flag = jedisClusterFactory.getObject().get(key);
            if (seconds > -1) {
                jedisClusterFactory.getObject().expire(key, seconds);
            }
        } catch (Exception e) {
            logger.error(TimeUtil.getDateString(new Date()) + ":::::" + "getString" + key, e);
        }
        return flag;
    }

    @Override
    public boolean exists(String key) {
        boolean flag = false;
        try {
            flag = jedisClusterFactory.getObject().exists(key);
        } catch (Exception e) {
            logger.error(TimeUtil.getDateString(new Date()) + ":::::" + "exists" + key, e);
        }
        return flag;
    }

    public JedisClusterFactory getJedisClusterFactory() {
        return jedisClusterFactory;
    }

    public void setJedisClusterFactory(JedisClusterFactory jedisClusterFactory) {
        this.jedisClusterFactory = jedisClusterFactory;
    }
}
