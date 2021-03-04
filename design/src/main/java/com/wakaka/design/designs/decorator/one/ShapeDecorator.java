package com.wakaka.design.designs.decorator.one;

/**
 * @author Crysmart
 * @date 2021/1/5 17:22
 */
public abstract class ShapeDecorator implements Shape {

    protected Shape shape;

    public ShapeDecorator(Shape shape){
        this.shape = shape;
    }

    @Override
    public void draw() {
        shape.draw();
    }
}
