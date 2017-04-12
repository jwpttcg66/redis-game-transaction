package com.redis.transaction.lock;

import com.redis.log.Loggers;
import com.redis.transaction.enums.GameTransactionEntityCause;
import com.redis.transaction.enums.GameTransactionLockStateEnum;
import com.redis.transaction.exception.GameTransactionException;
import com.redis.transaction.service.IRGTRedisService;
import com.redis.util.StringUtils;
import org.slf4j.Logger;

/**
 * Created by jiangwenping on 16/11/26.
 * 读锁
 */
public class GameTransactionReadLock implements GameTransactionLockInterface {

    protected static Logger logger = Loggers.lockLogger;

    /** 对应的锁key*/
    private String lockKey;

    /** redis*/
    private IRGTRedisService redisService;

    /**事务原因*/
    private GameTransactionEntityCause gameTransactionEntityCause;

    /**锁定状态 */
    private GameTransactionLockStateEnum lockState;

    /**
     * 锁内容
     */
    private String lockContent="";

    public GameTransactionReadLock(String lockKey, IRGTRedisService redisService, GameTransactionEntityCause gameTransactionEntityCause) {
        super();
        this.lockKey = lockKey;
        this.redisService = redisService;
        this.gameTransactionEntityCause = gameTransactionEntityCause;
        this.lockState = GameTransactionLockStateEnum.init;
    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean create(long seconds) throws GameTransactionException {
        this.lockState =  GameTransactionLockStateEnum.create;
        String realLockKey = getLockKey(lockKey, gameTransactionEntityCause);
        logger.info("read  realLockKey:" + realLockKey);
        boolean detectflag= redisService.exists(realLockKey);
        if(detectflag && !StringUtils.isEmptyString(this.lockContent)){
            detectflag = checkContent();
        }
        return detectflag;
    }

    @Override
    public String getInfo() {
        return lockKey + StringUtils.DEFAULT_SPLIT + gameTransactionEntityCause.getCause()+ StringUtils.DEFAULT_SPLIT + this.lockState;
    }


    /**
     * 获取锁可以
     * @param lockKey
     * @param GameTransactionEntityCause
     * @return
     */
    public String getLockKey(String lockKey, GameTransactionEntityCause GameTransactionEntityCause){
        return lockKey + "#" + gameTransactionEntityCause.getCause();
    }

    @Override
    public void setContent(String lockContent) {
        this.lockContent = lockContent;
    }

    /**
     * 检查锁内容
     * @return
     */
    public boolean checkContent(){
        boolean checkFlag = false;
        String realLockKey = getLockKey(lockKey, gameTransactionEntityCause);
        String content = redisService.getString(realLockKey);
        if(!StringUtils.isEmptyString(content)){
            logger.info("read content realLockKey:" + realLockKey);
            checkFlag = content.equals(this.lockContent );
        }

        return checkFlag;
    }

}
