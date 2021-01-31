package com.wakaka.design.designs.decorator.one;

/**
 * 装饰器模式
 * @author Crysmart
 * @date 2021/1/5 16:52
 */
public class Starter {
    public static void main(String[] args) {
        ShapeDecorator circleShape = new RedShapeDecorator(new CircleShape());
        circleShape.draw();
        ShapeDecorator rectangleShape = new RedShapeDecorator(new RectangleShape());
        rectangleShape.draw();
    }
}
