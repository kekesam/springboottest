package com.mk.mongodb;

import com.mk.util.PropertiesUtil;
import com.mongodb.*;
import lombok.extern.slf4j.Slf4j;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MongoUtil {

    private static final String HOST = PropertiesUtil.getProperty("mongo.host","127.0.0.1");
    private static final int PORT = Integer.parseInt(PropertiesUtil.getProperty("mongo.port","27017"));
    private static final String USERNAME = PropertiesUtil.getProperty("mongo.username","root");
    private static final String PASSWORD = PropertiesUtil.getProperty("mongo.password","xiaoer1986");
    private static final String DBNAME = PropertiesUtil.getProperty("mongo.db","mkblog");

    /**
     * 连接Mongodb
     * @return
     */
    public static MongoClient getClient(){
        try {
            log.debug("Mongodb Connection success ip {},port {}",HOST,PORT);
            return new MongoClient(HOST,PORT);
        } catch (UnknownHostException e) {
            log.debug("Mongodb Connection fail {}",e);
            return null;
        }
    }

    /**
     * 连接Mongodb
     * @return
     */
    public static MongoClient getClientByPwd(){
        try {
            List<ServerAddress> lists=new ArrayList<ServerAddress>();
            lists.add(new ServerAddress(HOST, PORT));
            List<MongoCredential> listm=new ArrayList<MongoCredential>();
            listm.add(MongoCredential.createCredential(USERNAME, DBNAME, PASSWORD.toCharArray()));
            MongoClientOptions.Builder builder = MongoClientOptions.builder();
            // 与目标数据库能够建立的最大connection数量为50
            builder.connectionsPerHost(50);
            // 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
            builder.threadsAllowedToBlockForConnectionMultiplier(50);
            // 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
            // 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
            // 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
            builder.maxWaitTime(1000*60*2);
            // 与数据库建立连接的timeout设置为1分钟
            builder.connectTimeout(1000*60*1);
            //===================================================//
            MongoClientOptions mco = builder.build();


            return new MongoClient(lists,listm);
        } catch (UnknownHostException e) {
            log.debug("Mongodb Connection fail {}",e);
            return null;
        }
    }

    /**
     * 获取table的操作对象
     * @param tableName
     * @return
     */
    public static DBCollection getCollection(String tableName){
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        return dbCollection;
    }

    /**
     * 关闭
     * @param client
     */
    public static void close(MongoClient client){
        client.close();
    }
}
