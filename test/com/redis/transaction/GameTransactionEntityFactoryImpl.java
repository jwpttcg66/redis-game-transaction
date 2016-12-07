package com.redis.transaction;

import com.redis.transaction.entity.TestMutexEntity;
import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.factory.GameTransactionEntityFactory;
import com.redis.transaction.service.RedisService;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class GameTransactionEntityFactoryImpl extends GameTransactionEntityFactory {

    public  static TestMutexEntity createTestMutexEntity(GameTransactionEntityCause cause,RedisService redisService, String redisKey, String union){
        String key = GameTransactionKeyFactoryImpl.getPlayerTransactionEntityKey(cause, redisKey, union);
        TestMutexEntity testMutexEntity = new TestMutexEntity(cause, union, redisService);
        return testMutexEntity;
    }
}
