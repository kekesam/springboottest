package com.mk.mongodb;

import com.mk.util.PropertiesUtil;
import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MongodbUtil {

    private static final String HOST = PropertiesUtil.getProperty("mongo.host","127.0.0.1");
    private static final int PORT = Integer.parseInt(PropertiesUtil.getProperty("mongo.port","27017"));
    private static final String DB = PropertiesUtil.getProperty("mongo.db","mkshop");
    private static final String USERNAME = PropertiesUtil.getProperty("mongo.username","root");
    private static final String PASSWORD = PropertiesUtil.getProperty("mongo.password","xiaoer1986");

    public static MongoClient getClient(){
        try {
            log.info("mongodb启动开始了,ip是{},监听的端口是：{}",HOST,PORT);
            return new MongoClient(HOST,PORT);
        }catch(Exception ex){
            log.info("mongodb启动失败了,失败的原因是：{}", ex);
            return null;
        }
    }


    public static void main(String[] args){
       System.out.println(getClient());
    }

}
