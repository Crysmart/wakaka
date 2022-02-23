package com.wakaka.design.designs.proxy.one;

public class UserController implements IUserController {

    public void login() throws InterruptedException {
        System.out.println("登录中。。。");
        Thread.sleep(5000);
        System.out.println("登录成功！");
    }
}
