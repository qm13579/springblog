package cn.people;

import cn.people.build.ThingPad;
import cn.people.common.ResultTest;
import cn.people.domain.UserInfo;
import cn.people.openoffice.Office;
import cn.people.thread.MyRunnable;
import cn.people.thread.MyThreadTest;
import cn.people.utils.common.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import java.net.ConnectException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : FENGZHI
 * create at:  2020/2/27  下午8:31
 * @description: 我的测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    private static final Logger log = LoggerFactory.getLogger(MyTest.class);
    @Test
    public void buildTest(){
        ThingPad inter = new ThingPad.Builder().setBoard("inter").build();
        System.out.println(inter);
    }
    @Test
    public void transformPdfTest() throws ConnectException {
        Office.officeToPdf("1239911104622096384.xlsx","test.pdf");
    }

    @Test
    public void lambdaTest(){

        demoA("Zhang", val ->{
            System.out.println(val);
        });
        demoB("zhangsan",System.out::println);

        //定义一个方法，方法参数args1 args2 interface

        MathOperate mathOperate = (val) -> System.out.println(val);


    }
    @Test
    public void logTest(){
        log.info("=========================info===================");
        log.info("=========================info===================");
        log.info("=========================info===================");
        log.error("========================error==================");
        log.error("========================error==================");
        log.error("========================error==================");
    }
    @Test
    public void testObject(){
        UserInfo userInfo = new UserInfo();
        userInfo.setStatus(1);
        boolean empty = ObjectUtils.isEmpty(userInfo);
        System.out.println(empty);
    }

    /**
     * 创建一个类继承thread后成为一个线程类，
     * 成为线程类后必须重写run方法
     * 在调用方法时使用start进行方法建立线程
     *
     * 创建一了类实现runnable接口，重写run方法
     * 实例化myRunnable类后
     * 传入实例化的Thread中，并调用start建立线程
     */
    @Test
    public void threadTest(){
        //继承thread类
        MyThreadTest threadTest = new MyThreadTest();
        threadTest.start();
        //实现runnable接口
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * https://juejin.im/entry/5abb8f6951882555677e9da2
     * 1 自定义线程池 application.properties
     * 2 线程池配置属性类 taskThread
     * 3 创建线程池 TaskExecutePool
     * 4 创建线程任务
     * 5 修改启动类，在启动类添加注解
     */
    @Test
    public void springBootAsyncTest(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(new MyRunnable());
        executorService.shutdown();
    }

    /**
     * LocalDate与string之间的转换
     */
    @Test
    public void LocalDateTest(){
        LocalDate today = LocalDate.now();
        System.out.println(today);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = today.format(fmt);
        System.out.println(format);
    }

    @Test
    public void eumPrintTest(){
        System.out.println(ResultTest.SUCCESS());
    }

    public void demoA(String s,MathOperate m){
        m.operate(s);
    }

    public void demoB(String s,MathOperate m){
        m.operate(s);
    }


}

interface MathOperate{

    void operate(String val);

}

class PageHelp{

    public static void pageHelpQ(Object str){
        System.out.println(str);
    };

}