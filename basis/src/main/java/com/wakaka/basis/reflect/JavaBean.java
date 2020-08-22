package com.wakaka.basis.reflect;

import java.util.Date;

/**
 * 被创建的javaBean
 *
 * @author Wang.hm
 * @date Created in 15:33 2020/5/5
 */
public class JavaBean {
    private String username="username";
    private String password="password";
    private Integer age=11;
    private Date birthday=new Date();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "JavaBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
