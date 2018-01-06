package com.mk.mongodb.dao.impl;

import com.mk.mongodb.MongodbUtil;
import com.mk.mongodb.bean.UserLog;
import com.mk.mongodb.dao.IUserLogDao;
import com.mk.util.BeanUtilSpring;
import com.mongodb.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import java.util.List;

@Slf4j
public class UserDaoImpl  implements IUserLogDao {


    /*@Override
    //命令：db.userlog.insert({})
    public UserLog save(UserLog userLog) {
        log.info("保存save启动开始");
        MongoClient client = MongodbUtil.getClient();
        //选择了数据库 user mkshop
        DB db = client.getDB(DB);
        //找到数据表 db.getCollection('user').find({})
        DBCollection collection = db.getCollection("userlog");
        //完成保存操作 db.user.save({id:1,name:"keke"})
        DBObject dbObject = new BasicDBObject();
        dbObject.put("message",userLog.getMessage());
        dbObject.put("createTime",userLog.getCreateTime());
        dbObject.put("updateTime",userLog.getUpdateTime());
        dbObject.put("userId",userLog.getUserId());
        dbObject.put("username",userLog.getUsername());
        dbObject.put("model",userLog.getModel());
        dbObject.put("info",userLog.getInfo());
        dbObject.put("ip",userLog.getIp());
        dbObject.put("os",userLog.getOs());
        dbObject.put("bos",userLog.getBos());
        WriteResult writeResult = collection.save(dbObject);
       *//* int count = Integer.parseInt(
                String.valueOf(writeResult.getLastError().get("ok"))
        );*//*
        userLog.setId(String.valueOf(dbObject.get("_id")));
        return userLog;
    }*/


    @Override
    //命令：db.userlog.insert({})
    public UserLog save(UserLog userLog) {
        log.info("保存save启动开始");
        MongoClient client = MongodbUtil.getClient();
        //选择了数据库 user mkshop
        DB db = client.getDB(DB);
        //找到数据表 db.getCollection('user').find({})
        DBCollection collection = db.getCollection("userlog");
        //完成保存操作 db.user.save({id:1,name:"keke"})
        //如何将一个bean转换成一个hashmap
        DBObject dbObject = new BasicDBObject(BeanUtilSpring.objectToMap(userLog));
        WriteResult writeResult = collection.save(dbObject);
        userLog.setId(String.valueOf(dbObject.get("_id")));
        return userLog;
    }

    @Override
    public UserLog getById(String id) {
        log.info("保存getById启动开始");
        MongoClient client = MongodbUtil.getClient();
        //选择了数据库 user mkshop
        DB db = client.getDB(DB);
        //找到数据表 db.getCollection('user').find({})
        DBCollection collection = db.getCollection("userlog");
        //db.userlog.find({"_id":id});
        DBObject params = new BasicDBObject("_id",new ObjectId(id));
        DBCursor dbCursor = collection.find(params);
        UserLog userLog = null;
        if(dbCursor.hasNext()){
            DBObject dbObject = dbCursor.next();
            userLog = BeanUtilSpring.mapToObject(dbObject.toMap(),UserLog.class);
        }
        return userLog;
    }

    @Override
    public List<UserLog> findUserLogs() {
        return null;
    }

    @Override
    public int countUserLogs() {
        return 0;
    }

    @Override
    public void update(UserLog userLog) {

    }

    @Override
    public void delete(String id) {

    }
}
