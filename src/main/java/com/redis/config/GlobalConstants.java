package com.redis.config;

/**
 * Created by jiangwenping on 16/11/29.
 */
public class GlobalConstants {

    /**
     * redis Key的基本配置
     */
    public static class RedisKeyConfig {
        /**
         * 正常缓存有效时间
         */
        public static final int NORMAL_LIFECYCLE = 86400;
        //mget时，key的最大值
        public static final int MGET_MAX_KEY = 50;
        /**
         * 正常缓存有效时间一个月
         */
        public static final int NORMAL_MONTH_LIFECYCLE = 86400 * 24;
    }


    /**
     * redis Key的基本配置
     */
    public static class RedisConfigFile {
        /**
         * pool
         */
        public static final String REDIS_POOL_CONIFG = "redis-pool-config.xml";
        /**
         * redis
         */
        public static final String REDIS = "redis_data.xml";
    }

    /**
     * redis Key的基本配置
     */
    public static class Strings {
        public static final String commonSplitString = "#";
    }
}
