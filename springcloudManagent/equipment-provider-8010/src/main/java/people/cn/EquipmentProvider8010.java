package people.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import people.cn.common.IdWorker;

@SpringBootApplication
@EnableEurekaClient
public class EquipmentProvider8010 {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentProvider8010.class,args);
    }

//    @Bean
//    public IdWorker idWorker(){
//        return new IdWorker();
//    }

}
