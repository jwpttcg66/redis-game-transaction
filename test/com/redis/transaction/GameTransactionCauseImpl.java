package com.redis.transaction;

import com.redis.transaction.enums.GameTransactionCause;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class GameTransactionCauseImpl extends GameTransactionCause{
    public static GameTransactionCauseImpl test = new GameTransactionCauseImpl("testTransAction");
    public static GameTransactionCauseImpl read = new GameTransactionCauseImpl("readTransAction");
    public static GameTransactionCauseImpl attchment = new GameTransactionCauseImpl("attchmentTransAction");
    public GameTransactionCauseImpl(String cause) {
        super(cause);
    }
}
