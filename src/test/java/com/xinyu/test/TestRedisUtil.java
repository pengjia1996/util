package com.xinyu.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.xinyu.file.PropertiesUtil;
import com.xinyu.redis.RedisUtil;

/**  
 * redis公共类测试
 * @author:彭嘉
 * @date:2021年4月19日 下午4:01:01   
 */
public class TestRedisUtil {
    private static Logger logger = Logger.getLogger(TestRedisUtil.class);

    static {
        PropertyConfigurator.configure(PropertiesUtil.getLog4jPropertiesUrl());
    }

    /**
     * 测试或redis链接
     * @throws InterruptedException 
     */
    @Test
    public void testGetJedis() throws InterruptedException {


        for (int i = 0; i < 100; i++) {
            new Thread() {
                @Override
                public void run() {

                    for (int m = 0; m < 100; m++) {

                        boolean set2 = RedisUtil.set("age" + m, Math.random() + "");
                        /* int numIdle = RedisUtil.pool.getNumIdle();
                        logger.info("空闲链接数" + numIdle);
                        int numActive = RedisUtil.pool.getNumActive();
                        logger.info("===========活动链接数=========" + numActive);*/
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();
        }





    }

}



