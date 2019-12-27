package com.test;

//import com.baidu.aip.http.AipRequest;
import com.baidu.aip.imageclassify.AipImageClassify;

import com.baidu.aip.ocr.AipOcr;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class Sample {

    public static final String APP_ID = "18036267";
    public static final String API_KEY = "RWxN9tw7AHproCh2UfKDgnSE";
    public static final String SECRET_KEY = "Rok2WMqiQMCgpuQXu09RDKvRqhtFfLk2";

    public static AipOcr init(){
        //初始化
//        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        //设置网络
        client.setConnectionTimeoutInMillis(20000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }
    @Test
    public void sample() throws IOException {
        //传入可选的参数
        HashMap<String, String> options = new HashMap<>();

        AipOcr client = init();
        //参数为本地图片路径
//        String image = "test.png";
        String filePath = "D:\\project\\blog\\src\\main\\resources\\static\\image\\1206502524173971456.png";
//        JSONObject res = client.basicGeneral(filePath, options);
        File file = new File(filePath);
        byte[] bytes = Files.readAllBytes(file.toPath());
        System.out.println(bytes);
//        JSONObject res = client.basicGeneral(bytes, options);
        JSONObject res = client.basicGeneral(filePath, options);
        System.out.println(res);
    }

}
