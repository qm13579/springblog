package com.blog.utils.orcApi;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Sample {

    public static final String APP_ID = "18036267";
    public static final String API_KEY = "RWxN9tw7AHproCh2UfKDgnSE";
    public static final String SECRET_KEY = "Rok2WMqiQMCgpuQXu09RDKvRqhtFfLk2";

    public static AipOcr init(){
        //初始化
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        //设置网络
        client.setConnectionTimeoutInMillis(20000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

    public void sample(){
        //传入可选的参数
        HashMap<String, String> options = new HashMap<>();
        options.put("language","CHN_ENG");
        options.put("detect_direction","true");
        options.put("detect_language","true");
        options.put("probability","true");
        AipOcr client = init();
        //参数为本地图片路径
//        String image = "test.png";
        String filePath = "D:\\project\\blog\\src\\main\\resources\\static\\image\\1206502524173971456.png";
        JSONObject res = client.basicGeneral(filePath, options);
        System.out.println(res);
    }
}
