package com.mk.mongodb.dao;

import com.mk.mongodb.MongoUtil;
import com.mk.mongodb.bean.Person;
import com.mk.util.BeanUtilSpring;
import com.mk.util.JsonUtil;
import com.mk.util.PropertiesUtil;
import com.mongodb.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
@Slf4j
public class PersonDao {

    private static final String tableName = "Person";
    private static final String DBNAME = PropertiesUtil.getProperty("mongo.db","mkblog");

    /**
     * 查看所有的用户信息
     * @author chenmc
     * @date 2017/12/27 19:50
     * @param
     * @return
     */
    public static List<Person> findPersons(){
        log.debug("查询所有findPersons------start");
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        DBCursor dbCursor =  dbCollection.find();
        List<Person> personList = new ArrayList<>();
        while (dbCursor.hasNext()){
            DBObject dbObject = dbCursor.next();
            Person person = new Person();
            person.setId(String.valueOf(dbObject.get("_id")));
            person.setAddress(String.valueOf(dbObject.get("address")));
            person.setName(String.valueOf(dbObject.get("name")));
            personList.add(person);
        }
        //关闭
        MongoUtil.close(mongoClient);
        return personList;
    }


    /**
     * 根据Id查询信息
     * @param id
     * @return
     */
    public static Person getPerson(String id){
        log.debug("查询单个getPerson------start");
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        //设置查询条件
        BasicDBObject basicDBObject = new BasicDBObject("_id",new ObjectId(id));
        DBCursor dbCursor =  dbCollection.find(basicDBObject);
        Person person = null;
        if(dbCursor.hasNext()){
            DBObject dbObject = dbCursor.next();
            person = BeanUtilSpring.mapToObject(dbObject.toMap(),Person.class);
        }
        //关闭
        MongoUtil.close(mongoClient);
        return person;
    }


    /**
     * 根据Id查询信息
     * @param id
     * @return
     */
    public static Person getPerson2(String id){
        log.debug("查询单个getPerson------start");
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        //设置查询条件
        BasicDBObject basicDBObject = new BasicDBObject("_id",new ObjectId(id));
        DBObject dbObject =  dbCollection.findOne(basicDBObject);
        Person person = BeanUtilSpring.mapToObject(dbObject.toMap(),Person.class);
        //关闭
        MongoUtil.close(mongoClient);
        return person;
    }

    public static Person getPerson3(String id){
        log.debug("查询单个getPerson------start");
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        //设置查询条件
        DBObject dbObject =  dbCollection.findOne(new ObjectId(id));
        Person person = BeanUtilSpring.mapToObject(dbObject.toMap(),Person.class);
        //关闭
        MongoUtil.close(mongoClient);
        return person;
    }


    /**
     * 求count
     * @param id
     * @return
     */
    public static long countPerson(){
        log.debug("求总数countPerson------start");
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        long count = dbCollection.count();
        //关闭
        MongoUtil.close(mongoClient);
        return count;
    }

    //保存
    public static Person save(Person person){
        log.debug("保存save------start");
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        Map<String,Object> maps = BeanUtilSpring.objectToMap(person);
        DBObject dbObject = new BasicDBObject(maps);
        WriteResult count = dbCollection.save(dbObject);
        ObjectId objectId = (ObjectId)dbObject.get( "_id" );
        person.setId(objectId.toString());
        //关闭
        MongoUtil.close(mongoClient);
        return person;
    }

    //保存
    public static void updateOne(Person person){
        log.debug("保存updateOne------start");
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        //查询条件
        BasicDBObject whereObject = new BasicDBObject("_id",new ObjectId(person.getId()));
        //修改内容
        Map<String,Object> maps = BeanUtilSpring.objectToMapNotNull(person);
        //这里要注意用$set，否则如果没有数据的列会被清空，因为update更新的时候直接写dbObject会造成没有数据的列设置为null.
        DBObject dbObject = new BasicDBObject("$set",maps);
        WriteResult count = dbCollection.update(whereObject,dbObject);
        //关闭
        MongoUtil.close(mongoClient);
    }

