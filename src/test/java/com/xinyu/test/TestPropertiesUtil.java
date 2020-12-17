package com.xinyu.test;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.xinyu.file.util.PropertiesUtil;

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

    @Test
    public void test1() {

        String propertiesUrl = PropertiesUtil.getValue("url");
        System.err.println(propertiesUrl);
    }
}



