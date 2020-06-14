package cn.people.utils.aspect;

import cn.people.domain.LogPojo;
import cn.people.service.IInfoService;
import cn.people.utils.IdWorker;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/5  下午7:34
 * @description: 访问记录
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;

    SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");

    @Resource
    private IInfoService infoService;

    @Pointcut("execution(* cn.people.controller.*.*(..))")
    public void logPointcut(){}

//    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String url = request.getRequestURI().toString();
        String method = request.getMethod().toString();

        Object proceed = pjp.proceed();
        //把log数据存入缓存中
        List<LogPojo> logPojos = (List<LogPojo>) redisTemplate.opsForList().leftPop("pv");
        Long pvNum = redisTemplate.opsForValue().increment("pvNum", 1);

        long end = System.currentTimeMillis();
        log.info(url+"请求用时"+":"+(end-start));
        LogPojo logs = new LogPojo(idWorker.nextId() + "", url, method, "admin", DATE.format(new Date()),(end-start)+"");
        if (logPojos == null){
            logPojos = new ArrayList<>();
            logPojos.add(logs);
        }else {
            logPojos.add(logs);
        }
        redisTemplate.opsForList().leftPush("pv",logPojos);

//        infoService.logInsert(log);
        return proceed;

    }

}
