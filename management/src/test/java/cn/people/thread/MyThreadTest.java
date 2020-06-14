package cn.people.thread;

/**
 * @author : FENGZHI
 * create at:  2020/4/16  下午8:48
 * @description:
 */
public class MyThreadTest extends Thread {

    @Override
    public void run() {
        System.out.println("this is myThread");
    }
}
