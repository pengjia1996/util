package com.xinyu.test;

import java.util.Date;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.xinyu.datetime.DateTimeUtil;
import com.xinyu.file.PropertiesUtil;

/**  
 * 用一句话描述这个类的作用 
 * @author:彭嘉
 * @date:2021年4月14日 下午2:00:31   
 */
public class TestDateTimeUtil {

    static {
        PropertyConfigurator.configure(PropertiesUtil.getLog4jPropertiesUrl());
    }

    public static void main(String[] args) {


    }

    /**
     * 测试获取两个时间之间的日期
     */
    @Test
    public void testGetBetweenDates() {
        Date startTime = new Date();
        // startTime = DateTimeUtil.addTimeByDayAndReset(startTime, -1);
        Date endTime = DateTimeUtil.addTimeByDayAndReset(startTime, 3);
        
        List<Date> betweenDates = DateTimeUtil.getBetweenDates(startTime, endTime);
        for (Date date : betweenDates) {
            System.err.println("test1:" + DateTimeUtil.fmDateyyyyMMddHHmmssSSS().format(date));
        }
        
        List<Date> betweenDates2 = DateTimeUtil.getBetweenDates(startTime, endTime, true);
        for (Date date : betweenDates2) {
            System.err.println("test2:" + DateTimeUtil.fmDateyyyyMMddHHmmssSSS().format(date));
        }
        
        List<Date> betweenDates3 = DateTimeUtil.getBetweenDates(startTime, endTime, false);
        for (Date date : betweenDates3) {
            System.err.println("test3:" + DateTimeUtil.fmDateyyyyMMddHHmmssSSS().format(date));
        }

    }

    /**
     * 测试日期加天数并重置未一天的开始时间
     */
    @Test
    public void testAddTimeByDayAndReset() {
        Date startTime = new Date();
        startTime = DateTimeUtil.addTimeByDayAndReset(startTime, 1);
        Date endTime = DateTimeUtil.addTimeByDayAndReset(startTime, -2);
    }

}



