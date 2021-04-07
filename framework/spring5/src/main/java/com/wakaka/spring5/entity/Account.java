package com.wakaka.spring5.entity;

/**
 * @author Crysmart
 * @date 2021/4/7 14:26
 */
public class Account {
    private String name;
    private String pwd;

    public Account(){}
    public Account(String name,String pwd){
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
