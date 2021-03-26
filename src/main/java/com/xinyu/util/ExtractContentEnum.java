package com.xinyu.util;

/**   
 * @Title:ExtractContentEnum
 * @author:彭嘉
 * @date:2020年11月17日 下午6:58:09  
 * @Description:提取字符串中内容用到的枚举  
 */
public enum ExtractContentEnum {

    /**
     * 中文
     */
    CHINESE("[^\\u4e00-\\u9fa5]", "中文"),

    /**
     * 数字
     */
    NUMBER("[^\\d]", "数字");

    private String regex;// regular expression 正则表达式

    private String name;// 要提取的内容类型名称

    ExtractContentEnum(String regex, String name) {
        this.regex = regex;
        this.name = name;
    }

    public String getRegex() {
        return regex;
    }

    public String getName() {
        return name;
    }


}



