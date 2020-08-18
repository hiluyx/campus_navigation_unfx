package com.scaudachuang.campus_navigation_unfx.utils.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class HttpClientUtil {
    public static String doGet(String url, Map<String, String> param) throws IOException, URISyntaxException {
        String resultString = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response;
        // 创建uri
        URIBuilder builder = new URIBuilder(url);
        if (param != null) {
            for (String key : param.keySet()) {
                builder.addParameter(key, param.get(key));
            }
        }
        URI uri = builder.build();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uri);
        // 执行请求
        response = httpclient.execute(httpGet);
        // 判断返回状态是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        }try {
            response.close();
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
