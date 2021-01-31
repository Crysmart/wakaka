package com.wakaka.design.designs.template.one;

class Person extends AbstractAction{//抽象实现
    @Override
    void eat() {
        System.out.println("人类要吃汉堡");
    }
    @Override
    void sleep() {
        System.out.println("人类还要睡觉补充能量");
    }
    @Override
    void work() {
        System.out.println("人类做脑力劳动");
    }
}