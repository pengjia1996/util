package com.xinyu.defaults;

import java.util.List;

/**
 * 默认公共类
 *
 * @author 彭嘉
 */
public class DefaultUtil {

    /**
     * obj对象为空返回对象的默认值 0L
     *
     * @param obj
     * @return Long
     */
    public static Long getLong(Long obj) {
        return obj == null ? 0L : obj;
    }

    /**
     *
     * obj对象为空返回对象的默认值 0
     * @param obj
     * @return Integer
     */
    public static Integer getInteger(Integer obj) {
        return obj == null ? 0 : obj;
    }

    /**
     * 获取集合的大小
     * @param <T>
     * @param list
     * @return int
     * @author 彭嘉
     */
    public static <T> int getListSize(List<T> list) {
        return list == null ? 0 : list.size();
    }

    /**
     * obj为空时返回""空字符,不为空返回字符串
     * @param obj
     * @return String
     * @author 彭嘉
     */
    public static String getString(Object obj) {
        return obj == null ? "" : obj + "";
    }

}



