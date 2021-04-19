package com.xinyu.redis;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.xinyu.file.PropertiesUtil;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author:彭嘉
 * @date:2020年11月4日 下午4:20:54  
 * @Description:redis缓存操作公共类   
 */
public class RedisUtil {

    private static final Logger logger = Logger.getLogger(RedisUtil.class);
    public static ShardedJedisPool pool;


    /**
     * 从链接池获取一个redis链接
     */
    public static synchronized ShardedJedis getJedis() {
        try {
            if (pool == null) {
                String ip = PropertiesUtil.getValue("redisIp");
                String port = PropertiesUtil.getValue("redisPort");
                logger.info("redisIp:" + ip);
                logger.info("redisPort: " + port);
                List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo(ip, port));
                // jedis配置对象
                JedisPoolConfig config = new JedisPoolConfig();

                // 最小空闲链接数 默认0
                config.setMinIdle(0);
                // 最大空闲链接数 默认8个
                config.setMaxIdle(8);
                // 最大链接数,默认8个,为负数时不限制
                config.setMaxTotal(8);

                /*// 是否验证链接可用,true验证,false不验证,默认false
                config.setTestWhileIdle(true);
                // 最大空闲链接数, 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
                config.setMaxIdle(100);
                // 在方法返回之前是否经过验证从池中取出的链接对象是否有效。
                // 验证由与池关联的工厂的<code>validateObject()</code>方法执行。
                // 如果对象验证失败，则将从池中删除并销毁该对象，并再次尝试从池中取链接对象。默认false
                config.setTestOnBorrow(true);
                // 设置空闲多久多长时间销毁链接,且空闲连接>最大空闲数 时直接销毁,不再根据MinEvictableIdleTimeMillis判断 (默认逐出策略)
                config.setSoftMinEvictableIdleTimeMillis(0);
                // 销毁扫描的时间间隔(毫秒) 如果为负数,则不运行销毁线程, 默认-1
                config.setTimeBetweenEvictionRunsMillis(-1);
                // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
                config.setBlockWhenExhausted(true);
                // 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
                config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
                // 是否启用pool的jmx管理功能, 默认true
                config.setJmxEnabled(true);
                // MBean ObjectName = new ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" + "pool" + i); 默 认为"pool",
                // JMX不熟,具体不知道是干啥的...默认就好.
                config.setJmxNamePrefix("pool");
                // 是否启用后进先出, 默认true
                config.setLifo(true);
                // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间, 默认-1
                config.setMaxWaitMillis(-1);
                // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
                config.setMinEvictableIdleTimeMillis(1800000);
                // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
                config.setNumTestsPerEvictionRun(3);*/
                pool = new ShardedJedisPool(config, shards);
            }

            ShardedJedis jedis = pool.getResource();
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("从链接池获取redis链接失败", e);
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
        try {
            jedis = getJedis();
            String set = jedis.set(Key, value);
            return "ok".equals(set);
        } catch (Exception e) {
            logger.error("set(String Key, String value) error", e);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}



