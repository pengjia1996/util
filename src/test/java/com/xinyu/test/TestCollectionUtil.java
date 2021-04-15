package com.xinyu.test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.PropertyConfigurator;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.xinyu.file.PropertiesUtil;
import com.xinyu.json.GsonUtil;
import com.xinyu.orm.User;
import com.xinyu.reflect.ReflectUtil;

/**  
 * 用一句话描述这个类的作用 
 * @author:彭嘉
 * @date:2021年3月5日 下午2:14:51   
 */
public class TestCollectionUtil {

    static {
        PropertyConfigurator.configure(PropertiesUtil.getLog4jPropertiesUrl());
    }

    public static void main(String[] args){

        
        try {
         // JAVA 8直接用流的方法
            User u1 = new User("1", "xiaohei", 18, new Date());
            User u2 = new User("2", null, 16, null);
            User u3 = new User("3", "xiaobai", 16, null);
            List<User> list = new ArrayList<User>();
            list.add(u1);
            list.add(u2);
            list.add(u3);
            Map<String, User> collect = list.stream().collect(Collectors.toMap(User::getId, (p) -> p));
            System.err.println(GsonUtil.getGson().toJson(collect));
            
            // ListUtil.
            Map<String, User> map = Maps.uniqueIndex(list, new Function<User, String>() {
                @Override
                public String apply(User user) {
                    return user.getId();
                }
            });
            
            
            System.err.println(GsonUtil.getGson().toJson(map));

            Class cls = User.class;
            
            Field kField = ReflectUtil.getField(cls, "id");
            
            Object object = kField.get(u1);
            
            System.err.println(object);

            System.err.println(kField.getName());

            System.err.println(kField.toGenericString());

            Type type = kField.getGenericType();
            System.err.println(type);

            Object obj1 = 12;
            Object obj2 = "xxx";

            /*  System.err.println(cls);
            Constructor constructor = cls.getConstructor();
            Object newInstance = constructor.newInstance();*/

            /*  Map<String, User> map = new HashMap();
            Class<? extends Map> class1 = map.getClass();
            Type type = (Type)map.getClass();
            System.err.println(class1);
            System.err.println(type);*/




            /*Map<Long, User> listToMap = CollectionUtil.listToMap(list, "id");
            
            System.err.println(GsonUtil.getGson().toJson(listToMap));*/
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        

    }


}



