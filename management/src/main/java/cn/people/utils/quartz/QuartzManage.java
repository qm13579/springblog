package cn.people.utils.quartz;

import cn.people.domain.QuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.quartz.TriggerBuilder.newTrigger;
import javax.annotation.Resource;
import java.util.Date;

/**
 * 任务调度任务
 * @author : FENGZHI
 * create at:  2020/4/13  下午1:28
 * @description: 任务调度
 * 构建job信息
 * 构建触发器trigger
 * 加入调度
 */
@Component
public class QuartzManage {

    private static final Logger logger = LoggerFactory.getLogger(QuartzManage.class);
    private static final String JOB_NAME = "TASK_";

    @Resource
    private Scheduler scheduler;

    public void addJob(QuartzJob quartzJob) {
        logger.info("----------addJob------------");
        logger.info(quartzJob.toString());
        try {
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class)
                    .withIdentity(JOB_NAME + quartzJob.getId())
                    .build();
            //构建触发器
            CronTrigger cronTrigger = newTrigger()
                    .withIdentity(JOB_NAME + quartzJob.getId())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()))
                    .build();
            //提供一个不限量的数据容器，用于在实行任务过程中取值，在项目中用于存放bean filed，创建反射
            cronTrigger.getJobDataMap().put(QuartzJob.JOB_KEY,quartzJob);

            JobDataMap map = cronTrigger.getJobDataMap();
            System.out.println(map);
            map.forEach((key,value) -> {
                System.out.println(key+":"+value);
            });
            //重置启动时间
            ((CronTriggerImpl)cronTrigger).setStartTime(new Date());
            //执行定时任务
            scheduler.scheduleJob(jobDetail,cronTrigger);
            //暂停任务
            if (quartzJob.getPause()){
                pauseJob(quartzJob);
                logger.info("暂停任务：" + quartzJob.getId());
            }
        }catch (Exception e){
            logger.error("创建定时任务失败");
        }
    }

    /**
     * 暂停一个任务
     * @param quartzJob
     */
    public void pauseJob(QuartzJob quartzJob){
        try{
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.pauseJob(jobKey);
        }catch (Exception e){
            logger.error("定时任务暂停失败",e);

        }
    }

}
