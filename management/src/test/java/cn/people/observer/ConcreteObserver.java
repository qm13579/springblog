package cn.people.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Observable;
import java.util.Observer;

/**
 * @author : FENGZHI
 * create at:  2020/3/7  下午10:01
 * @description: as
 */
@Component
public class ConcreteObserver implements Observer {

    private ConcreteSubject concreteSubject;

    public ConcreteObserver(ConcreteSubject subject) {
        this.concreteSubject = subject;
        subject.addObserver(this);
    }

    //    public void update(int state) {
//        System.out.println("concreteObserver1 know state had change to"+state);
//        doSomething();
//    }
    private void doSomething(){
        System.out.println("concreteObserver1 do something");
    }

    @Override
    public void update(Observable o, Object arg) {
        doSomething();
        System.out.println("---------->");
    }
}
