package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class ControlManager extends JPanel {

    // Enum for all type of JButtons that will be added should only be used in the UI package
    protected enum ClickBtn {
        startAndStop,
        speedIncrease,
        speedDecrease,
        shapeSizeIncrease,
        shapeSizeDecrease,
        setShapeColor,
        selectLemniscate,
        selectSpiral,
        selectSquare,
        selectCircle
    }

    private final DrawingPanel drawingPanel;
    private final ButtonGroup path;
    private final ButtonGroup shape;

    // The control manager should only be instantiate in the UI package !
    protected ControlManager(DrawingPanel drawingPanel, Dimension jFrameDimension) {

        this.drawingPanel = drawingPanel;
        this.path = new ButtonGroup();
        this.shape = new ButtonGroup();

        setOpaque(true); //set to false to hide the background
        setBackground(Color.DARK_GRAY);
        setPreferredSize(jFrameDimension);

        setLayout(new GridLayout(10, 1, 5, 10));

        TreeMap<ClickBtn, AbstractButton> btnMap = new TreeMap<>();
        // JButtons
        btnMap.put(ClickBtn.startAndStop, new JButton("Play & Pause"));
        btnMap.put(ClickBtn.speedIncrease, new JButton("Speed ++"));
        btnMap.put(ClickBtn.speedDecrease, new JButton("Speed --"));
        btnMap.put(ClickBtn.shapeSizeIncrease, new JButton("Shape size ++"));
        btnMap.put(ClickBtn.shapeSizeDecrease, new JButton("Shape size --"));
        btnMap.put(ClickBtn.setShapeColor, new JButton("Shape Color"));
        // Radio Btn
        btnMap.put(ClickBtn.selectLemniscate, new JRadioButton("Lemniscate"));
        btnMap.put(ClickBtn.selectSpiral, new JRadioButton("Spiral"));
        btnMap.put(ClickBtn.selectSquare, new JRadioButton("Square"));
        btnMap.put(ClickBtn.selectCircle, new JRadioButton("Circle"));

        // Follow the order of the Click Btn Enum
        for(Map.Entry<ClickBtn, AbstractButton> entry: btnMap.entrySet()) {

            switch (entry.getKey()){
                case setShapeColor -> addChangeColorListener(entry.getValue());
                case startAndStop -> addStartStopListener(entry.getValue());
                case speedIncrease, speedDecrease -> addSetSpeedListener(entry.getKey(), entry.getValue());
                case shapeSizeIncrease, shapeSizeDecrease -> addSetShapeSizeListener(entry.getKey(), entry.getValue());
                case selectSpiral, selectLemniscate -> addPathSelectorListener(entry.getKey(), entry.getValue());
                case selectCircle, selectSquare -> addShapeSelectorListener(entry.getKey(), entry.getValue());
                default -> System.out.println("Error Invalid Button");
            }
            add(entry.getValue());
        }

        Thread thread = new Thread(drawingPanel);
        thread.start();
    }

    // Add a start and stop event on a JButton for the drawingPanel
    private void addStartStopListener(AbstractButton btn){
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.ORANGE);
        btn.addActionListener(e -> drawingPanel.setHasStarted(!drawingPanel.getHasStarted()));
    }

    // Add a button event to change the currentShape color in the drawingPanel
    private void addChangeColorListener(AbstractButton btn){
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.CYAN);
        btn.addActionListener(e -> drawingPanel.changeShapeColor());
    }

    // Add an event listener on a JButton to set the speed of the Shape in drawingPanel
    private void addSetSpeedListener(ClickBtn key, AbstractButton btn){

        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.GREEN);

        if(key == ClickBtn.speedIncrease){
            btn.addActionListener(e -> drawingPanel.increaseSpeed());
        }

        if(key == ClickBtn.speedDecrease) {
            btn.addActionListener(e -> drawingPanel.decreaseSpeed());
        }
    }

    // Add an event listener on a JButton to set the shape Size of the drawingPanel
    private void addSetShapeSizeListener(ClickBtn key, AbstractButton btn){

        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.RED);

        if(key == ClickBtn.shapeSizeIncrease){
            btn.addActionListener(e -> drawingPanel.increaseShapeSize());
        }

        if(key == ClickBtn.shapeSizeDecrease) {
            btn.addActionListener(e -> drawingPanel.decreaseShapeSize());
        }
    }

    // Add an event listener on a JButton to set the Path of the drawingPanel
    private void addPathSelectorListener(ClickBtn key, AbstractButton btn){
        btn.setOpaque(false);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(e -> drawingPanel.setCurrentPathFromMVC(key));
        path.add(btn);
    }

    // Add an event listener on a JButton to set the Shape of the drawingPanel
    private void addShapeSelectorListener(ClickBtn key, AbstractButton btn) {
        btn.setOpaque(false);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(e -> drawingPanel.setCurrentShapeFromMVC(key));
        shape.add(btn);
    }

}
