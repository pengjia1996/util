package com.xinyu.ssh;

import org.apache.log4j.Logger;

/**   
 * @Title:DBOperateAbstract
 * @author:彭嘉
 * @date:2020年11月26日 上午11:50:28  
 * @Description:数据库操作公共类
 * 目前只对mysql数据库进行操作,使用ssh框架
 */
public class DBOperateSSH {
    
    private static Logger logger = Logger.getLogger(DBOperateSSH.class);

    /**
     * 创建数据库默认的使用的字符集
     * utf8mb4支持数据库的版本需要高于5.5.3 ,本人测试使用的版本 8.0.16
     */
    String DEFAULTCHARACTER = "utf8mb4";

    /**
     * 创建数据库默认的使用的校对规则
     */
    String DEFAULTCOLLATE = "utf8mb4_general_ci";
    


    /* public static boolean createDBByName(String dbName) {
        logger.info("进入BackupsUtil的createDBByName方法");
        try {
            // 保存创建数据库的sql
            StringBuffer creatDBSql = new StringBuffer();
            creatDBSql.append("create database if not exists " + dbName + " ");
            // 设置字符集
            creatDBSql.append("default character set gbk ");
            // 设置字符集默认校对规则
            creatDBSql.append("default collate gbk_chinese_ci ");
            // 执行sql并接收受影响的行数
            boolean result = false;
            int resultInt = Db.update(creatDBSql.toString());
            if(1==resultInt) {
                result= true;
            }else{
                //新库如已存在则不执行。
                logger.info("数据库创建失败，"+dbName+"可能已存在");
                result = false;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("BackupsUtil的createDBByName error", e);
        }
        return false;
    }*/

}



