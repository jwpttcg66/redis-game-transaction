package com.redis.transaction.enums;

/**
 * Created by jiangwenping on 16/11/26.
 */
public enum  GameTransactionCommitResult {
    /** 成功*/
    SUCCESS,
    /** 失败*/
    COMMON_ERROR,
    /** 失败*/
    LOCK_ERROR,
    ;
}
