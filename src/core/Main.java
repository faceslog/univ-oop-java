package core;

import ui.StartFrame;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            StartFrame sFrame = new StartFrame();
        });

    }
}
