package com.redis.transaction;

import com.redis.transaction.enums.GameTransactionCause;
import com.redis.transaction.exception.GameTransactionException;

/**
 * Created by jiangwenping on 16/12/6.
 */
public interface GameTransactionInterface {

    /** 激活，构造*/
    public static final int ACTIVE = 0;
    /** 尝试提交*/
    public static final int TRYCOMMITED = 1;
    /** 正式提交*/
    public static final int COMMITED = 2;
    /** 正式回滚*/
    public static final int ROLLEDBACK = 3;

    /**
     * 事务提交
     * @throws GameTransactionException
     */
    public void commit() throws GameTransactionException;

    /**
     * 事务回滚
     * @throws GameTransactionException
     */
    public void rollback() throws GameTransactionException;

    /**
     * 是否可以提交
     * @return
     */
    public boolean canCommit();

    /**
     * 尝试性提交
     */
    public void trycommit() throws GameTransactionException;

    /**
     * 获取事务原因
     * @return
     */
    public GameTransactionCause getCause();

    /**
     * 是否可以创建锁
     * @return
     */
    public boolean createGameTransactionLock() throws GameTransactionException;


    /**
     * 释放锁
     * @return
     */
    public void releaseGameTransactionLock();
}
