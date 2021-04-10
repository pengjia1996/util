package com.xinyu.money;

import org.apache.log4j.Logger;

import com.xinyu.calculate.Calculator;
import com.xinyu.regex.RegexMatchUtil;

/**
 * 
 * 金钱操作公共类
 * @author:彭嘉
 */
public class MoneyUtil {

    private static final Logger logger = Logger.getLogger(MoneyUtil.class);

    /**
    * 
    * 将元转成分     
    * @param money 要转换的金额(单位元)
    * @return Long 转换好的金额(单位分)
    */
    public static Long yuanConvertFen(String money) {
        logger.info("yuanConvertFen(String money)");
        return RegexMatchUtil.isNumberByLargeRange(money) ? null : Calculator.calcNumber(money, 100, "*").longValue();
    }

}



