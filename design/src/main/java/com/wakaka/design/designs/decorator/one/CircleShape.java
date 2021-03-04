package com.wakaka.design.designs.decorator.one;

/**
 * @author Crysmart
 * @date 2021/1/5 17:20
 */
public class CircleShape implements Shape {
    /**
     * 画
     */
    @Override
    public void draw() {
        System.out.println("画圈");
    }
}
