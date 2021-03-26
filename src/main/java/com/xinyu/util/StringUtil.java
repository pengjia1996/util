package com.xinyu.util;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**   
 * @Title:StringUtil
 * @author:彭嘉
 * @date:2020年11月17日 下午2:22:34  
 * @Description:字符串公共类   
 */
public class StringUtil {
    private static Logger logger = Logger.getLogger(StringUtil.class);

    /**
     * 
     * @Title:isNull
     * @author:彭嘉
     * @date:2020年11月17日 下午2:27:29  
     * @Description: 空判断   
     * @param str 要判断的字符
     * @return boolean true为空,false不为空
     */
    public static boolean isNull(String str) {
        if ((str != null) && !"".equals(str) && !str.equals("null")) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 
     * @Title:isNull
     * @author:彭嘉
     * @date:2020年11月17日 下午3:16:41  
     * @Description: 大范围空判断    
     * @param str 要判断的字符
     * @return boolean true为空,false不为空
     */
    public static boolean isNull2(String str) {
        boolean isnull = (str != null) && !"".equals(str.trim()) && !str.equals("null") && !"[]".equals(str.trim()) && !"''".equals(str.trim())
            && !"\"\"".equals(str.trim()) && !"{}".equals(str.trim());
        logger.info("isnull:" + isnull);
        if (isnull) {
            return false;
        } else {
            return true;
        }

    }
    
    /**
     * 
     * @Title:getSexByIdCard
     * @author:彭嘉
     * @date:2020年6月16日 下午4:35:11  
     * @Description: 根据身份证号码判断是否是男的,1是男的,2是女的,为空时没有判断出来   
     * @param idCardNum 二代身份证号码,18位
     * @return String
     */
    public static String getSexByIdCard(String idCardNum) {
        // 注释：编码规则顺序从左至右依次为6位数字地址码，8位数字出生年份日期码，3位数字顺序码，1位数字校验码（可为x）。
        // [1-9]\d{5} 前六位地区，非0打头
        // (18|19|([23]\d))\d{2} 出身年份，覆盖范围为 1800-3999 年
        // ((0[1-9])|(10|11|12)) 月份，01-12月
        // (([0-2][1-9])|10|20|30|31) 日期，01-31天 闰年不能禁止29+
        // \d{3}[0-9Xx]： 顺序码三位 + 一位校验码

        String sex = "";
        try {
            logger.info("身份证号码的长度" + idCardNum.length());
            if (idCardNum.length() != 18) {
                logger.info("身份证号码不是18位");
                return "";
            }
            // String regex =
            // "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$/";

            /*boolean isMatch = Pattern.matches(regex, idCardNum);
            logger.info("身份证号码是否通过验证" + isMatch);
            if (!isMatch) {
                return "身份证号码不是有效的";
            }*/
            String sexNumStr = idCardNum.substring(16, 17); // 取指定位置的值(16位之后,17位结束;)
            int num = Integer.parseInt(sexNumStr);// 强制类型转换
            if (num % 2 == 0) {
                // 偶数女
                sex = "2";
            } else {
                // 奇数男
                sex = "1";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getSexByIdCard error", e);
            return "";
        }

        return sex;
    }

    /**
     * 
     * @Title:replaceNumber
     * @author:彭嘉
     * @date:2020年6月15日 下午2:49:46  
     * @Description: 将纯数字字符串按照指定方式替换成*     
     * @param numberStr 数字字符串
     * @param startNum 前面要显示的位数
     * @param endNUm  后面要显示的位数
     * @param num * 显示的位数,为null时,显示位数为 bankCard.length-(startNum+endNUm)
     * @return String 操作后的字符
     */
    public static String replaceNumber(String numberStr, int startNum, int endNUm, Integer num) throws Exception {
        String resultStr = "";
        try {
            if (numberStr == null || numberStr.isEmpty()) {
                return null;
            } else {
                if (num == null) {
                    String regex = "(?<=\\d{" + startNum + "})\\d(?=\\d{" + endNUm + "})";
                    resultStr = numberStr.replaceAll(regex, "*");
                } else {
                    String regex = "(?<=\\d{" + startNum + "})\\d+(?=\\d{" + endNUm + "})";
                    StringBuffer charStr = new StringBuffer();
                    for (int i = 0; i < num; i++) {
                        charStr.append("*");
                    }
                    resultStr = numberStr.replaceAll(regex, charStr.toString());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("replaceNumber error", e);
        }
        return resultStr;
    }

    /**
     * @Title:extractContent
     * @author:彭嘉
     * @date:2020年11月17日 下午6:43:34  
     * @Description: 提取字符串中的指定内容 
     * @param str 待操作的字符串
     * @param extractContentEnum 要提取的数据类型 如中文,数字,等
     * @return 从字符串中提取的内容,null 提取失败
     * @throws Exception
     */
    public static String extractContent(String str, ExtractContentEnum extractContentEnum) {
        String result = null;

        try {
            if (QwyUtil.isNullAndEmpty(str)) {
                logger.info("待操作的字符串为空");
                return result;
            }
            if (QwyUtil.isNullAndEmpty(extractContentEnum)) {
                logger.info("要提取字符类型为空 extractContentEnum is null");
                return result;
            }

            logger.info("开始提取字符串中的" + extractContentEnum.getName());
            Pattern p = Pattern.compile(extractContentEnum.getRegex());
            result = p.matcher(str).replaceAll("");
            logger.info("提取结果" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("提取字符串中的" + extractContentEnum.getName() + "失败", e);
        }
        return result;
    }
    
    
    


    


}



