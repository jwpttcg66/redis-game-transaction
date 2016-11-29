package com.redis.transaction;

import com.redis.transaction.service.ConfigService;
import com.redis.transaction.service.RedisService;

/**
 * Created by jiangwenping on 16/11/29.
 */
public class Main {
    public static void main(String[] args) throws  Exception{
        ConfigService configService = new ConfigService();
        RedisService redisService = new RedisService();
        redisService.setJedisPool(configService.initRedis(configService.initRediPoolConfig()));

        String testKey = "ketest100";
        redisService.setString(testKey, "100");
        String number = redisService.getString(testKey);
        System.out.println(number);
    }
}
