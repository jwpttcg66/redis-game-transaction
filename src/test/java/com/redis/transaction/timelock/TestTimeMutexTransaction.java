package com.redis.transaction.timelock;

import com.redis.transaction.GameTransactionCauseImpl;
import com.redis.transaction.GameTransactionEntityCauseImpl;
import com.redis.transaction.GameTransactionEntityFactoryImpl;
import com.redis.transaction.RedisKey;
import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.service.RGTConfigService;
import com.redis.transaction.service.RGTRedisService;
import com.redis.transaction.service.TransactionService;
import com.redis.transaction.service.TransactionServiceImpl;

/**
 * Created by jiangwenping on 16/12/9.
 */
public class TestTimeMutexTransaction {
    public static void main(String[] args) throws Exception {

        RGTConfigService RGTConfigService = new RGTConfigService();
        RGTRedisService RGTRedisService = new RGTRedisService();
        RGTRedisService.setJedisPool(RGTConfigService.initRedis(RGTConfigService.initRediPoolConfig()));

        TransactionService transactionService = new TransactionServiceImpl();
        String union = "union";
        TestTimeMutexEntity testMutexEntity = GameTransactionEntityFactoryImpl.createTestTimeMutexEntity(GameTransactionEntityCauseImpl.time, RGTRedisService, RedisKey.player, union);
        GameTransactionCommitResult commitResult = transactionService.commitTransaction(GameTransactionCauseImpl.time, testMutexEntity);
        System.out.println(commitResult.getReuslt());
    }
}
