package com.mk.service.impl;

import com.mk.bean.MongoUser;
import com.mk.service.MongoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MongoUserServiceImpl implements MongoUserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(MongoUser object, String collectionName) {
        mongoTemplate.insert(object, collectionName);
    }

    @Override
    public MongoUser findOne(Map<String, Object> params, String collectionName) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(params.get("id"))), MongoUser.class, collectionName);
    }

    @Override
    public List<MongoUser> findAll(Map<String, Object> params, String collectionName) {
        List<MongoUser> result = mongoTemplate.find(new Query(Criteria.where("age").lt(params.get("maxAge"))), MongoUser.class, collectionName);
        return result;
    }

    @Override
    public void update(MongoUser mongoUser, String collectionName) {
        mongoTemplate.upsert(new Query(Criteria.where("id").is(mongoUser.getId())),
                new Update().set("name", mongoUser.getName())
                .set("age", mongoUser.getAge()),
                MongoUser.class,
                collectionName);
    }

    @Override
    public void createCollection(String name) {
        mongoTemplate.createCollection(name);
    }

    @Override
    public void remove(String id, String collectionName) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), MongoUser.class, collectionName);
    }

    public List<MongoUser> findLike(Map<String, Object> params, String collectionName) {
        Query query = new Query();
        MongoUser mongoUser = new MongoUser();
        mongoUser.setName("keke");
        Example example = Example.of(mongoUser, ExampleMatcher.matchingAny());
        query.addCriteria(new Criteria().alike(example));
        List<MongoUser> result = mongoTemplate.find(query, MongoUser.class, "MongoUser");
        return result;
    }

    public List<MongoUser> findLike2(Map<String, Object> params, String collectionName) {
        Query query = new Query();
        //MongoUser mongoUser = new MongoUser();
        //mongoUser.setName("keke");
        Example example = Example.of(params, ExampleMatcher.matchingAny());
        query.addCriteria(new Criteria().alike(example));
        query.skip(0).limit(2);
        List<MongoUser> result = mongoTemplate.find(query, MongoUser.class, "MongoUser");
        return result;
    }
}