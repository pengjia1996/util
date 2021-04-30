package com.xinyu.number;

import com.xinyu.json.GsonUtil;
import com.xinyu.regex.RegexMatchUtil;
import org.checkerframework.checker.regex.RegexUtil;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author pengjia
 * @version 1.0
 * @date 2021/4/30 16:21
 */
public class NumberUtil {

    /**
     * 用于将阿拉伯数字 转换成 中文的数组
     */
    private static final String[] UNITS = {"", "十", "百", "千", "万", "十", "百", "千", "亿"};
    private static final char[] NUMS = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九'};


    /**
     * @param obj
     * @return java.lang.String
     * @author pengjia
     * @date 2021/4/30 17:04
     */
    public static String format(Object obj) {
        /*NumberFormat numberFormat = NumberFormat.getInstance();
        DecimalFormat format = (DecimalFormat) numberFormat;
        format.applyPattern("#,####.##########;");
         format.format(obj);*/

        String numStr=obj.toString();
        if (RegexMatchUtil.isNumberByLargeRange(numStr)){

            return null;
        }else{
            throw new IllegalArgumentException("参数非法,请传入数字,obj="+obj);
        }

    }


    public static String subZeroAndDot(String str){
        if(str.indexOf(".") > 0){
            str = str.replaceAll("0+?$", "");//去掉多余的0
            str = str.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return str;
    }



}
