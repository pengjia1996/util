package com.xinyu.json;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinyu.util.QwyUtil;

/**   
 * @Title:GsonUtil
 * @author:彭嘉
 * @date:2020年11月5日 下午5:04:27  
 * @Description:gson操作公共类   
 */
public class GsonUtil {

    // gson频繁的创建和销毁会消耗大量资源,使用单例模式
    // 反序列使用 new TypeToken<List<User>>() {}.getType()
    private static Gson gson = null;

    /**
     * 
     * 获取Gson对象,单例 
     * @date:2020年11月9日 下午6:37:23  
     * @return Gson
     */
    public static synchronized Gson getGson() {
        if (QwyUtil.isNullAndEmpty(gson)) {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").registerTypeAdapter(Date.class, new DateAdapter()).create();
        }
        return gson;

    }


}



