package com.redis.transaction.service;

import com.redis.log.Loggers;
import com.redis.transaction.GameTransaction;
import com.redis.transaction.entity.AbstractGameTransactionEntity;
import com.redis.transaction.enums.GameTransactionCause;
import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.exception.GameTransactionException;
import org.slf4j.Logger;


/**
 * Created by jiangwenping on 16/12/6.
 */
public class TransactionServiceImpl implements TransactionService{


    protected final Logger logger = Loggers.transactionLogger;

    public GameTransactionCommitResult commitTransaction(GameTransactionCause gameTransactionCause, AbstractGameTransactionEntity... abstractGameTransactionEntity){
        GameTransactionCommitResult gameTransactionTryCommitResult = GameTransactionCommitResult.SUCCESS;
        GameTransaction gameTransaction = new GameTransaction(gameTransactionCause);
        for(int i = 0; i < abstractGameTransactionEntity.length; i++){
            gameTransaction.addEntity(abstractGameTransactionEntity[i]);
        }
        try {
            if(gameTransaction.createGameTransactionLock()){
                logger.info("获得" + gameTransaction.getTransactionInfo() + "锁成功");
                gameTransaction.trycommit();
                if(gameTransaction.canCommit()){
                    logger.info("提交" + gameTransaction.getTransactionInfo() + "事务");
                    gameTransaction.commit();
                    logger.info("提交" + gameTransaction.getTransactionInfo() + "事务成功");
                }else{//不可以提交
                    gameTransactionTryCommitResult = gameTransaction.getGameTransactionTryCommitResult();
                    logger.info("尝试提交" + gameTransaction.getTransactionInfo() + "事务失败");
                }

            }else{
                logger.info("获得" + gameTransaction.getTransactionInfo() + "锁失败");
                gameTransactionTryCommitResult = GameTransactionCommitResult.LOCK_ERROR;
            }
        } catch (Exception e) {
            logger.error("提交" + gameTransaction.getTransactionInfo() + "锁异常", e);
            try {
                gameTransaction.rollback();
            } catch (Exception e2) {
                logger.error("回滚" + gameTransaction.getTransactionInfo() + "锁异常", e);
            }
            gameTransactionTryCommitResult = GameTransactionCommitResult.COMMON_ERROR;
            if(e instanceof GameTransactionException){
                GameTransactionException gameTransactionException = (GameTransactionException) e;
                GameTransactionCommitResult tempGameTransactionTryCommitResult = gameTransactionException.getGameTransactionCommitResult();
                if(tempGameTransactionTryCommitResult != null){
                    gameTransactionTryCommitResult = tempGameTransactionTryCommitResult;
                }
            }
        }finally{
            logger.info("释放" + gameTransaction.getTransactionInfo() + "锁");
            gameTransaction.releaseGameTransactionLock();
        }

        return gameTransactionTryCommitResult;
    }


    public GameTransactionCommitResult commitTransaction(GameTransactionCause gameTransactionCause, long waitTime, AbstractGameTransactionEntity... abstractGameTransactionEntity){
        GameTransactionCommitResult gameTransactionTryCommitResult = GameTransactionCommitResult.SUCCESS;
        GameTransaction gameTransaction = new GameTransaction(gameTransactionCause, waitTime);
        for(int i = 0; i < abstractGameTransactionEntity.length; i++){
            gameTransaction.addEntity(abstractGameTransactionEntity[i]);
        }
        try {
            if(gameTransaction.createGameTransactionLock()){
                logger.info("获得" + gameTransaction.getTransactionInfo() + "锁成功");
                gameTransaction.trycommit();
                if(gameTransaction.canCommit()){
                    logger.info("提交" + gameTransaction.getTransactionInfo() + "事务");
                    gameTransaction.commit();
                    logger.info("提交" + gameTransaction.getTransactionInfo() + "事务成功");
                }else{//不可以提交
                    gameTransactionTryCommitResult = gameTransaction.getGameTransactionTryCommitResult();
                    logger.info("尝试提交" + gameTransaction.getTransactionInfo() + "事务失败");
                }

            }else{
                logger.info("获得" + gameTransaction.getTransactionInfo() + "锁失败");
                gameTransactionTryCommitResult = GameTransactionCommitResult.LOCK_ERROR;
            }
        } catch (Exception e) {
            logger.error("提交" + gameTransaction.getTransactionInfo() + "锁异常", e);
            try {
                gameTransaction.rollback();
            } catch (Exception e2) {
                logger.error("回滚" + gameTransaction.getTransactionInfo() + "锁异常", e);
            }
            gameTransactionTryCommitResult = GameTransactionCommitResult.COMMON_ERROR;
            if(e instanceof GameTransactionException){
                GameTransactionException gameTransactionException = (GameTransactionException) e;
                GameTransactionCommitResult tempGameTransactionTryCommitResult = gameTransactionException.getGameTransactionCommitResult();
                if(tempGameTransactionTryCommitResult != null){
                    gameTransactionTryCommitResult = tempGameTransactionTryCommitResult;
                }
            }
        }finally{
            logger.info("释放" + gameTransaction.getTransactionInfo() + "锁");
            gameTransaction.releaseGameTransactionLock();
        }

        return gameTransactionTryCommitResult;
    }

}

