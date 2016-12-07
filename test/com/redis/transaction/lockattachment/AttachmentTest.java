package com.redis.transaction.lockattachment;

import com.redis.transaction.service.ConfigService;
import com.redis.transaction.service.RedisService;
import com.redis.transaction.service.TransactionService;
import com.redis.transaction.service.TransactionServiceImpl;

/**
 * Created by jiangwenping on 16/12/7.
 */
public class AttachmentTest {

    public static void main(String[] args) throws Exception {
        ConfigService configService = new ConfigService();
        RedisService redisService = new RedisService();
        redisService.setJedisPool(configService.initRedis(configService.initRediPoolConfig()));

        TransactionService transactionService = new TransactionServiceImpl();
    }
}
