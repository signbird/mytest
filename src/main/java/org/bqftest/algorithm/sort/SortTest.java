package org.bqftest.algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest {

    
    public static void main(String[] args) {
        
        List<String> lists = new ArrayList<String>();
        lists.add("a");
        lists.add("c");
        lists.add("f");
        lists.add("d");
        Collections.sort(lists);
        
//        Collections.sort(lists, new Comparator<String>(){
//            @Override
//            public int compare(String o1, String o2) {
//                //  按字典顺序正序排列 
//                return o1.compareTo(o2);
//            }});

        System.out.println(lists);
    }
}
