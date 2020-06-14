package cn.people.utils.quartz;

import cn.people.domain.QuartzJob;
import cn.people.utils.thread.ThreadPoolExecutorUtil;
import lombok.SneakyThrows;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author : FENGZHI
 * create at:  2020/4/11  下午9:16
 * @description:
 */
@Async
public class ExecutionJob  extends QuartzJobBean {

    private final static ThreadPoolExecutor EXECUTOR = ThreadPoolExecutorUtil.getPool();
    private final Logger logger = LoggerFactory.getLogger(ExecutionJob.class);


    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) {

        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        logger.info("任务准备执行，任务名称{}",quartzJob.getJobName());
        QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(),
                quartzJob.getMethodName(), quartzJob.getParams());
        Future future = EXECUTOR.submit(task);
        future.get();

        logger.info("任务执行完毕");

    }
}
