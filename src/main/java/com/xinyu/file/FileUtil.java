package com.xinyu.file;

import com.xinyu.util.QwyUtil;

import java.io.File;


/**
 * 文件公共类
 *
 * @author pengjia
 */
public class FileUtil {


    /**
     * 根据路径判断文件是否存在
     * @param path 文件路径
     * @return boolean
     * @author pengjia
     * @date 2021/5/9 17:49
     */
    public static boolean exist(String path) {
        if (QwyUtil.isNullAndEmpty(path)) {
            System.err.println("path is null,文件路径不能为空");
            return false;
        }
        return new File(path).exists();
    }


}



