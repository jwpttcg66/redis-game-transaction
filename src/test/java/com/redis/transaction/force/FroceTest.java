package com.redis.transaction.force;

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
 * Created by jiangwenping on 16/12/7.
 */
public class FroceTest {
    public static void main(String[] args) throws Exception {
        ConfigService configService = new ConfigService();
        RedisService redisService = new RedisService();
        redisService.setJedisPool(configService.initRedis(configService.initRediPoolConfig()));

        TransactionService transactionService = new TransactionServiceImpl();

        String union = "union";
        String attchMent = "attchement";
        ForceEntity forceEntity = GameTransactionEntityFactoryImpl.createForceEntity(GameTransactionEntityCauseImpl.force, redisService, RedisKey.player, union, TimeUtil.SIX_HOUR_SECOND);
        forceEntity.getGameTransactionLockInterface().setContent(attchMent);
        GameTransactionCommitResult commitResult = transactionService.commitTransaction(GameTransactionCauseImpl.force, forceEntity);
        System.out.println(commitResult.getReuslt());
    }
}
