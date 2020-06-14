package cn.people.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author : FENGZHI
 * create at:  2020/4/14  下午9:05
 * @description:
 */
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext正在初始化");
        SpringContextHolder.applicationContext = applicationContext;

    }

    /**
     * 从静态变量applicationContext中取得bean，自动转型为所赋予对象的类型
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name){
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 检查applicationContext不为空
     */
    public static void assertContextInjected()  {
        if (applicationContext == null){
            throw new IllegalStateException("applicationContext属性未注入");
        }
    }

    /**
     * 清除springContextHolder中的applicationContext为null
     */
    public static void clearHolder(){
        applicationContext = null;
    }

    @Override
    public void destroy() throws Exception {
        clearHolder();
    }
}
