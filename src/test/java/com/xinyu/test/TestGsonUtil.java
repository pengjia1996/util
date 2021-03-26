package com.xinyu.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinyu.json.GsonUtil;
import com.xinyu.orm.User;

/**   
 * @Title:TestGsonUtil
 * @author:彭嘉
 * @date:2020年11月10日 上午11:27:06  
 * @Description:测试GsonUtil    
 */
public class TestGsonUtil {
    public static void main(String[] args) {
        Gson gson = GsonUtil.createGson();

        User u1 = new User("1", "xiaohei", 18, new Date());
        User u2 = new User("2", "", 16, null);
        List<User> list = new ArrayList<User>();
        list.add(u1);
        list.add(u2);
        String json = gson.toJson(list);
        System.err.println(json);


        Object fromJson = gson.fromJson(json, new TypeToken<List<User>>() {}.getType());
        System.err.println(fromJson);


    }

    @Test
    public void test1() {
        Gson gson = GsonUtil.createGson();

        User u1 = new User("1", "xiaohei", 18, new Date());

        User u2 = new User("2", "", 16, null);
        
        String json1 = gson.toJson(u1);
        String json2 = gson.toJson(u2);

        System.err.println(json1);
        System.err.println(json2);

        String json3 = gson.toJson(u2);
        System.err.println(json3);

    }

}



