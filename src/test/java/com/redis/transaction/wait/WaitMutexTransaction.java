package com.redis.transaction.wait;

import com.redis.transaction.GameTransactionCauseImpl;
import com.redis.transaction.GameTransactionEntityCauseImpl;
import com.redis.transaction.GameTransactionEntityFactoryImpl;
import com.redis.transaction.RedisKey;
import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.service.RGTConfigService;
import com.redis.transaction.service.RGTRedisService;
import com.redis.transaction.service.TransactionService;
import com.redis.transaction.service.TransactionServiceImpl;
import com.redis.util.TimeUtil;

/**
 * Created by jiangwenping on 16/12/15.
 */
public class WaitMutexTransaction {
    public static void main(String[] args) throws Exception {

        RGTConfigService RGTConfigService = new RGTConfigService();
        RGTRedisService RGTRedisService = new RGTRedisService();
        RGTRedisService.setJedisPool(RGTConfigService.initRedis(RGTConfigService.initRediPoolConfig()));

        TransactionService transactionService = new TransactionServiceImpl();
        String union = "union";
        long waitTime = TimeUtil.MINUTE_SECOND;
        WaitMutexEntity waitMutexTransaction = GameTransactionEntityFactoryImpl.createWaitMutexEntity(GameTransactionEntityCauseImpl.wait, RGTRedisService, RedisKey.player, union);
        GameTransactionCommitResult commitResult = transactionService.commitTransaction(GameTransactionCauseImpl.wait, waitTime, waitMutexTransaction);
        System.out.println(commitResult.getReuslt());
    }
}
