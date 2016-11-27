package com.redis.transaction.entity;

import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.exception.GameTransactionException;
import com.redis.transaction.lock.GameTransactionLockInterface;

/**
 * Created by jiangwenping on 16/11/26.
 * 游戏事务实体接口
 */
public interface GameTransactionEntityInterface {

    /**
     * 执行
     */
    public void commit() throws GameTransactionException;

    /**
     * 回滚
     */
    public void rollback() throws GameTransactionException;

    /**
     * 尝试性提交
     */
    public GameTransactionCommitResult trycommit() throws GameTransactionException;


    /**
     * 是否可以创建锁
     * @return
     */
    public boolean createGameTransactionLock(long seconds)  throws GameTransactionException;

    /**
     * 释放锁
     * @return
     */
    public void releaseGameTransactionLock();

    /**
     * 强制释放锁
     * @return
     */
    public void foreceReleaseGameTransactionLock();

    public String getInfo();

    /**
     * 是否需要执行
     * @return
     */
    public boolean needCommit();

    /**
     * 获取锁内容
     * @return
     */
    public GameTransactionLockInterface getGameTransactionLockInterface();
}

