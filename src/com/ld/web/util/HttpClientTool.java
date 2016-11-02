package com.ld.web.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 
 * <p> Title: HttpClientTool </p>
 * <p> Copyright: Copyright (c) 2015 </p>
 * <p> Description: HttpClient工具类 </p>
 * 
 * @author LD
 * 
 * @date 2015-08-06
 */
public class HttpClientTool {

    private static Logger logger = Logger.getLogger(HttpClientTool.class);

    private static final HttpClientTool INSTANCE = new HttpClientTool();

    private final int CONNECT_TIME_OUT = 10000; // 网络与服务器建立连接的超时时间
    private final int SOCKET_TIME_OUT = 15000; // Socket读数据的超时时间，即从服务器获取响应数据需要等待的时间
    private final int CONNECTION_REQUEST_TIMEOUT = 5000; // 从连接池中取连接

    /**
     * Merge mainUrl and suffixUrl
     * 
     * @param mainUrl
     * @param suffixUrl
     * @return
     */
    public String mergeUrl(String mainUrl, String suffixUrl) {
        mainUrl = mainUrl.endsWith("/") ? mainUrl : mainUrl + "/";
        suffixUrl = suffixUrl.startsWith("/") ? suffixUrl.substring(1) : suffixUrl;
        return mainUrl + suffixUrl;
    }

    /**
     * Http Post
     * 
     * @param url
     * @param header
     * @param params
     * @throws Exception
     * @return
     */
    public String post(String url, RequestConfig config, Map<String, String> header, Map<String, String> params) throws Exception {
        logger.info(String.format("Httpclient send data: %s", JsonMapper.getInstance().toJson(params)));

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(url);

            if (null != config) {
                httppost.setConfig(config);
            }

            if (null != header && !header.isEmpty()) {
                for (Entry<String, String> entry : header.entrySet()) {
                    httppost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            if (null != params && !params.isEmpty()) {
                httppost.setEntity(new UrlEncodedFormEntity(getNameValuePair(params), "utf-8"));
            }

            HttpResponse response = httpclient.execute(httppost);
            if (null != response && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String resp = null != entity ? EntityUtils.toString(entity) : null;
                logger.info(String.format("Httpclient response data: %s", resp));
                return resp;
            }
            return null;
        } catch (Exception e) {
            logger.error(String.format("Httpclient post exception: %s", e.getMessage()), e);
            throw new Exception(e);
        } finally {
            closeClient(httpclient);
        }
    }

    /**
     * Http Post
     * 
     * @param url
     * @param json
     * @throws Exception
     * @return
     */
    public String post(String url, RequestConfig config, String json) throws Exception {
        logger.info(String.format("Httpclient send json: %s", json));

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(url);

            if (null != config) {
                httppost.setConfig(config);
            }

            if (!StringUtil.isEmpty(json)) {
                StringEntity entity = new StringEntity(json, "utf-8");
                entity.setContentEncoding("utf-8");
                entity.setContentType("application/x-www-form-urlencoded");
                httppost.setEntity(entity);
            }

            HttpResponse response = httpclient.execute(httppost);
            if (null != response && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String resp = null != entity ? EntityUtils.toString(entity) : null;
                logger.info(String.format("Httpclient response data: %s", resp));
                return resp;
            }
            return null;
        } catch (Exception e) {
            logger.error(String.format("Httpclient post exception: %s", e.getMessage()), e);
            throw new Exception(e);
        } finally {
            closeClient(httpclient);
        }
    }

    /**
     * Convert Map<String, String> to List<NameValuePair>
     * 
     * @param params
     * @return
     */
    private List<NameValuePair> getNameValuePair(Map<String, String> params) {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (Entry<String, String> entry : params.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    /**
     * Close httpclient
     * 
     * @param httpclient
     */
    private void closeClient(CloseableHttpClient httpclient) {
        if (null != httpclient) {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error(String.format("Httpclient close exception: %s", e.getMessage()), e);
            }
        }
    }

    /**
     * Set request config timeout
     * 
     * @return
     */
    public RequestConfig getDefaultTimeoutConfig() {
        return RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIME_OUT)
                .setConnectTimeout(CONNECT_TIME_OUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).build();
    }

    public static HttpClientTool getInstance() {
        return INSTANCE;
    }

    private HttpClientTool() {
    }
}
