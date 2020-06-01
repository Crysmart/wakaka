package com.wakaka.basis.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Crysmart
 */
public class StreamDemo {
    public static void main(String[] args) {
    }

    private static void test7() {
        Stream<String> nameStream = Stream.of("Zebe", "July", "Yaha").filter(name -> {
            if (!name.isEmpty()) {
                //当此参数直接被使用的时候，未进行运算的时候不会进行打印，就是惰性求值
                System.out.println("过滤流，当前名称：" + name);
                //当此参数被调用进行运算的时候，就是及早求值
                /*name = name +name;
                System.out.println(name);*/
                return true;
            }
            return false;
        });
        //当流内有惰性语句时，在执行collect的时候才会进行使用（打印）
        nameStream.collect(Collectors.toList());
        //对于第二次重复使用Stream则会抛出异常，流已被使用，所以流只能被操作一次后就会关闭
        nameStream.collect(Collectors.toList());
    }

    private static void test6() {
        User u1 = new User("zhangsan","123",55).setDept(Arrays.asList(new Dept("运营部","00100"),new Dept("IT部","12312311")));
        User u2 = new User("lisi","12321312",123).setDept(Arrays.asList(new Dept("测试部","11011"),new Dept("IT部","12312311")));
        User u3 = new User("wangwu","45674534",11).setDept(Arrays.asList(new Dept("运营部","00100"),new Dept("IT部","12312311")));
        User u4 = new User("zhaoliu","3452134",77).setDept(Arrays.asList(new Dept("运维部","11011"),new Dept("IT部","12312311")));
        List<User> users = Arrays.asList(u1, u2, u3, u4);
        users.stream()
                //对list的内部list进行过滤，筛选出 隶属于这个部门的人员（人员部门有多个）
                .filter(user -> user.getDept().stream().anyMatch(dept -> "00100".equals(dept.getDeptCode())))
                //循环打印
                .forEach(System.out::println);
    }

    private static void test5() {
        List<Integer> list = Arrays.asList(1,23,4,453);
        Integer integer = list.stream()
                //reduce，接收一个BinaryOperator对象操作，数据操作
                .reduce(Integer::sum).get();
        System.out.println("integer = " + integer);
    }

    private static void test4() {
        List<String> list = Arrays.asList("aaa","bbbb","cccccc");
        String s = list.stream()
                //集合最小值，条件手动设置
                //.min(Comparator.comparing(CharSequence::length))
                //集合最大值，条件手动设置
                .max(Comparator.comparing(CharSequence::length))
                .get();
        System.out.println("s = " + s);
    }

    private static void test3() {
        List<String> list1 = Arrays.asList("aaa","bbb","ccc");
        List<String> list2 = Arrays.asList("ddd","eee","fff");
        List<String> collect = Stream.of(list1, list2)
                //flatMap,接受的返回参数仅仅是流,将所有集合转换成流进行处理
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    private static void test2() {
        List<String> strList = Arrays.asList("aaa","bbb","ccc");
        List<String> collect = strList.stream()
                //集合操作，所有对象同时更改，Function内容返回对象
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    private static void test1() {
        List<String> strList = Arrays.asList("aaa","bbb","ccc");
        //构建流式会花费资源
        List<String> list = strList.stream()
                //filter，对象过滤，Predicate内容返回为boolean
                .filter(name -> !name.contains("bbb"))
                .collect(Collectors.toList());
        System.out.println("list = " + list);
    }
}

class User{
    private String username;
    private String password;
    private Integer age;
    private List<Dept> dept;

    public User(String username, String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public User setDept(List<Dept> dept) {
        this.dept = dept;
        return this;
    }

    public List<Dept> getDept() {
        return dept;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", dept=" + dept +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(username, password, age, dept);
    }
}

class Dept{
    private String deptName;
    private String deptCode;

    public Dept(String deptName, String deptCode) {
        this.deptName = deptName;
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptName='" + deptName + '\'' +
                ", deptCode='" + deptCode + '\'' +
                '}';
    }
}
