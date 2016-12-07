package com.redis.transaction;

import com.redis.transaction.enums.GameTransactionEntityCause;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class GameTransactionEntityCauseImpl extends GameTransactionEntityCause{
    public static final GameTransactionEntityCause test = new GameTransactionEntityCauseImpl("test");

    public GameTransactionEntityCauseImpl(String gameTransactionEntityCause)
    {
        super(gameTransactionEntityCause);
    }

}
