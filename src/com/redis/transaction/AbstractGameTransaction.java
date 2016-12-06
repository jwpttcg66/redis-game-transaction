package com.redis.transaction;

/**
 * Created by jiangwenping on 16/12/6.
 */

import com.redis.transaction.entity.GameTransactionEntityInterface;
import com.redis.transaction.enums.GameTransactionCause;
import com.redis.transaction.enums.GameTransactionCommitResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author C172
 * 抽象游戏事务
 */
public abstract class AbstractGameTransaction implements GameTransactionInterface{

    /** 当前执行状态*/
    protected int state;
    /** 事务实体*/
    protected List<GameTransactionEntityInterface> entities;
    /** 事务原因*/
    private GameTransactionCause cause;

    /** 游戏事务提交结果*/
    protected GameTransactionCommitResult gameTransactionTryCommitResult;

    public AbstractGameTransaction(GameTransactionCause cause){
        this.cause = cause;
        this.entities = new ArrayList<GameTransactionEntityInterface>();
        gameTransactionTryCommitResult = GameTransactionCommitResult.SUCCESS;
        this.state = ACTIVE;
    }

    public void addEntity(GameTransactionEntityInterface entity){
        entities.add(entity);
    }

    @Override
    public GameTransactionCause getCause() {
        return cause;
    }

    @Override
    public boolean canCommit() {
        return gameTransactionTryCommitResult.equals(GameTransactionCommitResult.SUCCESS);
    }

    public GameTransactionCommitResult getGameTransactionTryCommitResult() {
        return gameTransactionTryCommitResult;
    }


}

