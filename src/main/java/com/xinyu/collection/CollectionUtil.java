package com.xinyu.collection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.xinyu.reflect.ReflectUtil;
import com.xinyu.util.QwyUtil;



/**   
 * @Title:CollectionUtil
 * @author:彭嘉
 * @date:2020年10月26日 下午5:28:35  
 * @Description:集合操作公共类   
 */
@SuppressWarnings("all")
public class CollectionUtil {
    private static Logger logger = Logger.getLogger(CollectionUtil.class);

    /**
     * @Title:listToMap
     * @author:彭嘉
     * @date:2019年9月30日 上午11:08
     * @Description: 将list转成map，对象中指定属性（kName）的值作为map的key,对象作为map的value
     * @param vList 需要转换成map的list
     * @param kName list中对象的属性名称,(用于将该属性的值作为map的key,一般为对象的id)
     * @return Map
     */
    public static <K, V> Map<K, V> listToMap(List<V> vList, String kName) {
        // 需要返回的map
        Map<K, V> map = new HashMap<K, V>();
        try {
            // 判断集合是否为空，属性名是否为空，为空不能转化，返回null
            if (QwyUtil.isNullAndEmpty(vList) || QwyUtil.isNullAndEmpty(kName)) {
                logger.info("vList or kName is null");
                return map;
            }
            // 获取kName的Field
            Field kField = ReflectUtil.getField(vList.get(0).getClass(), kName);
            if (!QwyUtil.isNullAndEmpty(kField)) {
                // 循环lsit,获取对象指定kName的属性的值，作为key
                for (V v : vList) {
                    map.put((K)kField.get(v), v);
                }
            } else {
                logger.info("根据kName，获取不到Field(对象的属性)");
            }
        } catch (Exception e) {
            logger.error("将list转成map err", e);
        }
        return map;
    }

    /**
     * 
     * @Title:listToMapList
     * @author:彭嘉
     * @date:2020年10月27日 上午9:07:37  
     * @Description: List 转Map<K,List<V>>   
     * @param <K> key的类型
     * @param <V> 集合中对象的类型
     * @param vList 要转换的集合
     * @param kName list中对象的属性名称,(用于将该属性的值作为map的key)
     * @return  Map<K,List<V>>
     */
    public static <K, V> Map<K, List<V>> listToMapList(List<V> vList, String kName) {
        // 需要返回的map
        Map<K, List<V>> map = new HashMap<K, List<V>>();
        try {
            // 判断集合是否为空，属性名是否为空，为空不能转化，返回
            if (QwyUtil.isNullAndEmpty(vList) || QwyUtil.isNullAndEmpty(kName)) {
                logger.info("vList or kName is null");
                return map;
            } else {
                // 获取kName的Field
                Field kField = ReflectUtil.getField(vList.get(0).getClass(), kName);
                List<V> list = null;
                if (!QwyUtil.isNullAndEmpty(kField)) {
                    // 循环lsit,获取对象指定kName的属性的值，作为key
                    for (V v : vList) {
                        K key = (K)kField.get(v);
                        if (map.containsKey(key)) {
                            list = map.get(key);
                        } else {
                            list = new ArrayList<V>();
                        }
                        list.add(v);
                        map.put(key, list);

                    }
                } else {
                    logger.info("根据kName，获取不到Field(对象的属性)");
                }
                return map;
            }
        } catch (Exception e) {
            logger.error("将list转成map<T,List> err", e);
        }
        return map;
    }

    /**
     * @Title:toSplitStrByListObjKey
     * @author:彭嘉
     * @date:2019年9月30日 下午13:08
     * @Description 
     * 
     * 将泛型集合list中某个属性的值拼装成string <br>
     * 例如，想将List<User> 中user对象的所有id已 ',' 拼装起来 ，参数kName 为"id",separated 为"," <br>
     * 最终格式为 xxx,xxx,xxx <br>
     * 如果需要的格式为: 'xxx','xxx','xxx' 参数includeStr的值为 ' <br>
     * 注意:kName的值必须是对象已有的属性，不能写错，根据kName获取对象的值必须能转换成字符串
     * 
     * @param tList 需要转换成map的list 
     * @param kName list中对象的属性名称 
     * @param separator 拼接的分隔符
     * @param includeStr 元素被包裹的符号
     * @return String
     */
    public static <T> String toSplitStrByListObjKey(List<T> tList, String kName, String separator,
        String includeStr) {
        StringBuffer sb = new StringBuffer();
        try {
            if (QwyUtil.isNullAndEmpty(tList)) {
                logger.info("vList is null,要拼接的集合不能为空");
                return "";
            } 
            
            if (QwyUtil.isNullAndEmpty(kName)) {
                logger.info("kName is null,集合中元素的属性名称不能为空");
                return "";
            }
            
            if (QwyUtil.isNullAndEmpty(separator)) {
                logger.info("separator is null,要拼接的分隔符不能为空");
                return "";
            }
            
            // 获取kName的Field
            Field kField = ReflectUtil.getField(tList.get(0).getClass(), kName);
            if (QwyUtil.isNullAndEmpty(kField)) {
                logger.info("根据kName，获取不到Field(对象的属性)");
                return "";
            }
            
            if (QwyUtil.isNullAndEmpty(includeStr)) {
                // 循环lsit,获取对象指定kName的属性的值，作为key
                for (T t : tList) {
                    if (kField.get(t) != null) {
                        sb.append((String)kField.get(t) + separator);
                    }
                }
            } else {
                for (T t : tList) {
                    if (kField.get(t) != null) {
                        sb.append(includeStr + (String)kField.get(t) + includeStr + separator);
                    }
                }
            }
            
            if (sb.length() > 0) {
                // 删除最后一个字符（分隔符）并返回
                return sb.deleteCharAt(sb.length() - 1).toString();
            }

            
            
        } catch (Exception e) {
            logger.error("toSplitStrBylistModelKey,将list中某个属性的值拼装成string err", e);
        }
        return "";
    }

}



