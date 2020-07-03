package people.cn.common.annotation;

import people.cn.common.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author apple
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {
    //资源名称，用于接口描述
    String name() default "";
    //资源 key
    String key() default "";
    //key prefix
    String prefix() default "";
    //时间的 单位秒
    int period();
    //限制访问次数
    int count();
    //限制类型
    LimitType limitType() default LimitType.CUSTOMER;

}
