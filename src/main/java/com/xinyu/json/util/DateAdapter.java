package com.xinyu.json.util;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * 
 * @Title:DateAdapter
 * @author:彭嘉
 * @date:2020年11月9日 下午6:34:04  
 * @Description:google json字符转json对象时日期适配
 */
public class DateAdapter implements JsonDeserializer<Date> {
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 
     * <p>Title: deserialize</p>
     * @author:彭嘉
     * @date:2020年11月6日下午4:03:20  
     * <p>Description: 自定义反序列化器</p>   
     * @param jsonElement 反序列化的json数据
     * @param type 要反序列化的对象类型
     * @param context 反序列化上下文
     * @return
     * @throws JsonParseException   
     * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
     */
    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        try {
            return df.parse(jsonElement.getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}