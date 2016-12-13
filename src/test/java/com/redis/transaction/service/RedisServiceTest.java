package com.redis.transaction.service;

import org.jdom.DataConversionException;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class RedisServiceTest {
    public static void main(String[] args) throws DataConversionException {
        ConfigService configService = new ConfigService();
        RedisService redisService = new RedisService();
        redisService.setJedisPool(configService.initRedis(configService.initRediPoolConfig()));

        String testKey = "ketest100";
        redisService.setString(testKey, "100");
        String number = redisService.getString(testKey);
        System.out.println(number);
    }
}
