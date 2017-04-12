package com.redis.transaction.spring.cluster;

import com.redis.transaction.GameTransactionCauseImpl;
import com.redis.transaction.GameTransactionEntityCauseImpl;
import com.redis.transaction.GameTransactionEntityFactoryImpl;
import com.redis.transaction.RedisKey;
import com.redis.transaction.entity.TestMutexEntity;
import com.redis.transaction.enums.GameTransactionCommitResult;
import com.redis.transaction.service.RGTRedisClusterService;
import com.redis.transaction.service.TransactionService;
import com.redis.util.BeanUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jiangwenping on 17/3/21.
 */
public class ClusterTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"bean/*.xml"});

        RGTRedisClusterService RGTRedisClusterService = (RGTRedisClusterService) BeanUtil.getBean("RGTRedisClusterService");
        TransactionService transactionService = (TransactionService) BeanUtil.getBean("transactionService");

        String union = "union";
        TestMutexEntity testMutexEntity = GameTransactionEntityFactoryImpl.createTestMutexEntity(GameTransactionEntityCauseImpl.test, RGTRedisClusterService, RedisKey.player, union);
        GameTransactionCommitResult commitResult = transactionService.commitTransaction(GameTransactionCauseImpl.test, testMutexEntity);
        System.out.println(commitResult.getReuslt());
    }
}
