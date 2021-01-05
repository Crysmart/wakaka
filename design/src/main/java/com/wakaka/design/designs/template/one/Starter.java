package com.wakaka.design.designs.template.one;


/**
 * 模板模式
 * @author Crysmart
 * @date 2021/1/5 16:54
 */
public class Starter {
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
