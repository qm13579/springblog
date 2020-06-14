package cn.people.observer;

import java.util.ArrayList;



/**
 * @author : FENGZHI
 * create at:  2020/3/7  下午9:02
 * @description: 被观察者类
 */
abstract class Subject {
    private ArrayList<Observer> observers;
    public Subject() {
        observers = new ArrayList<>();
    }
    public void attach(Observer observer){
        observers.add(observer);
    }
    public void detach(Observer observable){
        observers.remove(observable);
    }
    public void notifyObserver(int state){
        for (Observer obs :observers) {
            obs.update(state);
        }
    }
}
