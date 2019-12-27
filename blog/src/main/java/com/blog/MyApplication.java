package com.blog;

import com.alibaba.druid.pool.DruidDataSource;
import com.blog.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;

@EnableWebSocket
@MapperScan("com.blog.mapper")
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

    @Bean
    public ServerEndpointExporter ServerEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
