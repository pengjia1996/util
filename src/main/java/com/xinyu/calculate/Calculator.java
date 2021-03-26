package com.xinyu.calculate;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.xinyu.util.QwyUtil;

/**   
 * @Title:Calculator
 * @author:彭嘉
 * @date:2020年11月2日 下午5:10:46  
 * @Description:计算器   
 */
public class Calculator {
    private static Logger logger = Logger.getLogger(Calculator.class);

    /**
     * 对两个数进行四则运算; 自行选择保留位数;不四舍五入,直接截取;
     * result = num1+num2;<br>
     * result = num1-num2;<br>
     * result = num1*num2;<br>
     * result = num1/num2;<br>
     * @param num1 式子中的第一个数;
     * @param num2 式子中的第二个数;
     * @param calcSymbol 运算符号 "+" "-" "*" "/" 
     * @param remainNum 保留多少位小数; 如果小于0,则为0;
     * @return BigDecimal
     * @throws Exception 计算异常;
     */
    public static BigDecimal calcNumber(Object num1, Object num2, String calcSymbol, int remainNumPra) {
        try {
            int remainNum = remainNumPra < 0 ? 0 : remainNumPra;
            if (!QwyUtil.isNullAndEmpty(num1) && !QwyUtil.isNullAndEmpty(num2)) {
                BigDecimal decimal = new BigDecimal(num1.toString());
                BigDecimal decima2 = new BigDecimal(num2.toString());
                if ("+".equals(calcSymbol)) {
                    return decimal.add(decima2);
                } else if ("-".equals(calcSymbol)) {
                    return decimal.subtract(decima2);
                } else if ("*".equals(calcSymbol)) {
                    return decimal.multiply(decima2);
                } else if ("/".equals(calcSymbol)) {
                    if (!"0".equals(decima2.toString())) {
                        return decimal.divide(decima2, remainNum, BigDecimal.ROUND_DOWN);
                    } else {
                        System.err.println("除数不能为0");
                        throw new Exception("除数不能为0");
                    }
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("calcNumber(num1,num2,calcSymbol,remainNumPra) error", e);
        }

        return null;
    }

    /**
     * 对两个数进行四则运算; 保留小数位数4(除法);不四舍五入,直接截取;
     * result = num1+num2;<br>
     * result = num1-num2;<br>
     * result = num1*num2;<br>
     * result = num1/num2;<br>
     * @param num1 式子中的第一个数;
     * @param num2 式子中的第二个数;
     * @param calcSymbol 运算符号 "+" "-" "*" "/" 
     * @return BigDecimal
     * @throws Exception 计算异常;
     */
    public static BigDecimal calcNumber(Object num1, Object num2, String calcSymbol) {

        return calcNumber(num1, num2, calcSymbol, 4);
    }


}
