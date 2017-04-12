package com.redis.transaction.rollback;

import com.redis.transaction.entity.AbstractGameTransactionEntity;
import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.exception.GameTransactionException;
import com.redis.transaction.service.IRGTRedisService;

/**
 * Created by jiangwenping on 16/12/28.
 */
public class RollbackMutexEntity  extends AbstractGameTransactionEntity {

    private IRGTRedisService redisService;

    public RollbackMutexEntity(GameTransactionEntityCause cause, String key, IRGTRedisService redisService) {
        super(cause, key, redisService);
        this.redisService = redisService;

    }
    @Override
    public void commit() throws GameTransactionException {
        String testRedisKey =  "testRedis";
        redisService.setString(testRedisKey, "1000");
        setCommitProgress(1);
        throw new GameTransactionException();
    }

    @Override
    public void rollback() throws GameTransactionException {
        if(checkCommitProgress(1)){
            String testRedisKey =  "testRedis";
            redisService.setString(testRedisKey, "500");
        }
    }

    @Override
    public GameTransactionCommitResult trycommit() throws GameTransactionException {
        return GameTransactionCommitResult.SUCCESS;
    }
}
