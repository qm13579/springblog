package people.cn.common.interfaceBean;

/**
 * @author : FENGZHI
 * create at:  2020/3/8  下午9:07
 * @description: subject
 */
public interface Subject {

    void addObserver(ObserverInfo observer);
    void deleObserver(ObserverInfo observerInfo);
    void notifyObserver(String info);
}
