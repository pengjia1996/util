package com.xinyu.file;

import java.io.File;

import org.apache.log4j.Logger;

import com.xinyu.util.QwyUtil;

/**   
 * @Title:FileUtil
 * @author:彭嘉
 * @date:2020年12月3日 下午3:01:03  
 * @Description:文件公共类   
 */
public class FileUtil {

    private static Logger logger = Logger.getLogger(FileUtil.class);

    /**
     * 
     * @Title:exist
     * @author:彭嘉
     * @date:2020年12月9日 上午11:19:55  
     * @Description: 根据路径判断文件是否存在  
     * @param path 文件路径
     * @return boolean
     */
    public static boolean exist(String path) {
        if (QwyUtil.isNullAndEmpty(path)) {
            logger.info("path is null,文件路径不能为空");
            return false;
        }
        return new File(path).exists();
    }


}



