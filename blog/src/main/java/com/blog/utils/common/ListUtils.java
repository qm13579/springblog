package com.blog.utils.common;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    /**
     * 返回列表中的差集
     * @param list1
     * @param list2
     * @return
     */
    public List<String> DifferenceSet(List<String> list1,List<String> list2){
        //差集,判断前者列表元素是否在后者列表中存在,若不存在加入列表中
        List<String> newList = new ArrayList<>();
        for (String str1:list1) {
            for (String str2:list2) {
                if(str1.equals(str2)){
                    break;
                }else {
                    newList.add(str1);
                    break;
                }
            }
        }
        return newList;
    }
}