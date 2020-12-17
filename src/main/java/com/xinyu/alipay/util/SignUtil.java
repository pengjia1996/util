package com.xinyu.alipay.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**   
 * @Title:SignUtil
 * @author:彭嘉
 * @date:2020年11月5日 下午2:20:51  
 * @Description:签名相关公共类   
 */
public class SignUtil {
    private static Logger logger = Logger.getLogger(SignUtil.class);

    /**
     * 带上用于验证的加密sign
     * @param values 原始请求参数
     */
    public static void putSign(Map<String, Object> values) {
        logger.info("过滤前参数1：" + values);
        Map<String, String> map = new HashMap<String, String>();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                map.put(key, null);
            } else {
                map.put(key, value.toString());
            }
        }
        // 过滤空值、sign与sign_type参数
        Map<String, String> sParaNew = AlipayCore.paraFilter(map);
        logger.info("过滤后参数1：" + sParaNew);
        logger.info("private_key1：" + AlipayConfig.private_key);
        logger.info("input_charset1：" + AlipayConfig.input_charset);

        // 获取待签名字符串
        String preSignStr = AlipayCore.createLinkString(sParaNew);
        logger.info("preSignStr：" + preSignStr);
        String sign = RSA.sign(preSignStr, AlipayConfig.private_key, AlipayConfig.input_charset);
        logger.info("加密签名1：" + sign);
        values.put("sign", sign);
    }
}



