package com.blog.utils.aspect;

import com.blog.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面
 * @author apple
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private IdWorker idWorker;

    @Pointcut("execution(* com.blog.controller.*.*(..) )")
    public void LogPointcut(){}



    @Before("LogPointcut()")
    public void beginLog(){

        log.info("befor LogBegin");

    }

    /**
     *
     * 在使用security获取用户时，保证次方法要被filter过滤
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("LogPointcut()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURI().toString();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Object proceed = pjp.proceed();
        log.info("url:"+url);
        log.info("time:"+(System.currentTimeMillis()-start));
        log.info("id:"+idWorker.nextId()+"" );
        log.info("username:"+username);
        return proceed;

    }
}
/**
 * 术语
 * 通知--advice：需要完成的工作叫通知，例如在业务逻辑中需要比如事物、日志等先定义好，然后需要的地方在用
 * 连接点--join popoint ：在spring中允许使用通知的地方，基本上每个方法前后抛出时都可以时连接点
 * 切点--poincut：筛选出的连接点，一个类的所有方法都是连接点，但是又不全需要，会筛选出某些作为连接点作为切点
 * 引入--introduction（霸道||用的非常少）：在不改变一个现有类代码的情况下，为该类添加属性和方法
 * 目标--target：被通知的对象，也就是需要加入额外的代码对象
 * 织入--weaving：把切面加入程序代码的过程
 */
