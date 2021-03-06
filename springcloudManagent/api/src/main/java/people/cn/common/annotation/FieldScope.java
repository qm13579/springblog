package people.cn.common.annotation;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldScope {

    public String tableAlias() default "";

}
