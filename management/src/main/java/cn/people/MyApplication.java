package cn.people;
import cn.people.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author run
 */
@EnableCaching
@MapperScan("cn.people.mapper")
@SpringBootApplication
public class MyApplication {



    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }


}
