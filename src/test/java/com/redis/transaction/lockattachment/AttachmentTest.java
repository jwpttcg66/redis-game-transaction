package com.redis.transaction.lockattachment;

import com.redis.transaction.GameTransactionCauseImpl;
import com.redis.transaction.GameTransactionEntityCauseImpl;
import com.redis.transaction.GameTransactionEntityFactoryImpl;
import com.redis.transaction.RedisKey;
import com.redis.transaction.entity.CommonReadTransactionEnity;
import com.redis.transaction.entity.TestMutexEntity;
import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.service.RGTConfigService;
import com.redis.transaction.service.RGTRedisService;
import com.redis.transaction.service.TransactionService;
import com.redis.transaction.service.TransactionServiceImpl;

/**
 * Created by jiangwenping on 16/12/7.
 */
public class AttachmentTest {

    public static void main(String[] args) throws Exception {
        RGTConfigService RGTConfigService = new RGTConfigService();
        RGTRedisService RGTRedisService = new RGTRedisService();
        RGTRedisService.setJedisPool(RGTConfigService.initRedis(RGTConfigService.initRediPoolConfig()));

        TransactionService transactionService = new TransactionServiceImpl();

        String union = "union";
        String attchMent = "attchement";
        TestMutexEntity testMutexEntity = GameTransactionEntityFactoryImpl.createTestMutexEntity(GameTransactionEntityCauseImpl.attchment, RGTRedisService, RedisKey.player, union);
        testMutexEntity.getGameTransactionLockInterface().setContent(attchMent);
        GameTransactionCommitResult commitResult = transactionService.commitTransaction(GameTransactionCauseImpl.attchment, testMutexEntity);
        System.out.println(commitResult.getReuslt());

        CommonReadTransactionEnity commonReadTransactionEnity = GameTransactionEntityFactoryImpl.createNormalCommonReadTransactionEnity(GameTransactionEntityCauseImpl.attchment, RGTRedisService, RedisKey.player, union);
        commonReadTransactionEnity.getGameTransactionLockInterface().setContent(attchMent);
        commitResult = transactionService.commitTransaction(GameTransactionCauseImpl.read, commonReadTransactionEnity);
        System.out.println(commitResult.getReuslt());
    }
}
