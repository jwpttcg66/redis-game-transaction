package com.redis.transaction.entity;

import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.factory.GameTransactionKeyFactory;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class GameTransactionKeyFactoryImpl extends GameTransactionKeyFactory {
    /**
     * 获取玩家锁
     * @param cause
     * @param playerId
     * @param platformType
     * @return
     */
    public static String getPlayerTransactionEntityKey(GameTransactionEntityCause cause, String union){
        return RedisKey.player + union;
    }


}
