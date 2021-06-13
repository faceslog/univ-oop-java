package ui;

import ui.utils.FrameUtils;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {

    /*
    *  Use to create an initiate a JFrame with customized methods and members
    *  It using many frame utils methods. Instantiate a JFrame
    * */
    public StartFrame() {
        // Set the title
        setTitle("Shape Manager");
        // Set the propriety of default close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the window according to the screen resolution
        FrameUtils.setFrameSizeFromScreenResolution(this);
        // Set that the user cannot resize the window
        setResizable(false);
        // Set the frame to the screen center
        FrameUtils.setFrameCenter(this);
        // Set the background image of the loader
        FrameUtils.setFrameImgBg(this, "bg.jpg");
        // Set a layout for the frame
        setLayout(new BorderLayout());

        // Create a drawingPanel and add it to the frame the width is 3/4 of the JFrame frame size
        DrawingPanel manager = new DrawingPanel(new Dimension((getWidth() / 4) * 3, getHeight()));
        add(manager, BorderLayout.WEST);
        // Create the control manager and add it to the JFrame
        ControlManager controls = new ControlManager(manager, new Dimension((getWidth() / 4) / 2, getHeight()));
        add(controls, BorderLayout.EAST);
        // Display the frame for the user
        setVisible(true);
    }

}
