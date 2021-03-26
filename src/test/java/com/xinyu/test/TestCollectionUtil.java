package com.xinyu.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.xinyu.collection.CollectionUtil;
import com.xinyu.file.PropertiesUtil;
import com.xinyu.orm.User;

/**  
 * 用一句话描述这个类的作用 
 * @author:彭嘉
 * @date:2021年3月5日 下午2:14:51   
 */
public class TestCollectionUtil {

    static {
        PropertyConfigurator.configure(PropertiesUtil.getLog4jPropertiesUrl());
    }

    public static void main(String[] args) {
        User u1 = new User("1", "xiaohei", 18, new Date());
        User u2 = new User("2", null, 16, null);
        User u3 = new User("3", "xiaobai", 16, null);
        List<User> list = new ArrayList<User>();
        list.add(u1);
        list.add(u2);
        list.add(u3);

        String hhh = CollectionUtil.toSplitStrByListObjKey(list, "name", "、", "'");

        System.err.println(hhh);

    }

    @Test
    public void test1() {
        User u1 = new User("1", "xiaohei", 18, new Date());
        User u2 = new User("2", "", 16, null);
        List<User> list = new ArrayList<User>();
        list.add(u1);
        list.add(u2);

        String hhh = CollectionUtil.toSplitStrByListObjKey(list, "name", "、", "'");

        System.err.println(hhh);
    }
}



