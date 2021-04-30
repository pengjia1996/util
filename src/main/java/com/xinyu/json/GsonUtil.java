package com.xinyu.json;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinyu.util.QwyUtil;

/**   
 * gson操作公共类
 * @author:彭嘉
 */
public class GsonUtil {

    // gson频繁的创建和销毁会消耗大量资源,使用单例模式
    // 反序列使用 new TypeToken<List<User>>() {}.getType()
    private static Gson gson = null;

    private static Gson gson2 = null;

    /**
     * 
     * 获取Gson对象,单例
     * @return Gson
     */
    public static synchronized Gson getGson() {
        if (QwyUtil.isNullAndEmpty(gson)) {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").registerTypeAdapter(Date.class, new DateAdapter()).create();
        }
        return gson;

    }

    /**
     *
     * 生成Gson ,兼容时间戳
     * @return
     */
    public static synchronized Gson getGson2() {
        if (QwyUtil.isNullAndEmpty(gson2)) {
            // 日期时间戳转换
            gson2 = new GsonBuilder()
                .registerTypeAdapter(Date.class, new TimestampAdapter())
                .create();

        }
        return gson2;
    }



}



