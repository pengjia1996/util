package com.xinyu.file.util;

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

    // properties_url.properties 的key propertiesUrl(即日志配置文件,数据库配置文件,java.properties等文件的路径)
    private static String propertiesUrl = null;

    // java.properties 文件的路径
    private static String javaPropertiesUrl = null;

    // log4j.properties 文件的路径
    private static String log4jPropertiesUrl = null;

    private static String defaultJavaPropertiesUrl = "";


    /**
     * @Title:getLog4jPropertiesUrl
     * @author:彭嘉
     * @date:2020年11月5日 下午3:12:46  
     * @Description: 获取log4j.properties文件的路径    
     * @return String
     */
    public static String getLog4jPropertiesUrl() {
        try {
            if (QwyUtil.isNullAndEmpty(log4jPropertiesUrl)) {
                synchronized (PropertiesUtil.class) {
                    if (QwyUtil.isNullAndEmpty(log4jPropertiesUrl)) {
                        log4jPropertiesUrl = getPropertiesUrl() + "/log4j.properties";
                        return log4jPropertiesUrl;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
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
    public static String getJavaPropertiesUrl() {
        try {
            if (QwyUtil.isNullAndEmpty(javaPropertiesUrl)) {
                synchronized (PropertiesUtil.class) {
                    if (QwyUtil.isNullAndEmpty(javaPropertiesUrl)) {
                        if (QwyUtil.isNullAndEmpty(getPropertiesUrl())) {
                            logger.info("配置文件路径获取失败,默认从类路径加载查找");


                        } else {
                            String pathName = getPropertiesUrl() + "/java.properties";
                            if (FileUtil.isExistFile(pathName)) {
                                logger.info("请检查[" + pathName + "]路径下该文件是否存在");
                                System.err.println("请检查[" + pathName + "]路径下该文件是否存在");
                                return null;
                            } else {
                                javaPropertiesUrl = pathName;
                                return javaPropertiesUrl;
                            }

                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
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
            e.printStackTrace();
            logger.error(e.getMessage(), e);
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
    public static String getPropertiesUrl() {
        try {
            if (QwyUtil.isNullAndEmpty(propertiesUrl)) {
                synchronized (PropertiesUtil.class) {
                    if (QwyUtil.isNullAndEmpty(propertiesUrl)) {
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
                        propertiesUrl = property.trim();
                        return propertiesUrl;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return null;
        }
        return propertiesUrl;
    }

}



