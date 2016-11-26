package com.redis.log;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/**
 * Created by jiangwenping on 16/11/26.
 */
public class Loggers {
    /** redis相关的日志 */
    public static final Logger redisLogger = LoggerFactory.getLogger("redis");
    /** redis相关的日志 */
    public static final Logger transactionLogger = LoggerFactory.getLogger("transaction");
    /** redis相关的日志 */
    public static final Logger lockLogger = LoggerFactory.getLogger("lock");
}
