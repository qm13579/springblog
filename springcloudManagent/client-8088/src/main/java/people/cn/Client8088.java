package people.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Client8088 {

    public static void main(String[] args) {
        SpringApplication.run(Client8088.class,args);
    }

}
