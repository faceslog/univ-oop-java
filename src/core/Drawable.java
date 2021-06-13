package core;

import java.awt.Graphics2D;

@FunctionalInterface
public interface Drawable {

    // How to draw the class that implements Drawable
    void draw(Graphics2D g);

}