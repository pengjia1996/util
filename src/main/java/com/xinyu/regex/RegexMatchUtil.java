package com.xinyu.regex;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.xinyu.util.QwyUtil;

/**
 * @Title:RegexMatchUtil
 * @author:彭嘉
 * @date:2020年7月7日 下午5:04:32
 * @Description:正则匹配公共类
 */
public class RegexMatchUtil {

    private static final Logger logger = Logger.getLogger(RegexMatchUtil.class);

    /**
     * @Title:isNumber
     * @author:彭嘉
     * @date:2020年7月7日 下午5:20:01
     * @Description: 判断字符串是否是指定位数的数字(整数)
     * @param num 位数
     * @param str 要判断的字符
     * @return boolean true 符合,false不符合
     */
    public static boolean isNumber(int num, String str) {

        try {
            String regex = "^\\d{" + num + "}$";
            return !QwyUtil.isNullAndEmpty(str) && Pattern.matches(regex, str);
        } catch (Exception e) {
            logger.error("判断字符串是否是指定位数的正整数出错", e);
            return false;
        }

    }

    /**
     * @Title:isSixNumber
     * @author:彭嘉
     * @date:2020年7月7日 下午5:20:01
     * @Description: 判断字符串是否是6位数字
     * @param str 要判断的字符
     * @return boolean true 符合,false不符合
     */
    public static boolean isSixNumber(String str) {
        try {
            String regex = "^\\d{6}$";
            return !QwyUtil.isNullAndEmpty(str) && Pattern.matches(regex, str);
        } catch (Exception e) {
            logger.error("判断字符串是否是6位数字出错", e);
            return false;
        }

    }

    /**
     *
     * @Title:isNumber
     * @author:彭嘉
     * @date:2021年3月17日 下午3:14:11
     * @Description 判断字符串是否是数字(校验是否是正整数)
     * @param str 需要校验的字符
     * @return boolean true 符合,false不符合
     */
    public static boolean isNumber(String str) {
        try {
            String regex = "^\\d+$";
            return !QwyUtil.isNullAndEmpty(str) && Pattern.matches(regex, str);
        } catch (Exception e) {
            logger.error("判断字符串是否是正整数出错", e);
            return false;
        }

    }

    /**
     *
     * @author 彭嘉
     * @date 2021年4月8日 上午11:55:19
     * @description 判断字符串是否是负整数,负小数,  正整数或正小数
     * @param str 需要判断的字符串
     * @return boolean
     */
    public static boolean isNumberByLargeRange(String str) {
        try {
            // -[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)? \d 等同于 [0-9]
            // -表示负号,[0-9] 表示0-9任意数字,-[0-9]表示-0到-9, +表示表达式匹配一次或多次。
            // [0-9]+ 表示[0-9] 出现一次或多次,-[0-9]+ 表示-0到-∞
            // ?表示前面的子表达式匹配零次或一次,(.[0-9]+)?表示 出现一次或多次
            // .[0-9]+ 表示小数点出现一次,数字一次或多次
            // -[0-9]+(.[0-9]+)? 表示负整数或负小数
            // | 表示或者, [0-9]+(.[0-9]+)? 表示正整数或正小数
            // (\\d+(.\\d+)?)正整数或正小数, -? 表示负号出现0次或一次
            // "^-?(\\d+(.\\d+)?)$" 表示负整数,负小数, 正整数或正小数

            String regex = "^-?(\\d+(.\\d+)?)$";
            return !QwyUtil.isNullAndEmpty(str) && Pattern.matches(regex, str);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("判断字符串是否是整数,负数,小数出错", e);
            return false;
        }

    }

    /**
     * @Title:isIdCardNumber
     * @author:彭嘉
     * @date:2020年7月7日 下午5:34:12
     * @Description: 校验是否是身份证号码(18位)
     * @param str 要校验的字符
     * @return boolean true 符合,false不符合
     */
    public static boolean isIdCardNumber(String str) {
        try {
            // 当长度为18时进行正则判断
            // 注释：编码规则顺序从左至右依次为6位数字地址码，8位数字出生年份日期码，3位数字顺序码，1位数字校验码（可为x）。
            // [1-9]\d{9} 共10位 前六位地区，非0打头 后4位年份不限制(18|19|([23]\d))\\d{2} 1800-3999
            // ((0[1-9])|(10|11|12)) 月份，01-12月
            // (([0-2][1-9])|10|20|30|31) 日期，01-31天
            // \d{3}[0-9Xx]： 顺序码三位 + 一位校验码 X是罗马数字表示十
            String regex = "^[1-9]\\d{9}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
            return !QwyUtil.isNullAndEmpty(str) && Pattern.matches(regex, str);
        } catch (Exception e) {
            logger.error("判断字符串是否是身份证号码出错", e);
            return false;
        }

    }

    /**
     *
     * @Title:isIpAddress
     * @author:彭嘉
     * @date:2020年7月8日 上午10:40:28
     * @Description: 校验字符串是否是ip地址
     * @param str
     * @return boolean
     */
    public static boolean isIpAddress(String str) {

        try {
            // IP地址的长度为32位(共有2^32个IP地址)，分为4段，每段8位，
            // 用十进制数字表示，每段数字范围为0～255，段与段之间用句点隔开。
            // 判断字符串是否是ip地址
            // 2(5[0-5]|[0-4]\d) 匹配：200 ~ 255
            // [0-1]?\d{1,2} 匹配：0 ~ 199
            // 后边“点”和“数字”重复三次就可以了 (\.((2(5[0-5]|[0-4]\d))|[0-1]?\d{1,2})){3}
            // 0.0.0.0 ～ 255.255.255.255
            String regex = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$";
            return !QwyUtil.isNullAndEmpty(str) && Pattern.matches(regex, str);
        } catch (Exception e) {
            logger.error("判断字符串是否是ip地址出错", e);
            return false;
        }

    }

}
