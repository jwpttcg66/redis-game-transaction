package com.redis.util;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.net.URL;

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
