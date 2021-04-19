package com.xinyu.defaults;

import java.util.List;

/**  
 * 默认公共类
 * @author:彭嘉
 * @date:2021年4月15日 上午10:25:42   
 */
public class DefaultUtil {

    /**
     * obj对象为空返回对象的默认值 0L
     * @date 2021年4月15日 上午10:28:32    
     * @param obj
     * @return Long
     */
    public static Long getLong(Long obj) {
        return obj == null ? 0L : obj;
    }

    /**
     * obj对象为空返回对象的默认值 0
     * @date 2021年4月15日 上午10:28:32    
     * @param obj
     * @return Integer
     */
    public static Integer getInteger(Integer obj) {
        return obj == null ? 0 : obj;
    }

    /**
     * 
     * @author 彭嘉
     * @date 2021年4月16日 上午3:05:57
     * @description 获取结合的大小      
     * @param <T>
     * @param list
     * @return int
     */
    public static <T> int getListSize(List<T> list) {
        return list == null ? 0 : list.size();
    }

}


