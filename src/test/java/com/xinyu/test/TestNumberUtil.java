package com.xinyu.test;

import com.xinyu.file.PropertiesUtil;
import com.xinyu.number.NumberUtil;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author pengjia
 * @version 1.0
 * @date 2021/4/30 17:36
 */
public class TestNumberUtil {

    static {
        PropertyConfigurator.configure(PropertiesUtil.getLog4jPropertiesUrl());
    }

    public static void main(String[] args) {
        String format = NumberUtil.format(346324236124.345145461050000100);
        System.err.println(format);
    }
}
