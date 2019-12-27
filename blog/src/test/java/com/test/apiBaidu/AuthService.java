package com.test.apiBaidu;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AuthService {
    static String API_KEY = "RWxN9tw7AHproCh2UfKDgnSE";
    static String SECRET_KEY = "Rok2WMqiQMCgpuQXu09RDKvRqhtFfLk2";

//    public static String getAuth() throws MalformedURLException {
//        String API_KEY = "RWxN9tw7AHproCh2UfKDgnSE";
//        String SECRET_KEY = "Rok2WMqiQMCgpuQXu09RDKvRqhtFfLk2";
//        return getAuth(API_KEY,SECRET_KEY);
//    }

    @Test
    public  String getAuth() {
        //获取token
        String authHost = "http://aip.baidubce.com/oauth/2.0/token?";
        String getAccessToken = authHost
                + "grant_type=client_credentials"
                + "&client_id=" + API_KEY
                +  "&client_secret=" + SECRET_KEY;
        try {
            URL url = new URL(getAccessToken);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key:map.keySet()) {
                System.err.println(key+"---->"+map.get(key));
            }
            //定义bufferReader可输入流读取url的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ( (line=in.readLine()) != null ){
                result += line;
            }
            System.err.println("result"+result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");

            log.info("请求成功"+access_token);
            return access_token;
        }catch (Exception e){
            log.debug("请求失败");
            log.debug(e.toString());
        }
        return null;
    }
}
