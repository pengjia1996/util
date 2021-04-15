package com.xinyu.reflect;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.xinyu.util.QwyUtil;

/**
 * @Title:ReflectUtil
 * @author:彭嘉
 * @date:2020年10月26日 下午5:43:31
 * @Description:反射相关工具类
 */
public class ReflectUtil {
    private static Logger logger = Logger.getLogger(ReflectUtil.class);

    /**
     * 获取Field对象，并设置字段的属性可以访问,
     * 该对象反映此Class对象所表示的类或接口的指定kName已声明字段
     * @param clazz Class对象
     * @param kName 字段（属性）名
     * @return Field对象
     * @date:2019年9月30日 上午10:42
     */
    public static <V> Field getField(Class<V> clazz, String kName) {
        try {
            // 只要有一个参数为空，就返回null
            if (QwyUtil.isNullAndEmpty(clazz) || QwyUtil.isNullAndEmpty(kName)) {
                logger.info("clazz or kName is null");
                return null;
            }
            Field field = clazz.getDeclaredField(kName);
            // 设置字段的属性可以访问
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            logger.error("反射，获取指定名称的Field失败（方法getField）", e);
            return null;
        }
    }
}



