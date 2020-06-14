package cn.people.utils.task;

import cn.people.domain.LogPojo;
import cn.people.service.imp.InfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/29  下午9:25
 * @description:
 * 访问记录日志
 */
@Slf4j
@Configuration
@EnableScheduling
public class LogTask {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private InfoServiceImpl infoService;

    @Scheduled(cron = "0 0 0 * * ?")
    private void configureTask(){
      log.info("执行访问日志持久化任务");
        List<LogPojo> logPojos = (List<LogPojo>) redisTemplate.opsForList().leftPop("pv");
        for (LogPojo logPojo :logPojos) {
            infoService.logInsert(logPojo);
        }

    }
}
