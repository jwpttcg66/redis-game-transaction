package com.redis.transaction;

import com.redis.transaction.entity.GameTransactionEntityInterface;
import com.redis.transaction.enums.GameTransactionCause;
import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.exception.GameTransactionException;
import com.redis.util.TimeUtil;

/**
 * Created by jiangwenping on 16/12/6.
 */
public class GameTransaction extends AbstractGameTransaction {

    private long waitTime;

    public GameTransaction(GameTransactionCause cause){
        super(cause);
    }

    public GameTransaction(GameTransactionCause cause, long waitTime){
        super(cause);
        this.waitTime = waitTime;
    }


    public void commit() throws GameTransactionException {
        if(state != TRYCOMMITED)
            throw new GameTransactionException();
        this.state = COMMITED;
        for(GameTransactionEntityInterface entity:entities){
            if(!entity.needCommit()){
                continue;
            }
            entity.commit();
        }
    }

    public void rollback() throws GameTransactionException {
        //无条件进行回滚
        this.state = ROLLEDBACK;
        for(GameTransactionEntityInterface entity:entities){
            entity.rollback();
        }
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[PLAYERTRANSACTION]");
        for(GameTransactionEntityInterface entity:entities){
            sb.append(entity.toString());
        }
        return sb.toString();
    }

    @Override
    public void trycommit() throws GameTransactionException {
        if (state != ACTIVE)
            throw new GameTransactionException("GameTransaction is active, can't trycommit");
        this.state = TRYCOMMITED;
        for(GameTransactionEntityInterface entity:entities){
            if(!entity.needCommit()){
                continue;
            }
            GameTransactionCommitResult gameTransactionEntityTryCommitResult = entity.trycommit();
            if(!gameTransactionEntityTryCommitResult.equals(GameTransactionCommitResult.SUCCESS)){
                this.gameTransactionTryCommitResult = gameTransactionEntityTryCommitResult;
                break;
            }
        }
    }


    @Override
    public boolean createGameTransactionLock()  throws GameTransactionException{
        if(state != ACTIVE)
            throw new GameTransactionException();
        boolean creatflag = true;
        long startSecond = TimeUtil.getSeconds();
        if(waitTime > 0){
            for(;;){
                long currSeconds = TimeUtil.getSeconds();
                creatflag = createOnceGameTransactionLock(currSeconds);
                if(creatflag = true){
                    break;
                }

                try {
                    Thread.sleep(TimeUtil.SECOND);
                }catch (Throwable e){

                }

                currSeconds = TimeUtil.getSeconds();
                if(startSecond + waitTime < currSeconds){
                    creatflag = false;
                    break;
                }
            }
        }else {
            long seconds = TimeUtil.getSeconds();
            creatflag = createOnceGameTransactionLock(startSecond);
        }
        return creatflag;
    }

    public boolean createOnceGameTransactionLock(long seconds)throws GameTransactionException{
        boolean creatFlag = false;
        for (GameTransactionEntityInterface entity : entities) {
            try {
                creatFlag = entity.createGameTransactionLock(seconds);
            } catch (Exception e) {
                throw new GameTransactionException(e.getMessage());
            }
            if (!creatFlag) {
                break;
            }
        }
        return creatFlag;
    }


    @Override
    public void releaseGameTransactionLock() {
        for(GameTransactionEntityInterface entity:entities){
            entity.releaseGameTransactionLock();
        }
    }

    /**
     * 获取事务信息
     * @return
     */
    public String getTransactionInfo(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("transaction ");
        buffer.append(getCause());
        buffer.append(":");
        for(int i = 0; i < entities.size(); i++){
            GameTransactionEntityInterface entity = entities.get(i);
            buffer.append(entity.getInfo());
            if(i < entities.size()-1){
                buffer.append(",");
            }
        }
        return buffer.toString();
    }
}


