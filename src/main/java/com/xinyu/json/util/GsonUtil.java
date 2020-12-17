package com.xinyu.json.util;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xinyu.util.QwyUtil;

/**   
 * @Title:GsonUtil
 * @author:彭嘉
 * @date:2020年11月5日 下午5:04:27  
 * @Description:gson操作公共类   
 */
public class GsonUtil {

    // gson频繁的创建和销毁会消耗大量资源,使用单例模式
    private static Gson gson = null;

    /**
     * 
     * @Title:createGson
     * @author:彭嘉
     * @date:2020年11月9日 下午6:37:23  
     * @Description: 创建Gson对象,单例 
     * @return Gson
     */
    public static Gson createGson() {
        if (QwyUtil.isNullAndEmpty(gson)) {
            synchronized (GsonUtil.class) {
                if (QwyUtil.isNullAndEmpty(gson)) {
                    gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").registerTypeAdapter(Date.class, new DateAdapter()).create();
                }
                return gson;
            }
        }
        return gson;
    }

    public <T> T jsonToBean(String jsonStr, T t) {
        t = gson.fromJson(jsonStr, new TypeToken<T>() {}.getType());

        return t;

    }
}



