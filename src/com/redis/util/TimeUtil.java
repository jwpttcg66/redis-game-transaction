package com.redis.util;

/**
 * Created by jiangwenping on 16/11/26.
 */
public class TimeUtil {

    public static long SECOND = 1000;
    public static long MINUTE = 60 * SECOND;
    public static long FIVE_MINUTE = 5 * MINUTE;
    public static long ONE_HOUR = 60 * MINUTE;
    public static long SIX_HOUR = 6 * ONE_HOUR;
    public static long ONE_DAY = 24 * ONE_HOUR;
    //秒
    public static int ONE_DAY_SECOND = (int) (ONE_DAY / SECOND);
    public static int MINUTE_SECOND = (int) (MINUTE / SECOND);
    public static int FIVEMINUTE_SECOND = (int) (FIVE_MINUTE / SECOND);
    public static int SIX_HOUR_SECOND = (int) (SIX_HOUR / SECOND);
    public static int ONE_HOUR_SECOND = (int)(ONE_HOUR/SECOND);

    public static final String DEFAULT_DATE_FAMAT="yyyyMMdd";
}
