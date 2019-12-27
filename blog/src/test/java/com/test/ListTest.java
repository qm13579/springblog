package com.test;

import com.blog.utils.common.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
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
}
