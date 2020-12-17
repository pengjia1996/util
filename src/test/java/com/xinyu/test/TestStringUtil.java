package com.xinyu.test;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.xinyu.file.util.PropertiesUtil;
import com.xinyu.util.ExtractContentEnum;
import com.xinyu.util.StringUtil;

/**   
 * @Title:TestStringUtil
 * @author:彭嘉
 * @date:2020年11月17日 下午7:36:19  
 * @Description:测试   StringUtil类
 */
public class TestStringUtil {
    static {
        PropertyConfigurator.configure(PropertiesUtil.getLog4jPropertiesUrl());
    }

    public static void main(String[] args) {
        testExtractContent();
    }

    /**
     * 测试从字符串中提取指定内容
     */
    @Test
    public static void testExtractContent() {

        // 提取中文
        String str = "agh325ior发vdh货后感染2437风光大嫁fgjn化骨粉9063554";
        String result1 = StringUtil.extractContent(str, ExtractContentEnum.CHINESE);
        String result2 = StringUtil.extractContent(str, ExtractContentEnum.NUMBER);
        System.err.println(result1);
        System.err.println(result2);

    }

}



