package com.redis.transaction.exception;

import com.redis.transaction.enums.GameTransactionCommitResult;

/**
 * Created by jiangwenping on 16/11/26.
 * 事务锁运行时异常
 */
public class GameTransactionException extends RuntimeException {

    /**
     * 事务执行出错原因
     */
    private GameTransactionCommitResult gameTransactionCommitResult;

    public GameTransactionException(){
        super();
    }

    public GameTransactionException(GameTransactionCommitResult gameTransactionCommitResult){
        super();
        this.gameTransactionCommitResult = gameTransactionCommitResult;
    }

    public GameTransactionException(String msg){
        super(msg);
    }

    public GameTransactionCommitResult getGameTransactionCommitResult() {
        return gameTransactionCommitResult;
    }

}
