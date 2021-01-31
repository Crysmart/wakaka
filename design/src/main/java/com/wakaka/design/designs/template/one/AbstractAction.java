package com.wakaka.design.designs.template.one;

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