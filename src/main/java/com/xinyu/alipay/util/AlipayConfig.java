package com.xinyu.alipay.util;

/*
 * * 类名：AlipayConfig 功能：基础配置类 详细：设置帐户有关信息及返回路径 版本：3.3 日期：2012-08-10 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。 提示：如何获取安全校验码和合作身份者ID
 * 1.用您的签约支付宝账号登录支付宝网站(www.alipay.com) 2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 * 3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)” 安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？ 解决方法： 1、检查浏览器配置，不让浏览器做弹框屏蔽设置 2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "2088121414196225";
    // 商户的私钥
    /*public static String private_key ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK+9vb1F3jgcqrK5"
            +"Qa6jnUkESLDzWcy/Q661sVteBTfns+uZqZhErtIkYEqXleqSJ/TvEIXsqyRD8re2"
            +"U3DoeOzeGLxDbVExZ0kTCQUndSd4PvyMhe+5HCOBlrAbNLuda+DM+Yyksk44T2jg"
            +"kJ41jBCVCapgb1jUlB1JUVuckwmRAgMBAAECgYBtYMV3yMVu3QCsrbqgi/p3mG/5"
            +"mev5D817LmBNQm4zBkWnzAKO36iC59r6ce//h6nQuv3orI0zzAqDprm2GyovEdcf"
            +"hNXxQFHGAu53Msc9tPPtEJOTFTYGdASTiuZOqC8S7AAHyEn/AkmBewjoav3HcDdI"
            +"jRLuMVhSkLfQQyoyMQJBAOIW4rsq5UY0hS+HX6DExN0NGppH1/Ubu5hPEbDdnOL3"
            +"GSTqp91Jp7YR22V2Q7oHcCXbZVjjxOTvXMoAz0+RleUCQQDG/a4Z6Iu5knzc3pcN"
            +"OqwGJmDSo0reKP1sM35o+RlaU0VGMZPLi9OgaCtmNpsnMjE+wVw0iuX7sM2FKv9m"
            +"c+o9AkA5ZO/n7crIWLraxvXqvSjwqMvxEH3tNj4YOzJVgNy77ViPu5YjofgWS1Az"
            +"4KLuzcaVha2jtCaeq3kVusDmBSJVAkEAt4rLqIgPQQa19v+08fXZL/78Na16Y/oI"
            +"7mF8ypRg5yiBcCJQgWGivIT46tmZOIkaVRkC1E/9m67Tkm47jyOBvQJAOGXKd20N"
            +"+y81Ydm389XQ3yROM1dM/YPlw+zyan+KUeK/lUvPvwe7gHk8J0Syd6kW+8+AP8I7"
            +"xfN8RR3BIWYDXQ==";*/
    public static String private_key =
        "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJNfpAkIm84p253GrHHFc5n9AD1B3BGIdFyrMriP9QseWo+DtWVcwBPBCszq6iGOI7aMjCTM7lOfvIXdha5OGeSK7uXswqNPqjQMCyFQOk7EEEKwoMp8wK8eaJzep5V3K5kKJl/bi4BT9z58vtVviDzcw2HlLm4XKGJa8FPZo11fAgMBAAECgYBWYlZVkT06sGBx8DW+jp2/YoSUY5lqkZCbjSIy+vcI6Gbu/qYCX0abjakWCpDo0FdwtpujKph8XgJ0eS/VxyP3YEpPrz148Z2oDbdIi32UpmmBGrurtsJWa6XJEdwI01jzJSA+ABm/00ZOiDihgJFGF474n1D2TRLFdy675ZTkCQJBAPNzDKrLBY82Z2/xtooYq9Ob3Sq+c10aHndA+K9t5S2dPi1T0YobqOnBAgZHEiSGxbzUcSM2diSOewcIpPpK+3sCQQCa+J6+WCMlTxbRe5w+KDFnVy2JXvZFnY+KRzDfM9wTCD55IdZjPrN+1GAtun3yB1T8YVI82Z3E8fjoaY75475tAkEAstnb/YHHpM3wMfKfC1dSrNR+KE/giw6MQUpPKPqQevFhY4WHlHH7pzdvmFMQRhtpG+/q6laxduviAGneYi9CDwJBAIdqZrRnKdbpJERK4j2CO6FUkUrvev1b79jh2ne/xjevRX/trHuuh4R1KdHrmzBJbd7XX9giRHWbB2h3DOOZOYUCQCaW+RXIFxaFPlboKpbxbYLmtHZlYDqJUHap49wEMzly2/Wq2JUZAidT7SltwEtu06Sr+3EdKjaTj0BAzxm+Vy0=";
    // 支付宝的公钥，无需修改该值
    // public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6B"
    // + "kRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA"
    // + "23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    public static String ali_public_key =
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTX6QJCJvOKdudxqxxxXOZ/QA9QdwRiHRcqzK4j/ULHlqPg7VlXMATwQrM6uohjiO2jIwkzO5Tn7yF3YWuThnkiu7l7MKjT6o0DAshUDpOxBBCsKDKfMCvHmic3qeVdyuZCiZf24uAU/c+fL7Vb4g83MNh5S5uFyhiWvBT2aNdXwIDAQAB";

    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    // 调试用，创建TXT日志文件夹路径
    public static String log_path = "D:\\";

    // 字符编码格式 目前支持 gbk 或 utf-8
    // public static String input_charset = "gbk";
    public static String input_charset = "UTF-8";
    // 签名方式 不需修改
    public static String sign_type = "RSA";

}
