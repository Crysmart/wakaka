package com.wakaka.design.designs.template.one;

class Robot extends AbstractAction{//抽象实现
    @Override
    void eat() {
        System.out.println("吃机油");
    }
    @Override
    void sleep() { }
    @Override
    void work() {
        System.out.println("流水线工作");
    }
}