# Redis-分布式-事务
> 在大型游戏中经常使用分布式，分布式中因为游戏逻辑会经常游戏事务，借助redis特性我们可以实现分布式锁和分布式事务。很多redis集群不支持redis的事务特性。
这个框架用来解决分布式服务器下redis集群事务失效的情况下,基于分布式锁完成分布式事务。支持独占锁，共享锁，读写锁，并且支持事务提交失败情况下的回滚操作，让开发者可以有更多时间侧重游戏逻辑.

## 互斥锁使用例子

> 可参考test下的entity.

1. 生成事务原因,GameTransactionCauseImpl里面构造.
2. 生成锁实体,GameTransactionEntityFactoryImpl里面构造TimeMutexEntity.
3. 提交锁实体跟事务,transactionService.commitTransaction里面提交.
4. 后去事务提交结果,根据返回值做出判断.


## 读取锁使用例子

> 可参考test下的read 可以设置默认是否成功读取到

1. 生成读取事务原因GameTransactionCause里面已经构造好了.
2. 生成读取锁实体GameTransactionEntityFactory里面已经构造好了.
3. 提交读取锁实体跟事务transactionService.commitTransaction里面提交.
4. 后去事务提交结果 根据返回值做出判断.

## 标记锁使用例子

可参考test下的lockattchment可以设置默认是否成功读取到

#### 写锁例子如下

1. 生成事务原因,GameTransactionCauseImpl里面构造.
2. 生成锁实体,GameTransactionEntityFactoryImpl里面构造.
3. 生成锁标记内容 GameTransactionEntity对象获取GameTransactionLockInterface锁使用setContent
4. 提交锁实体跟事务,transactionService.commitTransaction里面提交.
5. 后去事务提交结果,根据返回值做出判断.

#### 读锁例子如下

1. 生成读取事务原因GameTransactionCause里面构造.
2. 生成读取锁实体GameTransactionEntityFactory里面构.
3. 生成锁标记内容 GameTransactionEntity对象获取GameTransactionLockInterface锁使用setContent
4. 提交读取锁实体跟事务transactionService.commitTransaction里面提交.
5. 后去事务提交结果 根据返回值做出判断.

#### 强制写锁使用例子如下
> 可参考test下的force
> 当锁存在的情况，延迟锁占用时间。
> 例子如下
1. 生成事务原因,GameTransactionCauseImpl里面构造.
2. 生成锁实体,GameTransactionEntityFactoryImpl里面构造ForceEntity.
3. 提交锁实体跟事务,transactionService.commitTransaction里面提交.
4. 后去事务提交结果,根据返回值做出判断.

## 带时间写锁使用例子如下
> 参考test下的testlock.
> 当锁存在的情况，延迟锁占用时间。
> 例子如下

1. 生成事务原因,GameTransactionCauseImpl里面构造.
2. 生成锁实体,GameTransactionEntityFactoryImpl里面构造TestTimeMutexEntity.
3. 提交锁实体跟事务,transactionService.commitTransaction里面提交.
4. 后去事务提交结果,根据返回值做出判断.

## 带时间等待写锁使用例子如下

> 参考test下的wait.当锁失败的情况下，会尝试每秒检查锁，直到设置的等待时间结束.例子如下

1. 生成事务原因,GameTransactionCauseImpl里面构造.
2. 生成锁实体,GameTransactionEntityFactoryImpl里面构造WaitMutexEntity.
3. 提交锁实体跟事务,transactionService.commitTransaction里面提交.
4. 后去事务提交结果,根据返回值做出判断.

## 带事务回滚写锁使用例子如下

> 参考test下的rollback.当事务提交失败的情况下，根据提交进度进行rollback.例子如下

1. 生成事务原因,GameTransactionCauseImpl里面构造.
2. 生成锁实体,GameTransactionEntityFactoryImpl里面构造RollbackMutexEntity.
3. 提交锁实体跟事务,transactionService.commitTransaction里面提交.
4. 后去事务提交结果,根据返回值做出判断.

## 已经集成spring和redis集群
> 参考test下的spring

代码最后通过maven部署,

此框架已经上线手游项目两年，经过百万级DAU验证,稳定运行.

- 作者qq 330258845
- QQ群310158485
