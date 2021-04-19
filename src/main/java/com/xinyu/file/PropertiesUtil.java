package com.xinyu.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.xinyu.util.QwyUtil;

/**   
 * @Title:PropertiesUtil
 * @author:彭嘉
 * @date:2020年11月4日 下午4:27:54  
 * @Description:属性文件(.properties)相关操作公共类   
 */
public class PropertiesUtil {
    private static Logger logger = Logger.getLogger(PropertiesUtil.class);

    /**
     *  properties_url.properties 的key propertiesUrl
     *  (即日志配置文件,数据库配置文件,java.properties等文件的路径)
     */
    private static String propertiesUrl = null;

    /**
     *  java.properties 文件的路径
     */
    private static String javaPropertiesUrl = null;

    /**
     *  log4j.properties 文件的路径
     */
    private static String log4jPropertiesUrl = null;

    private static String defaultJavaPropertiesUrl = "";

    /**
     * @Title:getLog4jPropertiesUrl
     * @author:彭嘉
     * @date:2020年11月5日 下午3:12:46  
     * @Description: 获取log4j.properties文件的路径    
     * @return String
     */
    public static synchronized String getLog4jPropertiesUrl() {

        if (QwyUtil.isNullAndEmpty(log4jPropertiesUrl)) {
            try {

                String configUrl = getPropertiesUrl();
                if (!QwyUtil.isNullAndEmpty(configUrl)) {

                    String path = configUrl + "/log4j.properties";
                    if (FileUtil.exist(path)) {
                        log4jPropertiesUrl = path;
                        return log4jPropertiesUrl;
                    } else {
                        // 日志配置还未加载,打印不了
                        System.err.println("请检查该路径下[" + path + "]是否存在log4j.properties文件");
                        System.err.println("该路径[" + path + "]不存在log4j.properties文件,默认从类路径加载文件");
                    }

                } else {
                    System.err.println("配置文件路径为空,默认从类路径加载log4j.properties文件");
                    // TODO
                }


            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("获取log4j日志属性文件路径失败");
            }
        }

        return log4jPropertiesUrl;

    }

    /**
     * 
     * @Title:getJavaPropertiesUrl
     * @author:彭嘉
     * @date:2020年11月5日 下午3:10:20  
     * @Description: 获取java.properties 文件的路径    
     * @return String
     */
    public static synchronized String getJavaPropertiesUrl() {

        if (QwyUtil.isNullAndEmpty(javaPropertiesUrl)) {
            try {
                if (QwyUtil.isNullAndEmpty(getPropertiesUrl())) {
                    logger.info("配置文件路径获取失败,默认从类路径加载查找");
                    // TODO
                } else {
                    String pathName = getPropertiesUrl() + "/java.properties";
                    // 判断文件是否存在
                    if (FileUtil.exist(pathName)) {
                        // 存在
                        javaPropertiesUrl = pathName;
                        return javaPropertiesUrl;
                    } else {
                        // 不存在
                        logger.info("请检查[" + pathName + "]路径下该文件是否存在");
                        return null;
                    }

                }
            } catch (Exception e) {
                logger.error("java.properties文件的路径出错", e);
                return null;
            }

        }
        return javaPropertiesUrl;

    }

    /**
     * @Title:getValue
     * @author:彭嘉
     * @date:2020年11月5日 上午9:36:53  
     * @Description:   获取java.properties配置文件对应key的值
     * @param key properties文件的key
     * @return String
     */
    public static String getValue(String key) {
        InputStream in = null;
        try {
            Properties prop = new Properties();
            File field = new File(getJavaPropertiesUrl());
            in = new FileInputStream(field);
            prop.load(in);
            // 避免空指针
            String value = prop.getProperty(key) == null ? null : prop.getProperty(key).trim();
            logger.info(getJavaPropertiesUrl() + "配置文件,key=" + key + ",value=" + value);
            if (QwyUtil.isNullAndEmpty(value)) {
                return null;
            }
            return value;
        } catch (Exception e) {
            logger.error("获取key的值出错", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("关闭资源异常");

                }

            }
        }
        return null;
    }

    /**
     * 
     * @Title:getPropertiesUrl
     * @author:彭嘉
     * @date:2020年11月5日 上午9:36:00  
     * @Description: 获取配置文件的路径 
     * @return String
     */
    public static synchronized String getPropertiesUrl() {
        if (QwyUtil.isNullAndEmpty(propertiesUrl)) {
            try {
                Properties prop = new Properties();
                InputStream in = PropertiesUtil.class.getResourceAsStream("/properties_url.properties");
                if (QwyUtil.isNullAndEmpty(in)) {
                    logger.info("请检查类加载后根路径下是否存在properties_url.properties文件");
                    return null;
                }
                prop.load(in);
                // 避免空指针
                String property = prop.getProperty("propertiesUrl");
                if (QwyUtil.isNullAndEmpty(property)) {
                    logger.info("配置文件的路径获取失败,请检查properties_url.properties文件是否存在key(propertiesUrl)");
                    return null;
                }

                String path = property.trim();
                boolean isExist = FileUtil.exist(path);
                if (isExist) {
                    propertiesUrl = path;
                    return propertiesUrl;
                } else {
                    logger.info("请检查该路径[" + path + "]是否存在,配置文件是否存在");
                    return null;
                }


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return propertiesUrl;
    }

}
