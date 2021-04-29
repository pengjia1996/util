package com.xinyu.json;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Gson 序列化和反序列化,时间戳适配器
 *
 * @author pengjia
 * @version 1.0
 */
public class TimestampAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {

    /**
     * 反序列化
     *
     * @param json    需要反序列化的json元素
     * @param typeOfT 反序列化后的类类型
     * @param context 序列化上下文
     * @return java.util.Date
     * @author pengjia
     * @date 2021/4/27 18:07
     */
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Date(json.getAsJsonPrimitive().getAsLong());
    }


    /**
     * 序列化
     *
     * @param src       源,需要序列化的日期
     * @param typeOfSrc 源对象的类型
     * @param context   序列化上下文
     * @return com.google.gson.JsonElement
     * @author pengjia
     * @date 2021/4/27 18:09
     */
    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {

        return new JsonPrimitive(src.getTime());
    }
    
}
