package com.xinyu.web.util;

import javax.servlet.http.HttpServletRequest;

/**   
 * @Title:WebUtil
 * @author:彭嘉
 * @date:2020年11月2日 上午9:00:50  
 * @Description:web相关操作公共类   
 */
public class WebUtil {
    /**
     * 
     * @Title:getIpAddress
     * @author:彭嘉(方法移动)
     * @date:2020年10月30日 15:14:49
     * @Description:获取ip地址
     * @param request HttpServletRequest对象
     * @return  String ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}



