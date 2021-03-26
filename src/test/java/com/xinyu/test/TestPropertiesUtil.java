package com.xinyu.test;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.xinyu.file.PropertiesUtil;

/**   
 * @Title:TestPropertiesUtil
 * @author:彭嘉
 * @date:2020年12月3日 下午2:54:00  
 * @Description:测试属性文件公共类 
 */
public class TestPropertiesUtil {
    static {
        PropertyConfigurator.configure(PropertiesUtil.getLog4jPropertiesUrl());
    }

    public static void main(String[] args) {
        TestPropertiesUtil test = new TestPropertiesUtil();
        test.test1();
    }

    /**
     * 获取配置文件路径
     */
    @Test
    public void test1() {
        String propertiesUrl = PropertiesUtil.getPropertiesUrl();
        System.err.println("配置文件路径:" + propertiesUrl);
    }
}



