package com.redis.transaction.force;

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
 * Created by jiangwenping on 16/12/7.
 */
public class FroceTest {
    public static void main(String[] args) throws Exception {
        RGTConfigService RGTConfigService = new RGTConfigService();
        RGTRedisService RGTRedisService = new RGTRedisService();
        RGTRedisService.setJedisPool(RGTConfigService.initRedis(RGTConfigService.initRediPoolConfig()));

        TransactionService transactionService = new TransactionServiceImpl();

        String union = "union";
        String attchMent = "attchement";
        ForceEntity forceEntity = GameTransactionEntityFactoryImpl.createForceEntity(GameTransactionEntityCauseImpl.force, RGTRedisService, RedisKey.player, union, TimeUtil.SIX_HOUR_SECOND);
        forceEntity.getGameTransactionLockInterface().setContent(attchMent);
        GameTransactionCommitResult commitResult = transactionService.commitTransaction(GameTransactionCauseImpl.force, forceEntity);
        System.out.println(commitResult.getReuslt());
    }
}
