package com.redis.transaction.enums;

/**
 * Created by jiangwenping on 16/11/26.
 * 事务锁类型
 */
public enum GameTransactionLockType {
    /**独自占有锁 */
    WRITE,
    /**读锁*/
    READ,
    /** 独自写占有，到时间才会释放锁*/
    WRITE_TIME,
    /** 独自写占有，无论获取锁成功，强制时间锁， 到时间时间释放*/
    FORCE_WRITE_TIME,
    ;
}