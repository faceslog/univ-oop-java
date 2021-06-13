package path.forms;

import path.Path;
import static java.lang.Math.*;
import java.awt.Point;

public class Spiral extends Path {

    // Mathematicals coefficients
    private static final double degrees = toRadians(0.1);
    private static final double end = 360 * 2 * 10 * degrees;
    private static final double a = 0.0;
    private static final double c = 1.0;

    // Helpers for the responsibe design
    private final double h = (double) (getHeight() * 20) / 720; // We said that for a height of 720 we have b = 20
    private final double center = (double) getWidth() / 2;

    private double theta;

    public Spiral(int width, int height){
        super(true, width, height);
        this.theta = 0.0;
    }

    // Methods to compute the next point of the Spiral path
    @Override
    public Point computeNextPoint(int speed){

        double r = a + h * pow(theta, 1 / c);
        double x = r * cos(theta);
        double y = r * sin(theta);

        if(theta < end && !getIsPathMovingBack()) {
            theta += degrees * speed;
        } else if(theta > 0 && getIsPathMovingBack()){
            theta -= degrees * speed;
        } else {
            setIsPathMovingBack(!getIsPathMovingBack());
        }

        return new Point((int) (center + x), (int) (center + y));
    }

}
