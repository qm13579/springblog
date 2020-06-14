package cn.people.mapper;

import cn.people.domain.QuartzJob;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/4/13  下午1:02
 * @description:
 */
public interface QuartzJobMapper {

    /**
     * 查询启用任务
     * @return
     */
    @Select("select * from quartz_job")
    @Results({
            @Result(property = "jobName", column = "job_name"),
            @Result(property = "beanName", column = "bean_name"),
            @Result(property = "methodName", column = "method_name"),
            @Result(property = "cronExpression", column = "cron_expression"),
    })
    List<QuartzJob> selectQuartzJob();
}
