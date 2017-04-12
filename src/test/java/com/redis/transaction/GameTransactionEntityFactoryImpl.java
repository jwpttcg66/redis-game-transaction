package com.redis.transaction;

import com.redis.transaction.entity.TestMutexEntity;
import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.enums.GameTransactionLockType;
import com.redis.transaction.factory.GameTransactionEntityFactory;
import com.redis.transaction.force.ForceEntity;
import com.redis.transaction.rollback.RollbackMutexEntity;
import com.redis.transaction.service.IRGTRedisService;
import com.redis.transaction.timelock.TestTimeMutexEntity;
import com.redis.transaction.wait.WaitMutexEntity;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class GameTransactionEntityFactoryImpl extends GameTransactionEntityFactory {

    public  static TestMutexEntity createTestMutexEntity(GameTransactionEntityCause cause,IRGTRedisService redisService, String redisKey, String union){
        String key = GameTransactionKeyFactoryImpl.getPlayerTransactionEntityKey(cause, redisKey, union);
        TestMutexEntity testMutexEntity = new TestMutexEntity(cause, union, redisService);
        return testMutexEntity;
    }

    public  static ForceEntity createForceEntity(GameTransactionEntityCause cause,IRGTRedisService redisService, String redisKey, String union, int seconds){
        String key = GameTransactionKeyFactoryImpl.getPlayerTransactionEntityKey(cause, redisKey, union);
        ForceEntity forceEntity = new ForceEntity(cause, union, redisService, GameTransactionLockType.FORCE_WRITE_TIME, seconds);
        return forceEntity;
    }

    public  static TestTimeMutexEntity createTestTimeMutexEntity(GameTransactionEntityCause cause,IRGTRedisService redisService, String redisKey, String union){
        String key = GameTransactionKeyFactoryImpl.getPlayerTransactionEntityKey(cause, redisKey, union);
        TestTimeMutexEntity testTimeMutexEntity = new TestTimeMutexEntity(cause, union, redisService);
        return testTimeMutexEntity;
    }

    public  static WaitMutexEntity createWaitMutexEntity(GameTransactionEntityCause cause,IRGTRedisService redisService, String redisKey, String union){
        String key = GameTransactionKeyFactoryImpl.getPlayerTransactionEntityKey(cause, redisKey, union);
        WaitMutexEntity waitTimeMutexEntity = new WaitMutexEntity(cause, union, redisService);
        return waitTimeMutexEntity;
    }

    public  static RollbackMutexEntity createRollbackMutexEntityy(GameTransactionEntityCause cause,IRGTRedisService redisService, String redisKey, String union){
        String key = GameTransactionKeyFactoryImpl.getPlayerTransactionEntityKey(cause, redisKey, union);
        RollbackMutexEntity rollbackMutexEntity = new RollbackMutexEntity(cause, union, redisService);
        return rollbackMutexEntity;
    }
}
