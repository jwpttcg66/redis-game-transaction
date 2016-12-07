package com.redis.transaction;

import com.redis.transaction.entity.TestMutexEntity;
import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.enums.GameTransactionLockType;
import com.redis.transaction.factory.GameTransactionEntityFactory;
import com.redis.transaction.force.ForceEntity;
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

    public  static ForceEntity createForceEntity(GameTransactionEntityCause cause,RedisService redisService, String redisKey, String union, int seconds){
        String key = GameTransactionKeyFactoryImpl.getPlayerTransactionEntityKey(cause, redisKey, union);
        ForceEntity forceEntity = new ForceEntity(cause, union, redisService, GameTransactionLockType.FORCE_WRITE_TIME, seconds);
        return forceEntity;
    }
}
