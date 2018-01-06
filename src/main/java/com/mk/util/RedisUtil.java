package com.mk.util;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
public class RedisUtil {
	/**
	 * 设置元素
	 * 方法名：set<br/>
	 * 创建人：xuke
	 * 时间：2017年12月18日-下午6:10:42 <br/>
	 * @param key
	 * @param value
	 * @return String<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	public static String set(String key ,String value) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("set key {},valye:{},error:{}",key,value,e);
		} finally {
			if(jedis!=null)jedis.close();
		}
		return result;
	}
	
	/**
	 * 
	 * 设置元素并且过期
	 * 方法名：setex<br/>
	 * 创建人：xuke <br/>
	 * 时间：2017年12月18日-下午6:07:22 <br/>
	 * @param key
	 * @param value
	 * @param seconds 单位秒
	 * @return String<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	public static String setex(String key ,String value,int seconds) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.setex(key,seconds,value);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("setex key {},value:{},error:{}",key,e);
		} finally {
			if(jedis!=null)jedis.close();
		}
		return result;
	}
	
	
	/**
	 * 过期是 返回值是 1 不过期是 返回值是 0
	 * 方法名：expire<br/>
	 * 创建人：xuke <br/>
	 * 时间：2017年12月18日-下午6:09:49 <br/>
	 * @param key
	 * @param seconds
	 * @return Long<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	public static Long expire(String key,int seconds) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.expire(key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("expire key {},error:{}",key,e);
		} finally {
			if(jedis!=null)jedis.close();
		}
		return result;
	}
	
	
	/**
	 * 获取元素
	 * 方法名：get<br/>
	 * 创建人：xuke <br/>
	 * 时间：2017年12月18日-下午6:10:19 <br/>
	 * @param key
	 * @return String<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	public static String get(String key) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("get key {},error:{}",key,e);
		} finally {
			if(jedis!=null)jedis.close();
		}
		return result;
	}
	
	
	/**
	 * 
	 * 删除元素
	 * 方法名：del<br/>
	 * 创建人：xuke <br/>
	 * 时间：2017年12月18日-下午6:12:07 <br/>
	 * @param key
	 * @return Long<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	public static Long  del(String key) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("del key {},error:{}",key,e);
		} finally {
			if(jedis!=null)jedis.close();
		}
		return result;
	}
	
	
	
	public static void main(String[] args) {
		RedisUtil.set("username", "xuke");
		String username = RedisUtil.get("username");
		String age = RedisUtil.get("age");
		String password = RedisUtil.get("password");
		System.out.println(username+"=="+password+"=="+age);
		RedisUtil.expire("age", 10);
		RedisUtil.setex("phone","123456787",100);
	}
	
	
}
