package com.redis.transaction.factory;

import com.redis.transaction.entity.CommonReadTransactionEnity;
import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.service.IRGTRedisService;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class GameTransactionEntityFactory {

    /**
     * 获取通用读锁实体 默认不能读取到
     *
     * @return
     */
    public static CommonReadTransactionEnity createCommonReadRejectTransactionEnity(
            GameTransactionEntityCause cause, IRGTRedisService redisService,
            String redisKey, String unionKey) {
        CommonReadTransactionEnity commonReadTransactionEnity = createNormalCommonReadTransactionEnity(cause, redisService, redisKey, unionKey);
        commonReadTransactionEnity.setRejectFlag(true);
        return commonReadTransactionEnity;
    }

    /**
     * 获取通用读锁实体 默认需要读取到
     *
     * @return
     */
    public static CommonReadTransactionEnity createNormalCommonReadTransactionEnity(
            GameTransactionEntityCause cause, IRGTRedisService redisService, String redisKey,
            String unionKey) {
        String key = GameTransactionKeyFactory.getCommonTransactionEntityKey(
                cause, redisKey, unionKey);
        return new CommonReadTransactionEnity(cause, key, redisService);
    }
}
