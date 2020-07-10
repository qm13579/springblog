package people.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan({"people.cn.dao","people.cn.system.dao"})
public class EquipmentProvider8010 {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentProvider8010.class,args);
    }

}
