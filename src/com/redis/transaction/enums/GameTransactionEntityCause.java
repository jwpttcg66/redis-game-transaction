package com.redis.transaction.enums;

/**
 * Created by jiangwenping on 16/11/26.
 * 游戏实体原因
 */
public class GameTransactionEntityCause {

    private String cause;

    public GameTransactionEntityCause(String cause) {
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