    //默认更新是and更新
    public static int updateMany(Person person,String name){
        log.debug("保存updateMany------start");
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        //查询条件
        Map<String,Object> params = new HashMap<>();
        params.put("name",name);
        BasicDBObject whereObject = new BasicDBObject(params);
        //修改内容
        Map<String,Object> maps = BeanUtilSpring.objectToMapNotNull(person);
        DBObject dbObject = new BasicDBObject("$set",maps);
        //批量修改
        WriteResult writeResult = dbCollection.updateMulti(whereObject,dbObject);
        //等价于 WriteResult count = dbCollection.update(whereObject,dbObject,false,true);
        //关闭
        MongoUtil.close(mongoClient);
        return writeResult.getN();
    }


    //or---update更新
    public static int updateManyOr(Person person,String name,String address){
        log.debug("保存updateManyOr------start");
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        //查询条件
        BasicDBObject[] dbObjects = new BasicDBObject[2];
        dbObjects[0]= new BasicDBObject("name",name);
        dbObjects[1]= new BasicDBObject("address",address);
        BasicDBObject whereObject = new BasicDBObject("$or",dbObjects);
        //修改内容
        Map<String,Object> maps = BeanUtilSpring.objectToMapNotNull(person);
        DBObject dbObject = new BasicDBObject("$set",maps);
        //批量修改
        WriteResult writeResult = dbCollection.updateMulti(whereObject,dbObject);
        //等价于 WriteResult count = dbCollection.update(whereObject,dbObject,false,true);
        //关闭
        MongoUtil.close(mongoClient);
        return writeResult.getN();
    }

    //删除
    public static int deleteById(String id){
        log.debug("deleteById------start");
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        WriteResult writeResult = dbCollection.remove(new BasicDBObject("_id",new ObjectId(id)));
        MongoUtil.close(mongoClient);
        return writeResult.getN();
    }


