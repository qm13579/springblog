package cn.people.utils.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : FENGZHI
 * create at:  2020/4/13  下午4:33
 * @description:
 */
@Slf4j
@Component
public class TestTask {
    public void run(){
        log.info("定时任务执行testTask");
    }
}
