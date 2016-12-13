package com.redis.transaction.lock;

import com.redis.transaction.exception.GameTransactionException;

/**
 * Created by jiangwenping on 16/11/26
 * 事务接口
 */
public interface GameTransactionLockInterface {
    /**
     * 销毁
     */
    public void destroy();

    /**
     * 创建
     * @return
     */
    public boolean create(long seconds)  throws GameTransactionException;

    /**
     * 获取信息
     * @return
     */
    public String getInfo();

    /**
     * 设置内容
     */
    public void setContent(String lockContent);

}

