package shape.geometric;

import shape.Shape;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Point;

public class Square extends Shape {

    private int c;

    public Square(Point center, int c) {
        super(center);
        this.c = c;
    }

    // Change the size of the square
    @Override
    public void setSize(int newSize) {
        c = newSize;
    }

    // Methods how to draw a Square
    @Override
    public void draw(Graphics2D g) {

        g.setStroke(new BasicStroke(1));

        int x = (int) (getCenter().getX());
        int y = (int) (getCenter().getY());
        g.drawRect(x, y, c, c);
    }
}