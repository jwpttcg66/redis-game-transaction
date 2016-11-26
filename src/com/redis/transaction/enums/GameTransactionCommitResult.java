package com.redis.transaction.enums;

/**
 * Created by jiangwenping on 16/11/26.
 */
public class  GameTransactionCommitResult {
    /** 成功*/
    public static  final String successString = "success";
    /** 失败*/
    public static  final  String commonnErroString="common_error";
    /** 失败*/
    public static  final  String lockErrorString = "lock_error";

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
