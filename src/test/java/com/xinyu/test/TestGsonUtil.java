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


        Gson gson = GsonUtil.getGson2();
        User u1 = new User("1", "xiaohei", 18, new Date());
        User u2 = new User("2", "", 16, null);
        List<User> list = new ArrayList<User>();
        list.add(u1);
        list.add(u2);
        String json = gson.toJson(list);
        System.err.println(json);
        List<User> list2=gson.fromJson(json,new TypeToken<List<User>>() {}.getType());
        System.err.println(list2);
    }

    @Test
    public void test2(){
        Gson gson = GsonUtil.getGson();
        User u1 = new User("1", "xiaohei", 18, new Date());
        User u2 = new User("2", "", 16, null);
        List<User> list = new ArrayList<User>();
        list.add(u1);
        list.add(u2);

        // System.err.println(list);
        String json = gson.toJson(list);
        // System.err.println(json);

        ArrayList<User> list2 = gson.fromJson(json, new ArrayList<User>().getClass());
        // System.err.println(list2);

        TypeToken<List<User>> typeToken = new TypeToken<List<User>>() {};
        System.err.println(typeToken.getClass());
        System.err.println(typeToken.getType());

        List<User> list3 = gson.fromJson(json, typeToken.getType());
        // System.err.println(list3);

        /*new TypeToken<List<User>>() {}.getType()*/
    }

    @Test
    public void test1() {
        Gson gson = GsonUtil.getGson();

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



