package com.xinyu.net.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.xinyu.alipay.util.SignUtil;
import com.xinyu.json.util.JSONObjectUtil;
import com.xinyu.util.QwyUtil;



/**   
 * @Title:NetWorkUtil
 * @author:彭嘉
 * @date:2020年10月28日 下午4:13:50  
 * @Description:网络通信相关操作公共类   
 */
public class NetWorkUtil {
    private static Logger logger = Logger.getLogger(NetWorkUtil.class);


    /**
     * 
     * @Title:httpPostFile
     * @author:彭嘉
     * @date:2020年10月27日 下午4:25:04  
     * @Description: http上传文件     
     * @param url 链接
     * @param params 参数
     * @return Map<String,String> code 状态码,msg 描述信息, data 响应数据
     */
    public static Map<String, String> httpPostFile(String url, Map<String, Object> params) {
        logger.info("into httpPostFile method");
        Map<String, String> resultMap = new HashMap<String, String>();
        try {
            MultipartEntity reqEntity = new MultipartEntity();
            HttpClient client = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                Object object = entry.getValue();
                String key = entry.getKey();
                if (object instanceof File) {
                    reqEntity.addPart(key, new FileBody((File)object));
                } else if (object instanceof String) {
                    reqEntity.addPart(key, new StringBody((String)object));
                } /*else if (object instanceof byte[]) {
                    reqEntity.addPart(key, new ByteArrayBody((byte[])object,""));
                  }*/
                /*else if() {
                   new  InputStreamBody(final InputStream in, final String mimeType, final String filename)
                }*/
            }
            // 设置链接建立前的超时时间(20s),以毫秒为单位
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
            // 设置等待响应数据超时时间(20s),以毫秒为单位
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
            httppost.setEntity(reqEntity);
            // 执行http请求
            HttpResponse response = client.execute(httppost);
            // 网络请求成功
            int statusCode = response.getStatusLine().getStatusCode();

            logger.info("url=" + url + ",statusCode=" + statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                String str = EntityUtils.toString(response.getEntity(), "UTF-8");
                resultMap.put("code", "success");
                resultMap.put("msg", "网络请求成功,url=" + url);
                resultMap.put("data", str);
                return resultMap;
            } else {// 网络请求失败
                resultMap.put("code", "fail");
                resultMap.put("msg", "网络请求失败,url=" + url);
                return resultMap;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("httpPostFile error", e);
            resultMap.put("code", "fail");
            resultMap.put("msg", "httpPostFile error,message=" + e.getMessage());
            return resultMap;
        }

    }

    /**
     * 用Post的方法访问网络;
     * @param url 访问地址 POST访问
     * @param parametersMap 参数 如果需要传递识别码,则以这种格式传递:
     * <br>  map.put("authorization", "69dfe6ff-afdb-4678-984e-b23b22f5c88c");
     * @return Map<String, Object>
     */
    public static Map<String, Object> accessIntentByPost(String url, Map<String, Object> parametersMap) {
        parametersMap.put("authorization", "");
        parametersMap.put("ZSXYAuthorizationValue", "");
        return accessIntent(url, parametersMap, "POST");
    }

    /**访问网络
     * @param url 访问地址 get访问,直接在地址后面带参数;
     * @param values 参数 当POST访问时,才需要用到此参数;如果需要传递识别码,则以这种格式传递:
     * <br> &nbsp;&nbsp;&nbsp;&nbsp; map.put("authorization", "69dfe6ff-afdb-4678-984e-b23b22f5c88c");
     * @param method 访问方法;POST,GET
     * @return  Map&lt;String,Object&gt;
     */
    public static Map<String, Object> accessIntent(String url, Map<String, Object> values, String method) {

        Map<String, Object> valuesEnd = new HashMap<String, Object>();
        valuesEnd.put("end", "error");
        valuesEnd.put("message", "网络异常!");
        HttpGet get = null;// get访问
        HttpPost post = null;// post访问
        HttpResponse response = null;// 响应
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            if (url.indexOf("HTTPS:") > -1) {
                client = (DefaultHttpClient)WebClientDevWrapper.wrapClient(client);
            }
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
            if (method.equalsIgnoreCase("GET")) {
                // get访问
                get = new HttpGet(url);
                if (!QwyUtil.isNullAndEmpty(values) && !QwyUtil.isNullAndEmpty(values.get("authorization"))) {
                    get.setHeader("Authorization", values.get("authorization").toString());
                }
                if (!QwyUtil.isNullAndEmpty(values) && !QwyUtil.isNullAndEmpty(values.get("ZSXYAuthorizationValue"))) {
                    get.setHeader("ZSXYAuthorizationValue", values.get("ZSXYAuthorizationValue").toString());
                }

                response = client.execute(get);

            } else {
                // Post访问;
                post = new HttpPost(url);
                if (!QwyUtil.isNullAndEmpty(values) && !QwyUtil.isNullAndEmpty(values.get("authorization"))) {
                    post.setHeader("Authorization", values.get("authorization").toString());
                }
                if (!QwyUtil.isNullAndEmpty(values) && !QwyUtil.isNullAndEmpty(values.get("ZSXYAuthorizationValue"))) {
                    post.setHeader("ZSXYAuthorizationValue", values.get("ZSXYAuthorizationValue").toString());
                }
                SignUtil.putSign(values);

                if (!QwyUtil.isNullAndEmpty(values)) {
                    List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
                    Iterator<Map.Entry<String, Object>> entrys = values.entrySet().iterator();
                    while (entrys.hasNext()) {
                        Entry<String, Object> entry = entrys.next();
                        params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                    }
                    HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
                    post.setEntity(entity);
                }
                logger.info("accessIntent 开始请求链接");
                response = client.execute(post);
            }
            logger.info("accessIntent 响应代码:" + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str = EntityUtils.toString(response.getEntity(), "UTF-8");
                try {
                    JSONObject jo = new JSONObject(str);
                    logger.info("地址: " + url);
                    return JSONObjectUtil.jsonParseToMap(jo);
                } catch (JSONException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage(), e);
                }
            }
            logger.info(response.getStatusLine().toString());
            return valuesEnd;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return valuesEnd;
    }
}



