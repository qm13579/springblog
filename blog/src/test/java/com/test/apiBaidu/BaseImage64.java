package com.test.apiBaidu;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

@Slf4j
public class BaseImage64 {
//    private final String POST_URL = "http://aip.baidubce.com/rest/2.0/ocr/v1/general_basic?access_token=" + this.getAuth();
    private final String POST_URL = "http://aip.baidubce.com/rest/2.0/ocr/v1/general_basic?access_token=" + this.getAuth();
    static String API_KEY = "RWxN9tw7AHproCh2UfKDgnSE";
    static String SECRET_KEY = "Rok2WMqiQMCgpuQXu09RDKvRqhtFfLk2";

    //读取图片
    public String getImageFromPath(){
        InputStream in;
        byte[] data = null;
        //读取图片为byte数组
        try{
            in = new FileInputStream("D:\\project\\blog\\src\\main\\resources\\static\\image\\Koala.jpg");
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }catch (Exception e){
            log.debug("读取失败");
            log.debug(e.toString());
        }
        BASE64Encoder encoder = new BASE64Encoder();
        //返回base61编码后的urlencode的字节数组
        return URLEncoder.encode(encoder.encode(data));
    }

    @Test
    public void checkFile() throws IOException, URISyntaxException {
        //拼接token
        String image = "image="+this.getImageFromPath();
        String result = post(image);
        log.info(result);
    }
    //发送请求
    private String post(String param) throws IOException, URISyntaxException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost();
        System.out.println("url---->"+POST_URL);
        URI url = new URI(POST_URL);
        post.setURI(url);

        //设置请求头
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        StringEntity entity = new StringEntity(param);
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
//        System.out.println(response.toString());
        if(response.getStatusLine().getStatusCode()==200){
            String str;
            try{
                str = EntityUtils.toString(response.getEntity());
                log.info(str);
                return str;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;

    }

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
