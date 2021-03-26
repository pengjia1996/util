package com.xinyu.random;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**   
 * @Title:RandomUtil
 * @author:彭嘉
 * @date:2020年12月21日 上午10:29:13  
 * @Description:随机公共类  
 */
public class RandomUtil {
    // SecureRandom is preferred to Random
    private static Random random = null;
    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title:verifyCode
     * @author:彭嘉(移动注释补充)
     * @date:2020年7月6日 下午5:24:05  
     * @Description: 随机生成6位字母数字  
     * @return String 6位字母数字
     */
    public static String verifyCode() {
        // Random random = new Random();
        // 32～126(共95个)是字符(32是空格），其中48～57为0到9十个阿拉伯数字。
        // 65～90为26个大写英文字母，97～122号为26个小写英文字母，其余为一些标点符号、运算符号等。
        // set 保存的是 48-123之间的非字符数字的ASCII
        char[] set = {91, 92, 93, 94, 95, 96, 58, 59, 60, 61, 62, 63, 64};
        StringBuffer str = new StringBuffer();
        while (str.length() != 6) {
            boolean flag = true;
            // 生成一个随机的int值，该值介于[48,123)
            int a = (random.nextInt(75) + 48);
            for (int j = 0; j < set.length; j++) {
                if (a == set[j]) {
                    flag = false;
                }
            }
            if (flag) {// 是字符或数字开始拼接
                char ch = (char)a;
                str.append(ch);
            }
        }
        return str.toString().toLowerCase();
    }

    /**
     * 
     * @Title verifyCodeNumber
     * @author 彭嘉
     * @date 创建时间: 2020年07月09日 10:05:05
     * @Description  随机生成6位数字  
     * @param 随机生成几位数
     * @return String 纯数字数字
     */
    public static String verifyCodeNumber(int number) {
        // Random rand = new Random();
        StringBuffer str = new StringBuffer();
        // 拼接随机数字
        while (str.length() != number) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

}



