package com.redis.transaction.entity;

import com.redis.log.Loggers;
import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.enums.GameTransactionLockType;
import com.redis.transaction.exception.GameTransactionException;
import com.redis.transaction.lock.GameTransactionLock;
import com.redis.transaction.lock.GameTransactionLockInterface;
import com.redis.transaction.lock.GameTransactionReadLock;
import com.redis.transaction.service.IRGTRedisService;
import org.slf4j.Logger;

import java.util.BitSet;

/**
 * Created by jiangwenping on 16/11/26.
 * 抽象事务实体
 */
public abstract class AbstractGameTransactionEntity implements GameTransactionEntityInterface {

    protected static Logger transactionLogger = Loggers.transactionLogger;

    /**
     * 进度设置集合 主要用于rollback
     */
    private BitSet progressBitSet;

    /**
     * 事务锁
     */
    private com.redis.transaction.lock.GameTransactionLockInterface gameTransactionLock;

    /**
     * 锁类型
     */
    private GameTransactionLockType gameTransactionLockType;

    /**
     * 锁的正向标志(主要用于读取的时候)
     */
    private boolean rejectFlag = false;


    public AbstractGameTransactionEntity(GameTransactionEntityCause cause, String key, IRGTRedisService redisService) {
        this.progressBitSet = new BitSet();
        this.gameTransactionLock = new GameTransactionLock(key, redisService, cause);
        this.gameTransactionLockType = GameTransactionLockType.WRITE;
    }

    public AbstractGameTransactionEntity(GameTransactionEntityCause cause, String key, IRGTRedisService redisService, GameTransactionLockType gameTransactionLockType) {
        this.progressBitSet = new BitSet();
        if (gameTransactionLockType.equals(GameTransactionLockType.READ)) {
            this.gameTransactionLock = new GameTransactionReadLock(key, redisService, cause);
            this.gameTransactionLockType = GameTransactionLockType.READ;
        } else {
            this.gameTransactionLock = new GameTransactionLock(key, redisService, cause);
            this.gameTransactionLockType = gameTransactionLockType;
        }
    }


    /**
     * @param cause
     * @param key
     * @param redisService
     * @param readLock
     * @param lockTime     此参数只针对 非readlock锁
     */
    public AbstractGameTransactionEntity(GameTransactionEntityCause cause, String key, IRGTRedisService redisService, GameTransactionLockType gameTransactionLockType, long lockTime) {
        this.progressBitSet = new BitSet();
        if (gameTransactionLockType.equals(GameTransactionLockType.READ)) {
            this.gameTransactionLock = new GameTransactionReadLock(key, redisService, cause);
            this.gameTransactionLockType = GameTransactionLockType.READ;
        } else if (gameTransactionLockType.equals(GameTransactionLockType.FORCE_WRITE_TIME)) {
            this.gameTransactionLock = new GameTransactionLock(key, redisService, cause, lockTime, true);
            this.gameTransactionLockType = gameTransactionLockType;
        } else {
            this.gameTransactionLock = new GameTransactionLock(key, redisService, cause, lockTime, false);
            this.gameTransactionLockType = gameTransactionLockType;
        }
    }

    /**
     * 设置提交事务的提交进度，用于回滚
     *
     * @param step
     */
    public void setCommitProgress(int step) throws GameTransactionException {
        if (checkCommitProgress(step)) {
            throw new GameTransactionException("progress has exsited");
        }
        progressBitSet.set(step);
    }

    /**
     * 检测当前进度是否设置过
     *
     * @param step
     * @return
     */
    public boolean checkCommitProgress(int step) {
        return progressBitSet.get(step);
    }

    /**
     * 清除进度
     *
     * @param step
     */
    public void cleanCommitProgress(int step) {
        progressBitSet.set(step, false);
    }

    @Override
    public boolean createGameTransactionLock(long seconds) throws GameTransactionException {
        boolean result = gameTransactionLock.create(seconds);
        if (rejectFlag) {
            result = !result;
        }
        return result;
    }

    @Override
    public void releaseGameTransactionLock() {
        //时间占有锁，不释放锁
        if (gameTransactionLockType.equals(GameTransactionLockType.WRITE_TIME)
                || gameTransactionLockType.equals(GameTransactionLockType.FORCE_WRITE_TIME)) {
            return;
        }
        gameTransactionLock.destroy();
    }

    @Override
    public void foreceReleaseGameTransactionLock() {
        gameTransactionLock.destroy();
    }


    public String getInfo() {
        return gameTransactionLockType + "类型" + gameTransactionLock.getInfo();
    }

    /**
     * 是否需要执行
     *
     * @return
     */
    public boolean needCommit() {
        return !gameTransactionLockType.equals(GameTransactionLockType.READ);

    }

    public boolean isRejectFlag() {
        return rejectFlag;
    }

    public void setRejectFlag(boolean rejectFlag) {
        this.rejectFlag = rejectFlag;
    }

    public GameTransactionLockInterface getGameTransactionLockInterface() {
        return this.gameTransactionLock;
    }
}
