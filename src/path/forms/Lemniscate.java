package path.forms;

import path.Path;
import static java.lang.Math.*;
import java.awt.Point;

public class Lemniscate extends Path {

    private static final double degrees = toRadians(0.1);

    private final double center = (double) getWidth() / 2;
    private final double h = (double) (getHeight() * 180) / 720;

    private double theta;

    public Lemniscate(int width, int height) {
        super(false, width, height);
        this.theta = 0;
    }

    @Override
    public Point computeNextPoint(int speed) {

        int x = (int) ( (h * Math.sqrt(2) * (Math.cos(theta))) / (1 + Math.pow(Math.sin(theta), 2)));
        int y = (int) ( (h * Math.sqrt(2) * ((Math.cos(theta)) * Math.sin(theta))) / (1 + Math.pow(Math.sin(theta), 2)));

        theta += degrees * speed;

        return new Point((int) (center + x), (int) (center + y));
    }
}
