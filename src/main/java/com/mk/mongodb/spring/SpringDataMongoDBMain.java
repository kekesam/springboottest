package com.mk.mongodb.spring;

import com.mk.mongodb.bean.Person;
import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;
import java.util.Date;

public class SpringDataMongoDBMain {

	public static final String DB_NAME = "mkblog";
	public static final String PERSON_COLLECTION = "Person";
	public static final String MONGO_HOST = "localhost";
	public static final int MONGO_PORT = 27017;

	public static void main(String[] args) throws UnknownHostException {
		MongoTemplate  mongoTemplate = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(MONGO_HOST,MONGO_PORT),DB_NAME));
		//新增
		Person p = new Person();
		p.setDeptno(1);
		p.setName("keke");
		p.setBirthday(new Date());
		p.setAddress("长沙");
		p.setMoney(125.8);
		mongoTemplate.save(p,"Person");
		System.out.println("keketip===="+p.getId());
	}




}