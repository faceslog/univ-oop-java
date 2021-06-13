package path;

import java.awt.*;

public abstract class Path {

    // Height and Width of the path used to compute the next path point and the responsive design
    private final int width;
    private final int height;

    // Is the path is able to move back and is bidirectional
    private final boolean isAbleToMoveBack;
    private boolean isPathMovingBack;

    public Path(boolean isAbleToMoveBack, int width, int height){

        this.isAbleToMoveBack = isAbleToMoveBack;
        this.isPathMovingBack = false;
        this.width = (width / 3) * 2; // 2/3 of the screen
        this.height = height;
    }

    // Return the height defined for the path
    public int getHeight() {
        return height;
    }

    // Return the width defined for the path
    public int getWidth() {
        return width;
    }

    // Return true/false if the path is moving back to his starter point
    public boolean getIsPathMovingBack(){

        if(isAbleToMoveBack) {
            return isPathMovingBack;
        } else {
            return false;
        }
    }

    // Set if the path should move back to his starter point or no
    public void setIsPathMovingBack(boolean isPathMovingBack) {

        if(isAbleToMoveBack) {
            this.isPathMovingBack = isPathMovingBack;
        }

    }

    // Speed is an int between 1 - MAX_INT
    public abstract Point computeNextPoint(int speed);
}
