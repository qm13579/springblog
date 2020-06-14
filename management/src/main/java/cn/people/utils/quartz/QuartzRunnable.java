package cn.people.utils.quartz;

import cn.people.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 执行定时任务
 * @author : FENGZHI
 * create at:  2020/4/14  下午8:49
 * @description:
 */
public class QuartzRunnable implements Callable {
    /**
     * target为task被Spring容器管理
     */
    private Object target;
    private Method method;
    private String params;

    public QuartzRunnable(String beanName, String methodName, String params) throws NoSuchMethodException {

        this.target = SpringContextHolder.getBean(beanName);
        this.params = params;

        if (StringUtils.isNotBlank(params)){
            this.method = target.getClass().getDeclaredMethod(methodName,String.class);
        }else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }

    }

    @Override
    public Object call() throws Exception {
        ReflectionUtils.makeAccessible(method);
        if (StringUtils.isNotBlank(params)){
            method.invoke(target,params);
        }else {
            method.invoke(target);
        }
        return null;
    }
}
