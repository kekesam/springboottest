package com.mk.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtilSpring<T> {

    //map----to---bean---查询使用 id
    public static <T> T mapToObject(Map map, Class<T> beanClass)  {
        if (map == null) return null;
        try {
            Object obj = beanClass.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            //获取类中所有的属性
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                //获取set方法
                Method setter = property.getWriteMethod();
                //属性名字
                String fname = property.getName();
                Object value = null;
                if(fname.toLowerCase().equals("id")){
                    value = map.get("_id").toString();
                }else{
                    value = map.get(fname);
                }
                if (setter != null && value!=null) {
                    setter.invoke(obj, value);
                }
            }
            return (T)obj;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    //bean----to---map
    public static Map<String, Object> objectToMap(Object obj) {
        if(obj == null)
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.equalsIgnoreCase("id") || key.compareToIgnoreCase("class") == 0) {
                    continue;
                }

                Method getter = property.getReadMethod();
                Object value = getter!=null ? getter.invoke(obj) : null;
                map.put(key, value);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    //bean----to---map
    public static Map<String, Object> objectToMapNotNull(Object obj) {
        if(obj == null)
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.equalsIgnoreCase("id") || key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter!=null ? getter.invoke(obj) : null;
                if(value!=null) map.put(key, value);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
