package ui;

import path.Path;
import path.forms.Lemniscate;
import path.forms.Spiral;
import shape.Shape;
import shape.geometric.Circle;
import shape.geometric.Square;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel implements Runnable {

    // Speed limits
    private static final int MAX_SPEED = 20;
    private static final int MIN_SPEED = 1;
    // Freq at which you which will be drawn the shapes creating the path
    private static final int DRAWING_FREQ = 50;
    // Shape size Limits
    private static final int MAX_SHAPE_SIZE = 50;
    private static final int MIN_SHAPE_SIZE = 10;

    private Path currentPath;
    private Shape currentShape;
    private int currentSpeed;
    private int currentShapeSize;
    private boolean hasStarted;

    // The Drawing Panel should only be instantiate in the UI package !
    protected DrawingPanel(Dimension jFrameDimension){

        hasStarted = false;
        currentSpeed = MIN_SPEED;
        currentShapeSize = 30;

        setOpaque(false);
        setBackground(Color.BLACK);
        setPreferredSize(jFrameDimension);
        setLayout(new BorderLayout());

        currentPath = new Lemniscate(getPreferredSize().width, getPreferredSize().height);
        currentShape = new Circle(currentPath.computeNextPoint(this.currentSpeed), this.currentShapeSize);
        add(currentShape, BorderLayout.CENTER);
    }

    // Implements Runnable
    @Override
    public void run() {
        int freq = 0;

        while (true){
            try {

                if(hasStarted){

                    currentShape.move(currentPath, currentSpeed);

                    ///////// CREATE Shape TO DRAW THE PATH //////////
                    if(freq == 0){
                        cloneCurrentShape();
                        freq += currentSpeed;
                    } else if(freq >= DRAWING_FREQ){
                        cloneCurrentShape();
                        freq = 0;
                    } else {
                        freq += currentSpeed;
                    }
                    /////////////// END PATH DRAWER ///////////////

                    // Refresh the JPanel to display the new shapes
                    repaint();
                }

                Thread.sleep(3);

            } catch (InterruptedException | CloneNotSupportedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Augment the speed of the moving shape should be only toggled from the MVC in the same package
    protected void increaseSpeed(){
        if(currentSpeed < MAX_SPEED) currentSpeed++;
    }

    // Decrease the speed of the moving shape should be only toggled from the MVC in the same package
    protected void decreaseSpeed(){
        if(currentSpeed > MIN_SPEED) currentSpeed--;
    }

    // Set if the Shape has started to move or not on the currentPath should be only toggled from the MVC in the same package
    protected void setHasStarted(boolean hasStarted) { this.hasStarted = hasStarted; }

    // Get if the Shape has started to move or not on the currentPath should be only toggled from the MVC in the same package
    protected boolean getHasStarted() {
        return hasStarted;
    }

    // Increase the currentShape Size should be only toggled from the MVC in the same package
    protected void increaseShapeSize(){

        if(currentShapeSize < MAX_SHAPE_SIZE) {
            currentShapeSize++;
            currentShape.setSize(currentShapeSize);
        }

    }

    // Decrease the current Shape Size should be only toggled from the MVC in the same package
    protected void decreaseShapeSize(){

        if(currentShapeSize > MIN_SHAPE_SIZE) {
            currentShapeSize--;
            currentShape.setSize(currentShapeSize);
        }

    }

    // Change the shape color
    protected void changeShapeColor(){
        currentShape.setRandomColor();
    }

    // Set a new path this methods should be only toggled from the MVC in the same package
    protected void setCurrentPathFromMVC(ControlManager.ClickBtn key) {

        if(key == ControlManager.ClickBtn.selectLemniscate) {
            currentPath = new Lemniscate(getPreferredSize().width, getPreferredSize().height);
        }

        if(key == ControlManager.ClickBtn.selectSpiral) {
            currentPath = new Spiral(getPreferredSize().width, getPreferredSize().height);
        }

        // Remove all current JComponent, shapes from the previous path
        removeAll();
        // add again our current shape
        add(currentShape, BorderLayout.CENTER);
    }

    // Set a new Shape this methods should be only toggled from the MVC in the same package
    protected void setCurrentShapeFromMVC(ControlManager.ClickBtn key) {

        if(key == ControlManager.ClickBtn.selectCircle) {
            currentShape = new Circle(currentPath.computeNextPoint(currentSpeed), currentShapeSize);
        }

        if(key == ControlManager.ClickBtn.selectSquare) {
            currentShape = new Square(currentPath.computeNextPoint(currentSpeed), currentShapeSize);
        }

        // add again our current shape
        add(currentShape, BorderLayout.CENTER);
        // Validate the components and all its subcomponents
        validate();
    }

    // Clone the currentShape
    private void cloneCurrentShape() throws CloneNotSupportedException {
        Shape temp = (Shape) currentShape.clone();
        add(temp, BorderLayout.CENTER);
    }
}
