package com.redis.transaction.service;

import com.redis.util.FileUtil;
import com.redis.config.GlobalConstants;
import com.redis.util.JdomUtils;
import com.redis.util.StringUtils;
import org.jdom.DataConversionException;
import org.jdom.Element;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by jiangwenping on 16/11/29.
 */
public class RGTConfigService {

    public JedisPoolConfig initRediPoolConfig() throws DataConversionException {
        Element element = JdomUtils.getRootElemet(FileUtil.getConfigURL(GlobalConstants.RedisConfigFile.REDIS_POOL_CONIFG).getFile());
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        int maxIdle = element.getAttribute("maxIdle").getIntValue();
        boolean testWhileIdle = element.getAttribute("testWhileIdle").getBooleanValue();
        int timeBetweenEvictionRunsMillis = element.getAttribute("timeBetweenEvictionRunsMillis").getIntValue();
        int numTestsPerEvictionRun = element.getAttribute("numTestsPerEvictionRun").getIntValue();
        int minEvictableIdleTimeMillis = element.getAttribute("minEvictableIdleTimeMillis").getIntValue();
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        return jedisPoolConfig;
    }

    public JedisPool initRedis(JedisPoolConfig jedisPoolConfig) throws DataConversionException {
        Element element = JdomUtils.getRootElemet(FileUtil.getConfigURL(GlobalConstants.RedisConfigFile.REDIS).getFile());
        String host = element.getAttribute("host").getValue();
        int port = element.getAttribute("port").getIntValue();
        boolean hasPassword = element.getAttribute("password") != null;
        int database = element.getAttribute("database").getIntValue();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port);
        if (hasPassword) {
            int timeout = element.getAttribute("timeout").getIntValue();
            String password = element.getAttribute("password").getValue();
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
        }
        return jedisPool;
    }
}
