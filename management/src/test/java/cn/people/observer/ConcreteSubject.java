package cn.people.observer;

import org.springframework.stereotype.Component;

import java.util.Observable;

/**
 * @author : FENGZHI
 * create at:  2020/3/7  下午9:50
 * @description: 被观察者实现类
 */
@Component
public class ConcreteSubject extends Observable {
    private int subjectState;

    public int getSubjectState() {
        return subjectState;
    }

//    public void setSubjectState(int subjectState) {
//        this.subjectState = subjectState;
//        System.out.println("subject state change to:"+subjectState);
//        this.notifyObserver(subjectState);
//    }


    public void setSubjectState(int subjectState) {
        setChanged();
        System.out.println("print this:"+subjectState);
        notifyObservers();
        this.subjectState = subjectState;

    }
}
