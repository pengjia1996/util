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


    /**
     * 将list转成map，对象中指定属性（kName）的值作为map的key,对象作为map的value
     *
     * @param vList 需要转换成map的list
     * @param kName list中对象的属性名称,(用于将该属性的值作为map的key,一般为对象的id)
     * @return java.util.Map<K, V>
     * @author pengjia
     * @date 2021/5/9 16:51
     */
    public static <K, V> Map<K, V> listToMap(List<V> vList, String kName) {
        // 需要返回的map
        Map<K, V> map = new HashMap<K, V>();
        try {
            // 判断集合是否为空，属性名是否为空，为空不能转化，返回null
            if (QwyUtil.isNullAndEmpty(vList) || QwyUtil.isNullAndEmpty(kName)) {
                System.err.println("vList or kName is null");
                return map;
            }
            // 获取kName的Field
            Field kField = ReflectUtil.getField(vList.get(0).getClass(), kName);
            if (!QwyUtil.isNullAndEmpty(kField)) {
                // 循环lsit,获取对象指定kName的属性的值，作为key
                for (V v : vList) {
                    map.put((K) kField.get(v), v);
                }
            } else {
                System.err.println("根据kName，获取不到Field(对象的属性) kName=" + kName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * List 转Map<K,List<V>>
     *
     * @param vList 要转换的集合
     * @param kName list中对象的属性名称,(用于将该属性的值作为map的key)
     * @return java.util.Map<K, java.util.List < V>>
     * @author pengjia
     * @date 2021/5/9 16:55
     */
    public static <K, V> Map<K, List<V>> listToMapList(List<V> vList, String kName) {
        // 需要返回的map
        Map<K, List<V>> map = new HashMap<K, List<V>>();
        try {
            // 判断集合是否为空，属性名是否为空，为空不能转化，返回
            if (QwyUtil.isNullAndEmpty(vList) || QwyUtil.isNullAndEmpty(kName)) {
                System.err.println("vList or kName is null");
                return map;
            } else {
                // 获取kName的Field
                Field kField = ReflectUtil.getField(vList.get(0).getClass(), kName);
                List<V> list = null;
                if (!QwyUtil.isNullAndEmpty(kField)) {
                    // 循环lsit,获取对象指定kName的属性的值，作为key
                    for (V v : vList) {
                        K key = (K) kField.get(v);
                        if (map.containsKey(key)) {
                            list = map.get(key);
                        } else {
                            list = new ArrayList<V>();
                        }
                        list.add(v);
                        map.put(key, list);

                    }
                } else {
                    System.err.println("根据kName，获取不到Field(对象的属性) kName=" + kName);
                }
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 拼接泛型集合中对象的某属性 返回格式 'xxx','xxx','xxx'
     *
     * @param list 泛型集合
     * @param key  集合中对象的属性名称
     * @return java.lang.String
     * @author pengjia
     * @date 2021/5/9 16:57
     */
    public static <T> String getKeyByList(List<T> list, String key) {
        return getKeyByList(list, key, ",", "'");
    }

    /**
     * @param tList
     * @param kName
     * @param separator
     * @return String
     * @Title:toSplitStrByListObjKey
     * @author:彭嘉
     * @date:2019年9月30日 下午13:08
     * @Description
     */

    /**
     * 拼接泛型集合中对象的某属性
     *
     * @param tList     需要转换成map的list
     * @param kName     list中对象的属性名称
     * @param separator 拼接的分隔符
     * @return java.lang.String
     * @author pengjia
     * @date 2021/5/9 16:58
     */
    public static <T> String getKeyByList(List<T> tList, String kName, String separator) {
        return getKeyByList(tList, kName, separator, null);
    }


    /**
     * 将泛型集合list中某个属性的值拼装成string <br>
     * 例如，想将List<User> 中user对象的所有id已 ',' 拼装起来 ，参数kName 为"id",separated 为"," <br>
     * 最终格式为 xxx,xxx,xxx <br>
     * 如果需要的格式为: 'xxx','xxx','xxx' 参数includeStr的值为 ' <br>
     * 注意:kName的值必须是对象已有的属性，不能写错，根据kName获取对象的值必须能转换成字符串
     *
     * @param tList      需要转换成map的list
     * @param kName      list中对象的属性名称
     * @param separator  拼接的分隔符
     * @param includeStr 元素被包裹的符号
     * @return java.lang.String
     * @author pengjia
     * @date 2021/5/9 17:00
     */
    public static <T> String getKeyByList(List<T> tList, String kName, String separator, String includeStr) {
        StringBuffer sb = new StringBuffer();
        try {
            if (QwyUtil.isNullAndEmpty(tList)) {
                System.err.println("vList is null,要拼接的集合不能为空");
                return "";
            }

            if (QwyUtil.isNullAndEmpty(kName)) {
                System.err.println("kName is null,集合中元素的属性名称不能为空");
                return "";
            }

            if (QwyUtil.isNullAndEmpty(separator)) {
                System.err.println("separator is null,要拼接的分隔符不能为空");
                return "";
            }

            // 获取kName的Field
            Field kField = ReflectUtil.getField(tList.get(0).getClass(), kName);
            if (QwyUtil.isNullAndEmpty(kField)) {
                System.err.println("根据kName，获取不到Field(对象的属性)");
                return "";
            }

            if (QwyUtil.isNullAndEmpty(includeStr)) {
                // 循环lsit,获取对象指定kName的属性的值，作为key
                for (T t : tList) {
                    if (kField.get(t) != null) {
                        sb.append((String) kField.get(t) + separator);
                    }
                }
            } else {
                for (T t : tList) {
                    if (kField.get(t) != null) {
                        sb.append(includeStr + (String) kField.get(t) + includeStr + separator);
                    }
                }
            }

            if (sb.length() > 0) {
                // 删除最后一个字符（分隔符）并返回
                return sb.deleteCharAt(sb.length() - 1).toString();
            }
        } catch (Exception e) {
            System.err.println("将list中某个属性的值拼装成string err");

        }
        return "";
    }

}



