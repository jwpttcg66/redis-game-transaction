package com.redis.transaction.enums;

/**
 * Created by jiangwenping on 16/11/26.
 * 事务锁状态
 */
public enum  GameTransactionLockStateEnum {
    /**
     * 初始化
     */
    init,

    /** 创建*/
    create,

    /**成功*/
    success,
}
