package com.mk.mongodb.dao;

import com.mk.util.PropertiesUtil;

public  interface IBaseDao {
    public static final String DB = PropertiesUtil.getProperty("mongo.db","mkshop");
}
