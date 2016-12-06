package com.redis.transaction.entity;

import com.redis.transaction.enums.GameTransactionCause;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class GameTransactionCauseImpl extends GameTransactionCause{
    public static GameTransactionCauseImpl test = new GameTransactionCauseImpl("testTransAction");
    private String cause;

    public GameTransactionCauseImpl(String cause) {
        this.cause = cause;
    }
}
