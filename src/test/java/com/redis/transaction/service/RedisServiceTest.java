package com.redis.transaction.service;

import org.jdom.DataConversionException;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class RedisServiceTest {
    public static void main(String[] args) throws DataConversionException {
        RGTConfigService RGTConfigService = new RGTConfigService();
        RGTRedisService RGTRedisService = new RGTRedisService();
        RGTRedisService.setJedisPool(RGTConfigService.initRedis(RGTConfigService.initRediPoolConfig()));

        String testKey = "ketest100";
        RGTRedisService.setString(testKey, "100");
        String number = RGTRedisService.getString(testKey);
        System.out.println(number);
    }
}
