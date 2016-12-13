package com.redis.transaction.enums;

/**
 * Created by jiangwenping on 16/11/26.
 * 事务执行结果
 */
public class  GameTransactionCommitResult {

    /** 成功*/
    public static  final GameTransactionCommitResult SUCCESS = new GameTransactionCommitResult("success");
    /** 失败*/
    public static  final  GameTransactionCommitResult COMMON_ERROR=new GameTransactionCommitResult("common_error");
    /** 失败*/
    public static  final  GameTransactionCommitResult LOCK_ERROR = new GameTransactionCommitResult("lock_error");

    /**
     * 事务执行结果
     */
    private String reuslt;

    public GameTransactionCommitResult(String reuslt) {
        this.reuslt = reuslt;
    }

    public String getReuslt() {
        return reuslt;
    }

    public void setReuslt(String reuslt) {
        this.reuslt = reuslt;
    }
}
