package com.redis.transaction.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.JedisCluster;

/**
 * Created by jiangwenping on 17/3/21.
 */
public class RedisClusterServiceTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"bean/*.xml"});
//        RGTRedisClusterService redisClusterService = (RGTRedisClusterService) classPathXmlApplicationContext.getBean("redisClusterService");
//
//        redisClusterService.setString("test", "200");
//        System.out.println(redisClusterService.getString("test"));
        JedisCluster jedisCluster = (JedisCluster) classPathXmlApplicationContext.getBean("jedisCluster");
        jedisCluster.set("test", "200");
        System.out.println(jedisCluster.get("test"));
    }
}
