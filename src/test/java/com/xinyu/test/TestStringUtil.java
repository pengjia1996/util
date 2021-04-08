package com.xinyu.test;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.xinyu.file.PropertiesUtil;
import com.xinyu.str.ExtractContentEnum;
import com.xinyu.str.StrUtil;

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
        TestStringUtil test = new TestStringUtil();
        test.testExtractContent();
    }

    /**
     * 测试从字符串中提取指定内容
     */
    @Test
    public void test() {

        // 提取中文
        String str = "agh325ior发vdh货后感染2437风光大嫁fgjn化骨粉9063554";
        String result1 = StrUtil.extractContent(str, null);
        String result2 = StrUtil.extractContent(str, ExtractContentEnum.NUMBER);
        System.err.println(result1);

    }

    /**
     * 测试从字符串中提取指定内容
     */
    @Test
    public void testExtractContent() {

        // 提取中文
        String str = "agh325ior发vdh货后感染2437风光大嫁fgjn化骨粉9063554";
        String result1 = StrUtil.extractContent(str, ExtractContentEnum.CHINESE);
        String result2 = StrUtil.extractContent(str, ExtractContentEnum.NUMBER);
        System.err.println(result1);
        System.err.println(result2);

    }

}



