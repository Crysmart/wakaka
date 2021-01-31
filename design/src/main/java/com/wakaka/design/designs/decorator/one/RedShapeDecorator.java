package com.wakaka.design.designs.decorator.one;

/**
 * @author Crysmart
 * @date 2021/1/5 17:22
 */
public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("为图形涂上红色");
    }
}
