package com.mk.util;


import org.springframework.util.StringUtils;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 属性文件读取工具类
 */
public class PropertiesUtil {

    //读取属性配置文件：java.util.Properties类
    private static Properties properties;
    private static final String filename = "mkshop.properties";
    static {
        try {
            properties = new Properties();
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(filename)));
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * 根据key获取属性值
     * @param key
     * @return
     */
    public static String getProperty(String key){
        String value = properties.getProperty(key);
        if(StringUtils.isEmpty(value)){
            return null;
        }
        return value.trim();
    }

    /**
     * 根据key获取属性值,可以给默认值
     * @param key
     * @return
     */
    public static String getProperty(String key,String defaultValue){
        String value = properties.getProperty(key);
        if(StringUtils.isEmpty(value))value=defaultValue;
        return value.trim();
    }

    /**
     *
     * 添加属性值，值会添加到内存中，文件中是不能追加进去的。
     * @param key
     * @param value
     */
    public static void setProperties(String key,String value){
        properties.setProperty(key,value);
    }

    public static void main(String[] args){
       System.out.println(PropertiesUtil.getProperty("redis.ip"));
       System.out.println(PropertiesUtil.getProperty("redis.port"));
       System.out.println(PropertiesUtil.getProperty("redis.password"));
       System.out.println(PropertiesUtil.getProperty("redis.password2","123456"));
    }


}
