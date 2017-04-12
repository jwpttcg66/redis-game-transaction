package com.redis.transaction.force;

import com.redis.transaction.entity.AbstractGameTransactionEntity;
import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.enums.GameTransactionLockType;
import com.redis.transaction.exception.GameTransactionException;
import com.redis.transaction.service.IRGTRedisService;

/**
 * Created by jiangwenping on 16/12/7.
 */
public class ForceEntity extends AbstractGameTransactionEntity {

    public ForceEntity(GameTransactionEntityCause cause, String key, IRGTRedisService redisService, GameTransactionLockType gameTransactionLockType, int seconds) {
        super(cause, key, redisService, gameTransactionLockType, seconds);
    }

    @Override
    public void commit() throws GameTransactionException {

    }

    @Override
    public void rollback() throws GameTransactionException {

    }

    @Override
    public GameTransactionCommitResult trycommit() throws GameTransactionException {
        return GameTransactionCommitResult.SUCCESS;
    }
}
