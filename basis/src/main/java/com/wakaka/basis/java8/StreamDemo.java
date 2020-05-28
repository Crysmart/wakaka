package com.wakaka.basis.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Crysmart
 */
public class StreamDemo {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        List<String> strList = Arrays.asList("aaa","bbb","ccc");
        //构建流式会花费资源
        List<String> list = strList.stream()
                //filter类似循环
                .filter(name -> !name.contains("bbb"))
                .collect(Collectors.toList());
        System.out.println("list = " + list);
    }
}
