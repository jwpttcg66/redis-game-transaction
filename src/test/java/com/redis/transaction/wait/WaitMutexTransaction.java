package com.redis.transaction.wait;

import com.redis.transaction.GameTransactionCauseImpl;
import com.redis.transaction.GameTransactionEntityCauseImpl;
import com.redis.transaction.GameTransactionEntityFactoryImpl;
import com.redis.transaction.RedisKey;
import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.service.ConfigService;
import com.redis.transaction.service.RedisService;
import com.redis.transaction.service.TransactionService;
import com.redis.transaction.service.TransactionServiceImpl;
import com.redis.util.TimeUtil;

/**
 * Created by jiangwenping on 16/12/15.
 */
public class WaitMutexTransaction {
    public static void main(String[] args) throws Exception {

        ConfigService configService = new ConfigService();
        RedisService redisService = new RedisService();
        redisService.setJedisPool(configService.initRedis(configService.initRediPoolConfig()));

        TransactionService transactionService = new TransactionServiceImpl();
        String union = "union";
        long waitTime = TimeUtil.MINUTE_SECOND;
        WaitMutexEntity waitMutexTransaction = GameTransactionEntityFactoryImpl.createWaitMutexEntity(GameTransactionEntityCauseImpl.wait, redisService, RedisKey.player, union);
        GameTransactionCommitResult commitResult = transactionService.commitTransaction(GameTransactionCauseImpl.wait, waitTime, waitMutexTransaction);
        System.out.println(commitResult.getReuslt());
    }
}
