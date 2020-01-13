package com.test;

import com.blog.utils.common.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
public class ListTest {
    @Test
    public void testList(){
        List<String> strings = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list2.add("b");
        list2.add("c");
        ListUtils listUtils = new ListUtils();
        List<String> strings1 = listUtils.DifferenceSet(list1, list2);
        System.out.println(strings1);
    }

    /**
     * 差集测试
     */
    @Test
    public void DifferenceSet(){
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();

        set1.add("admin");
        set1.add("user");

        set2.add("admin");

        set2.removeAll(set1);
        System.out.println("差集是:"+set2);
        System.out.println("差集测试");

    }
}
