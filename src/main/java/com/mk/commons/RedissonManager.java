package com.mk.commons;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import  com.mk.util.PropertiesUtil;

/**
 * Created by geely
 */
@Slf4j
@Component
public class RedissonManager {
    //在redis环境没有搭建起来之前，这里先注释上，否则项目启动不起来。

    private static Config config = new Config();
    private static RedissonClient client = null;
    private final static String redisIp = PropertiesUtil.getProperty("redis.ip");
    private final static String password = PropertiesUtil.getProperty("redis.password");
    private final static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));


    public void lockForList(RedissonClient client){

        RLock lock = client.getLock("anyRWLock");
        lock.lock(1000, TimeUnit.MILLISECONDS);
        RBucket<String> bucket = client.getBucket("bucket");
        String v = bucket.get();
        bucket.set(String.valueOf(Double.parseDouble(v)+10));
        lock.unlock();
    }

    //注入到Spring容器的话，使用@PostConstruct或者静态块初始化，效果是一样的{}
    //@PostConstruct//容器在启动打的时候会执行此方法
    private static void init() {
        try {
            config.useSingleServer().setAddress(redisIp+":"+redisPort);
            config.useSingleServer().setPassword(password);
            client = Redisson.create(config);
            //log.info("初始化Redisson结束");
        } catch (Exception e) {
            //log.error("redisson init error", e);
        }
    }

    public static RedissonClient getClient() {
        return client;
    }

    static{
        init();
    }

    public static void main(String[] args) {
        /*RBucket<String> rBucket =  getClient().getBucket("keke:redisson");
        rBucket.set("zhangsan");
        String value =  rBucket.get();
        System.out.println(value);*/


        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000)
                .setPassword("xiaoer1986")
                .addNodeAddress("192.168.0.103:6379","192.168.0.103:6380");
        RedissonClient client = Redisson.create(config);
        RBucket<String> bucket = client.getBucket("bucket");
        bucket.delete();
        System.out.println("已重置");
        bucket.set("0");
        new Thread() {
            @Override
            public void run() {

                RedissonManager test1 = new RedissonManager();
                for(int i=0;i<1000;i++){
                    test1.lockForList(client);

                }  }
        }.start();

        new Thread() {
            @Override
            public void run() {

                RedissonManager test1 = new RedissonManager();
                for(int i=0;i<1000;i++){
                    test1.lockForList(client);

                }  }
        }.start();

        new Thread() {
            @Override
            public void run() {

                RedissonManager test1 = new RedissonManager();
                for(int i=0;i<1000;i++){
                    test1.lockForList(client);

                }  }
        }.start();
        // client.shutdown();
        System.out.println(client);
    }
}
