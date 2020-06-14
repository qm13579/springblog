package cn.people.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author : FENGZHI
 * create at:  2020/3/15  下午10:52
 * @description:
 */
public class SecurityTest {

    public Boolean check(String ...permissions){
        Stream<String> stream = Arrays.stream(permissions);
        List<Object> objects = new ArrayList<>();

        stream.anyMatch(objects::contains);
        System.out.println("this is check");
        return true;
    }

}
