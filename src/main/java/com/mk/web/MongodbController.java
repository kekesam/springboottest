package com.mk.web;

import com.mk.bean.MongoUser;
import com.mk.service.MongoUserService;
import com.mk.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class MongodbController {


    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoUserService mongoUserDao;

    @GetMapping("/save")
    public String save(){
        MongoUser mongoUser = new MongoUser();
        mongoUser.setName("keke");
        mongoUser.setPassword("123456");
        mongoUser.setAge(31);
        mongoUserDao.insert(mongoUser,"MongoUser");
        return "success";
    }

    @GetMapping("/update")
    public String update(){
        MongoUser mongoUser = new MongoUser();
        mongoUser.setName("keke");
        mongoUser.setAge(100);
        mongoUser.setId("5a44ac91d602f7f5b861e2e1");
        mongoUserDao.update(mongoUser,"MongoUser");
        return "success";
    }

    @GetMapping("/delete")
    public String delete(){
        mongoUserDao.remove("5a44ac91d602f7f5b861e2e1","MongoUser");
        return "success";
    }

    @GetMapping("/search")
    public String search(){
        Query query = new Query();
        MongoUser mongoUser = new MongoUser();
        mongoUser.setName("keke");
        Example example = Example.of(mongoUser, ExampleMatcher.matchingAny());
        query.addCriteria(new Criteria().alike(example));
        List<MongoUser> result = mongoTemplate.find(query, MongoUser.class, "MongoUser");
        System.out.println("keketip===="+ JsonUtil.obj2String(result));
        return "success";
    }

    @GetMapping("/querysort")
    public List<MongoUser> search2(){
        Query query = new Query();
        String value = "ke";
        //i不区分大小写
        query.addCriteria(Criteria.where("name").regex("^.*" +value.toString()+ ".*$","i"));
        query.skip(0).limit(4);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "age")));
        List<MongoUser> result = mongoTemplate.find(query, MongoUser.class, "MongoUser");
        return result;
    }

}
