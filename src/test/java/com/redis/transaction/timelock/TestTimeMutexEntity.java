package com.redis.transaction.timelock;

import com.redis.transaction.entity.AbstractGameTransactionEntity;
import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.enums.GameTransactionLockType;
import com.redis.transaction.exception.GameTransactionException;
import com.redis.transaction.service.IRGTRedisService;
import com.redis.util.TimeUtil;

/**
 * Created by jiangwenping on 16/12/9.
 */
public class TestTimeMutexEntity extends AbstractGameTransactionEntity {

    private IRGTRedisService redisService;
    public TestTimeMutexEntity(GameTransactionEntityCause cause, String key, IRGTRedisService redisService) {
        super(cause, key, redisService, GameTransactionLockType.WRITE_TIME, TimeUtil.FIVE_MINUTE);
        this.redisService = redisService;

    }

    @Override
    public void commit() throws GameTransactionException {
        String testRedisKey =  "testRedis";
        redisService.setString(testRedisKey, "1000");
    }

    @Override
    public void rollback() throws GameTransactionException {

    }

    @Override
    public GameTransactionCommitResult trycommit() throws GameTransactionException {
//        String testRedisKey =  "testRedis";
//        if(redisService.getString(testRedisKey).equals("1000")){
//            return GameTransactionCommitResult.COMMON_ERROR;
//        }
        return GameTransactionCommitResult.SUCCESS;
    }

}

