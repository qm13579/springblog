package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import people.cn.common.IdWorker;

@Configuration
public class ConfigBean {
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
