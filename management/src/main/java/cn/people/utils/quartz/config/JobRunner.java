package cn.people.utils.quartz.config;

import cn.people.domain.QuartzJob;
import cn.people.mapper.QuartzJobMapper;
import cn.people.utils.quartz.QuartzManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/4/13  下午12:58
 * @description: 实现applicationRunner接口，重写run方法，项目启动时会自动执行run方法
 */
@Component
public class JobRunner implements ApplicationRunner {

    @Resource
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private QuartzManage quartzManage;
    /**
     * 系统启动后激活定时任务
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("----------注入定时任务------------");
        List<QuartzJob> quartzJobs = quartzJobMapper.selectQuartzJob();
        quartzJobs.forEach(quartzManage::addJob);
        System.out.println("----------定时任务注入完成------------");
    }
}
