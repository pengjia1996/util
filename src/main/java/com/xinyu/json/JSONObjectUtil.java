package com.xinyu.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.xinyu.util.QwyUtil;

/**   
 * @Title:JSONObjectUtil
 * @author:彭嘉
 * @date:2020年11月5日 下午1:59:28  
 * @Description:json操作公共类 使用的是apache(org.json)的jar<br>
 * 添加相关方法时,请不要和Google的Gson,alibaba的FastJson等jar混用   
 */
public class JSONObjectUtil {

    /**
     * 将json转换成Map格式;
     * @param json JSONObject
     * @return Map<String,Object> 
     * @throws JSONException 
     */
    public static Map<String, Object> jsonParseToMap(JSONObject json) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (!QwyUtil.isNullAndEmpty(json)) {
                Iterator<String> iterator = json.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    map.put(key, json.get(key));
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return map;
    }
}



