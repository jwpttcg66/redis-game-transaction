package com.redis.transaction.entity;

import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.exception.GameTransactionException;
import com.redis.transaction.service.IRGTRedisService;

/**
 * Created by jiangwenping on 16/12/6.
 * 设置一个数据到redis
 */
public class TestMutexEntity extends AbstractGameTransactionEntity{

    private IRGTRedisService redisService;
    public TestMutexEntity(GameTransactionEntityCause cause, String key, IRGTRedisService redisService) {
        super(cause, key, redisService);
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
