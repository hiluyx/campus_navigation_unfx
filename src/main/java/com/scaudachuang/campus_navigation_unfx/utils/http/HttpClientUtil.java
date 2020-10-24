package com.scaudachuang.campus_navigation_unfx.utils.http;

import com.alibaba.fastjson.JSONObject;
import com.scaudachuang.campus_navigation_unfx.POJO.FlaskServerResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
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

        httpGet.setConfig(RequestConfig
                .custom()
                .setConnectTimeout(5000)
                .build());
        // 执行请求
        response = httpclient.execute(httpGet);
        // 判断返回状态是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        try {
            response.close();
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public static FlaskServerResponse doPost2Flask(InputStream img,
                                                   String fileName,
                                                   String host,
                                                   String port,
                                                   String route) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder("http://" + host +":"+ port + "/" + route);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost upload = new HttpPost(builder.build());

        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();


        entityBuilder.addBinaryBody("file",img, ContentType.MULTIPART_FORM_DATA,fileName);

        upload.setEntity(entityBuilder.build());

        upload.setConfig(RequestConfig
                .custom()
                .setConnectTimeout(5000)
                .build());

        CloseableHttpResponse response = httpclient.execute(upload);

        FlaskServerResponse ret = new FlaskServerResponse();

        try{
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity responseEntity = response.getEntity();
                JSONObject reObj = JSONObject.parseObject(EntityUtils.toString(responseEntity, "utf-8"));
                ret.setId(Integer.parseInt(reObj.getString("b_id")));
                ret.setBuildingName(reObj.getString("building_name"));
            }
        }finally {
            response.close();
            httpclient.close();
        }
        return ret;
    }
}