    private static DBObject createDBObject(Person person) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        docBuilder.append("_id", person.getId());
        docBuilder.append("name", person.getName());
        docBuilder.append("address", person.getAddress());
        docBuilder.append("money", person.getMoney());
        docBuilder.append("birthday", person.getBirthday());
        return docBuilder.get();
    }


    public static List<Person> query(String keyword){
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        //完全匹配
        //Pattern pattern = Pattern.compile("^name$", Pattern.CASE_INSENSITIVE);
        //右匹配
        //Pattern pattern = Pattern.compile("^.*name$", Pattern.CASE_INSENSITIVE);
        //左匹配
        //Pattern pattern = Pattern.compile("^name.*$", Pattern.CASE_INSENSITIVE);
        //模糊匹配
        Pattern pattern = Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE);
        BasicDBObject query = new BasicDBObject();
        query.put("name",pattern);
        BasicDBObject sort = new BasicDBObject();
        // 1,表示正序； －1,表示倒序
        sort.put("money",1);
        DBCursor cur = dbCollection.find(query).sort(sort);
        int count = cur.count();
        List<Person> personList = new ArrayList<>();
        while (cur.hasNext()) {
            DBObject obj = cur.next();
            Person person = BeanUtilSpring.mapToObject(obj.toMap(),Person.class);
            personList.add(person);
            System.out.println("keketip===="+ JsonUtil.obj2String(obj.toMap()));
        }
        System.out.println("共有： " + count + "个");
        return personList;
    }


    /**
     * 分页查询
     *
     * @param db
     * @param name
     * @param start
     * @param pageSize
     */
    public static List<Person> page(String name,int start,int pageSize){
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);
        BasicDBObject sort = new BasicDBObject();
        sort.put("money",1);
        DBCursor cur = dbCollection.find().sort(sort).skip(start).limit(pageSize);;
        int count = cur.count();
        List<Person> personList = new ArrayList<>();
        while (cur.hasNext()) {
            DBObject obj = cur.next();
            Person person = BeanUtilSpring.mapToObject(obj.toMap(),Person.class);
            personList.add(person);
            System.out.println("keketip===="+ JsonUtil.obj2String(obj.toMap()));
        }
        System.out.println("共有： " + count + "个");
        return personList;
    }



    //大于，小于 大于等于  小于等于  不等于
    public static List<Person> queryAM(double start,double end){
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);

        //查询条件
        BasicDBObject queryCondition = new BasicDBObject();
        BasicDBList whereQuerys = new BasicDBList();
        //whereQuerys.add(new BasicDBObject("money", new BasicDBObject("$gt", start)));//大于
        //whereQuerys.add(new BasicDBObject("money", new BasicDBObject("$lt", end)));//等于
        //whereQuerys.add(new BasicDBObject("money", new BasicDBObject("$gte", start)));//大于等于
        //whereQuerys.add(new BasicDBObject("money", new BasicDBObject("$lte", end)));//小于等于
        whereQuerys.add(new BasicDBObject("money", new BasicDBObject("$ne", end)));//不等于
        queryCondition.put("$and", whereQuerys);

        //排序规则 // 1,表示正序； －1,表示倒序
        BasicDBObject sort = new BasicDBObject();
        sort.put("money",-1);

        //查询数据
        DBCursor cur = dbCollection.find(queryCondition).sort(sort);
        //求总数
        int count = cur.count();
        List<Person> personList = new ArrayList<>();
        while (cur.hasNext()) {
            DBObject obj = cur.next();
            Person person = BeanUtilSpring.mapToObject(obj.toMap(),Person.class);
            personList.add(person);
        }
        System.out.println("共有： " + count + "个");
        return personList;
    }



    //QueryOperators版本
    public static List<Person> queryQueryOperators(double start,double end){
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);

        //查询条件规则 or还是and
        BasicDBObject queryCondition = new BasicDBObject();
        //查看字段
        BasicDBList whereQuerys = new BasicDBList();
        whereQuerys.add(new BasicDBObject("money", new BasicDBObject(QueryOperators.GTE, start)));
        whereQuerys.add(new BasicDBObject("money", new BasicDBObject(QueryOperators.LTE, end)));
        //whereQuerys.add(new BasicDBObject("name", "徐柯11111"));
        //whereQuerys.add(new BasicDBObject("name", new BasicDBObject(QueryOperators.IN, new String[]{"徐柯11111","wangwang4"})));
        Pattern pattern = Pattern.compile("^.*徐.*$", Pattern.CASE_INSENSITIVE);
        whereQuerys.add(new BasicDBObject("name", new BasicDBObject("$regex", pattern)));//等价于whereQuerys.add(new BasicDBObject("name", pattern));
        //设置它们的查询关系，or
        queryCondition.append(QueryOperators.AND,whereQuerys);

        //排序规则 // 1,表示正序； －1,表示倒序
        BasicDBObject sort = new BasicDBObject();
        sort.put("money",-1);

        //查询数据
        DBCursor cur = dbCollection.find(queryCondition).sort(sort);
        //求总数
        int count = cur.count();
        List<Person> personList = new ArrayList<>();
        while (cur.hasNext()) {
            DBObject obj = cur.next();
            Person person = BeanUtilSpring.mapToObject(obj.toMap(),Person.class);
            personList.add(person);
        }
        System.out.println("共有： " + count + "个");
        return personList;
    }



    //聚合
    //QueryOperators版本
    public static void queryQueryGroup(){
        MongoClient mongoClient = MongoUtil.getClient();
        DB db = mongoClient.getDB(DBNAME);
        DBCollection dbCollection = db.getCollection(tableName);

        BasicDBObject cond= new BasicDBObject();
        //BasicDBObject dt= new BasicDBObject();
        //dt.put("$gte",fromISODate(start));//大于等于
        //dt.put("$lt",fromISODate(end));//小于
        //cond.put("dt", dt);
        //cond.put("age",age);
        BasicDBObject key = new BasicDBObject("deptno", true);
        BasicDBObject initial = new BasicDBObject("person",new ArrayList());
        String reduce = "function(doc,out){out.person.push(doc.money);}";
        String finalize = "function(out){" +
                "out.count=out.person.length;" +
                "out.sum=csum(out.person);" +
                "};" +
                "function csum(list){" +
                "var result = 0;" +
                "for(var i=0;i<list.length;i++){" +
                "result+=list[i]};" +
                "return result" +
                "}";
        DBObject list = dbCollection.group(key, cond, initial, reduce, finalize);
        System.out.println("keketip===="+list.toMap());
        //关闭
        MongoUtil.close(mongoClient);
    }


    public static Date fromISODate(String time){
        Date date=formatDate(time);
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, 8);
        return ca.getTime();
    }

    public static Date formatDate(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args){
        //List<Person> personList = queryAM(10,28.5);
        //List<Person> personList = queryQueryOperators(10,28.5);
        //System.out.println("keketip===="+JsonUtil.obj2String(personList));
        //queryQueryGroup();
        //queryQueryGroup2();
    }
}
