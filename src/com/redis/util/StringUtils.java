package com.redis.util;

/**
 * Created by jiangwenping on 16/11/26.
 * 通用服务类
 */
public class StringUtils {
    public static String DEFAULT_SPLIT="#";
    public static boolean  isEmptyString(String string){
        return (string == null || string.trim().length() ==0);
    }
}
