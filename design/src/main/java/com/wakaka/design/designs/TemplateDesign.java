package com.wakaka.design.designs;

/**
 * 模板设计模式
 * @author wakaka
 * @date Created in 16:37 2020/3/29
 */
public class TemplateDesign {
    public static void main(String[] args) {
        System.out.println("=============人类要干的事情===============");
        AbstractAction person = new Person();
        person.command(AbstractAction.EAT);
        person.command(AbstractAction.SLEEP);
        person.command(AbstractAction.WORK);
        System.out.println("=============机器人要干的事情===============");
        AbstractAction robot = new Robot();
        robot.command(AbstractAction.EAT);
        robot.command(AbstractAction.WORK);
    }
}

abstract class AbstractAction{//抽象模板
    /** 定义参数 */
    public static final Integer EAT = 1;
    public static final Integer SLEEP = 2;
    public static final Integer WORK = 3;
    /** 定义动作模板 */
    abstract void eat();
    abstract void sleep();
    abstract void work();
    /** 定义名命令执行 */
    public void command(Integer code){
        switch (code){
            case 1:{
                this.eat();
            }break;
            case 2:{
                this.sleep();
            }break;
            case 3:{
                this.work();
            }break;
            default:
                System.out.println("没有定义");
        }
    }
}

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