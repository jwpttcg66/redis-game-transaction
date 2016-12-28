package com.redis.transaction;

import com.redis.transaction.enums.GameTransactionEntityCause;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class GameTransactionEntityCauseImpl extends GameTransactionEntityCause{
    public static final GameTransactionEntityCause test = new GameTransactionEntityCauseImpl("test");
    public static final GameTransactionEntityCause read = new GameTransactionEntityCauseImpl("read");
    public static final GameTransactionEntityCause attchment = new GameTransactionEntityCauseImpl("attchment");
    public static final GameTransactionEntityCause force = new GameTransactionEntityCauseImpl("force");
    public static final GameTransactionEntityCause time = new GameTransactionEntityCauseImpl("time");
    public static final GameTransactionEntityCause wait = new GameTransactionEntityCauseImpl("wait");
    public static final GameTransactionEntityCause rollback = new GameTransactionEntityCauseImpl("rollback");
    public GameTransactionEntityCauseImpl(String gameTransactionEntityCause)
    {
        super(gameTransactionEntityCause);
    }

}
