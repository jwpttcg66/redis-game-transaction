package com.redis.transaction;

import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.factory.GameTransactionKeyFactory;
import com.redis.config.GlobalConstants;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class GameTransactionKeyFactoryImpl extends GameTransactionKeyFactory {
    /**
     * 获取玩家锁
     * @param cause
     * @return
     */
    public static String getPlayerTransactionEntityKey(GameTransactionEntityCause cause, String redisKey, String union){
        return redisKey + cause.getCause() + GlobalConstants.Strings.commonSplitString + union;
    }


}
