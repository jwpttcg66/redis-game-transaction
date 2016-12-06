package com.redis.transaction.entity;

import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.service.ConfigService;
import com.redis.transaction.service.RedisService;
import com.redis.transaction.service.TransactionService;
import com.redis.transaction.service.TransactionServiceImpl;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class TestMutexTransaction {
    public static void main(String[] args) throws Exception {

        ConfigService configService = new ConfigService();
        RedisService redisService = new RedisService();
        redisService.setJedisPool(configService.initRedis(configService.initRediPoolConfig()));

        TransactionService transactionService = new TransactionServiceImpl();
        String union = "union";
        TestMutexEntity testMutexEntity = GameTransactionEntityFactoryImpl.createTestMutexEntity(GameTransactionEntityCauseImpl.test, union, redisService);
        GameTransactionCommitResult commitResult = transactionService.commitTransaction(GameTransactionCauseImpl.test, testMutexEntity);
        System.out.println(commitResult.getReuslt());
    }
}
