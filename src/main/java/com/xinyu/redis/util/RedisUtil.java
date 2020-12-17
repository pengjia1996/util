package com.xinyu.redis.util;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.xinyu.file.util.PropertiesUtil;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**   
 * @Title:RedisUtil
 * @author:彭嘉
 * @date:2020年11月4日 下午4:20:54  
 * @Description:redis缓存操作公共类   
 */
public class RedisUtil {

    private static Logger logger = Logger.getLogger(RedisUtil.class);
    private static ShardedJedisPool pool;

    private static String redisIp = null;
    private static String redisPort = null;

    /**
     * getJedis的链接池对象
     * @param key 要判断的key
     * @return
     */
    private static ShardedJedis getJedis() {
        try {
            if (pool == null) {
                String ip = PropertiesUtil.getValue("redisIp");
                String port = PropertiesUtil.getValue("redisPort");
                System.out.println("redisIp: " + ip);
                System.out.println("redisPort: " + port);
                List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo(ip, port));
                // new JedisShardInfo("192.168.10.14",6900)
                JedisPoolConfig config = new JedisPoolConfig();
                // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
                // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
                // config.setMaxActive(300);
                // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
                config.setMaxIdle(100);
                // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
                // config.setMaxWait(1000 * 5);
                // 在borrow一个jedis实例时，是否提前进行validate验证操作；如果为true，则得到的jedis实例均是可用的；
                config.setTestOnBorrow(false);
                pool = new ShardedJedisPool(config, shards);
            }

            ShardedJedis jedis = pool.getResource();

            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    /**
     * 储存摸个Key和值
     * @param key 存储的key
     * @param value 存储的值
     * @return
     */
    public static boolean set(String Key, String value) {
        ShardedJedis jedis = null;
        boolean error = false;// 标记缓存操作过程是否出现异常
        try {
            jedis = getJedis();
            jedis.set(Key, value);
            // jedis.disconnect();
            return true;
        } catch (Exception e) {
            error = true;
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static String get(String Key) {
        String end = null;
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            end = jedis.get(Key);
            return end;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}



