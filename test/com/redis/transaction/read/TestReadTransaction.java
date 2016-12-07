package com.redis.transaction.read;

import com.redis.transaction.GameTransactionCauseImpl;
import com.redis.transaction.GameTransactionEntityCauseImpl;
import com.redis.transaction.GameTransactionEntityFactoryImpl;
import com.redis.transaction.RedisKey;
import com.redis.transaction.entity.CommonReadTransactionEnity;
import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.service.ConfigService;
import com.redis.transaction.service.RedisService;
import com.redis.transaction.service.TransactionService;
import com.redis.transaction.service.TransactionServiceImpl;

/**
 * Created by jiangwenping on 16/12/7.
 */
public class TestReadTransaction {
    public static void main(String[] args) throws Exception {
        ConfigService configService = new ConfigService();
        RedisService redisService = new RedisService();
        redisService.setJedisPool(configService.initRedis(configService.initRediPoolConfig()));

        TransactionService transactionService = new TransactionServiceImpl();
        String union = "union";
        CommonReadTransactionEnity commonReadTransactionEnity = GameTransactionEntityFactoryImpl.createNormalCommonReadTransactionEnity(GameTransactionEntityCauseImpl.read, redisService, RedisKey.common, union);
        GameTransactionCommitResult commitResult = transactionService.commitTransaction(GameTransactionCauseImpl.read, commonReadTransactionEnity);
        System.out.println(commitResult.getReuslt());

        CommonReadTransactionEnity commonRejectReadTransactionEnity = GameTransactionEntityFactoryImpl.createCommonReadRejectTransactionEnity(GameTransactionEntityCauseImpl.read, redisService, RedisKey.common, union);
        commitResult = transactionService.commitTransaction(GameTransactionCauseImpl.read, commonRejectReadTransactionEnity);
        System.out.println(commitResult.getReuslt());
    }
}
