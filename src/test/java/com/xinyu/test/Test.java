package com.xinyu.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xinyu.file.util.PropertiesUtil;
import com.xinyu.json.util.DateAdapter;
import com.xinyu.json.util.GsonUtil;
import com.xinyu.json.util.JSONObjectUtil;
import com.xinyu.net.util.NetWorkUtil;


/**
 * 
 * @Title:Test
 * @author:彭嘉
 * @date:2020年10月26日 下午6:02:02  
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws Exception {
        BigDecimal a = new BigDecimal("0");
        BigDecimal multiply = a.multiply(BigDecimal.valueOf(100));

        Long long1 = multiply == null ? null : multiply.longValue();
        System.err.println(long1);
    }




}



