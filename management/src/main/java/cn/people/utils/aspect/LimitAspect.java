package cn.people.utils.aspect;

import cn.people.utils.annotation.Limit;
import cn.people.utils.common.LimitType;
import cn.people.utils.common.RequestHolder;
import cn.people.utils.common.Result;
import cn.people.utils.common.ResultCode;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author : FENGZHI
 * create at:  2020/3/14  下午4:03
 * @description:
 */
@Aspect
@Component
public class LimitAspect {

    private final RedisTemplate<Object,Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(LimitAspect.class);

    public LimitAspect(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Pointcut("@annotation(cn.people.utils.annotation.Limit))")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取请求
        HttpServletRequest request = RequestHolder.getHttpServletRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //
        Limit limit = method.getAnnotation(Limit.class);
        LimitType limitType = limit.limitType();
        String key = limit.key();
        String join = StringUtils.join(limit.prefix(), "_", key, "_");

        boolean empty = StringUtils.isEmpty(key);
        String keys = StringUtils.join(limit.prefix(), "_", key, "_", request.getRequestURI().replaceAll("/", "_"));
        String luaScript = buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);

        Long count = redisTemplate.opsForValue().increment(keys, 1);
        if (count <= limit.count()){
            redisTemplate.expire(keys,limit.period(), TimeUnit.SECONDS);
            logger.info("第{}次访问key为{},描述为[{}]的接口",count,keys,limit.name());
        }
        if (count > limit.count()){
            return new Result(ResultCode.ERROR);
        }
        return joinPoint.proceed();
    }
    /**
     * 限流脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }
}
