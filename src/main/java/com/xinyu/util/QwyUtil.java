package com.xinyu.util;

import java.util.Collection;

/**   
 * @Title:QwyUtil
 * @author:彭嘉
 * @date:2020年10月26日 下午5:39:46  
 * @Description:常用公共类   
 */
public class QwyUtil {

    // CommonUtil

    /**
     * 
     * @Title:isNullAndEmpty
     * @author:彭嘉
     * @date:2020年10月26日 下午5:41:37  
     * @Description: 判断集合是否为空    
     * @param <T>
     * @param list
     * @return boolean
     */
    public static <T> boolean isNullAndEmpty(Collection<T> list) {
        if ((list != null) && (list.size() > 0)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @Title:isNullAndEmpty
     * @author:彭嘉
     * @date:2020年10月26日 下午5:45:34  
     * @Description: 判断字符串是否为空   
     * @param str
     * @return boolean
     */
    public static boolean isNullAndEmpty(String str) {
        return StringUtil.isNull(str);
    }

    /**
     * 
     * @Title:isNullAndEmpty
     * @author:彭嘉
     * @date:2020年10月26日 下午5:45:34  
     * @Description: 判断字符串是否为空(更大范围判空)    
     * @param str
     * @return boolean
     */
    public static boolean isNullAndEmptyByStr(String str) {
        return StringUtil.isNull2(str);
    }

    /**
     * 
     * @Title:isNullAndEmpty
     * @author:彭嘉
     * @date:2020年10月26日 下午5:57:34  
     * @Description: 判断obj是否为空   
     * @param obj
     * @return boolean
     */
    public static boolean isNullAndEmpty(Object obj) {
        if ((obj != null) && !"".equals(obj.toString()) && !obj.equals("null")) {
            return false;
        } else {
            return true;
        }
    }
}



