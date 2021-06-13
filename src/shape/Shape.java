package shape;

import core.Drawable;
import path.Path;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class Shape extends JComponent implements Drawable, Cloneable {

    // Center of the shape
    private Point center;
    // Current color of the shape
    private Color color;

    public Shape(Point center){
        this.center = center;
        // Default color is Red
        this.color = Color.RED;
    }

    // Get the center of the shape
    public Point getCenter() {
        return center;
    }

    // Set the center of the shape
    public void setCenter(Point center) {
        this.center = center;
    }

    // Move the form on the given path at a given speed
    public void move(Path currentPath, int speed){
        setCenter(currentPath.computeNextPoint(speed));
    }

    // Clone the current object
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Set the size of the shape
    public abstract void setSize(int newSize);

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setColor(color);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        draw(g);
    }

    // Set a random rgb color on the form
    public void setRandomColor(){
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);

        color = new Color(r, g, b);
        repaint();
    }
}

