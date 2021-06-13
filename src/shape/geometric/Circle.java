package shape.geometric;

import shape.Shape;
import java.awt.*;

public class Circle extends Shape {

    private int radius;

    public Circle(Point center, int radius) {
        super(center);
        this.radius = radius;
    }

    // Increase the radius of the circle to augment the size of the shape
    @Override
    public void setSize(int newSize) {
        radius = newSize;
    }

    // Methods to draw the circle
    @Override
    public void draw(Graphics2D g) {

        g.setStroke(new BasicStroke(1));

        int diameter = radius * 2;
        int x = (int) (getCenter().getX() - getRadius());
        int y = (int) (getCenter().getY() - getRadius());
        g.drawOval(x, y, diameter, diameter);
    }

    private double getRadius() {
        return radius;
    }
}
