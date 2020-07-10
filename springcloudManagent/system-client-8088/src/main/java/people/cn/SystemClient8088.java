package people.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import people.cn.common.IdWorker;


@SpringBootApplication
@EnableEurekaClient
public class SystemClient8088 {
    public static void main(String[] args) {
        SpringApplication.run(SystemClient8088.class,args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
