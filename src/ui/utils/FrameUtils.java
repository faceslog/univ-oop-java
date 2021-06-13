package ui.utils;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class FrameUtils {

    // This methods take a JFrame and set its position in the center of the screen depending the resolution
    public static void setFrameCenter(JFrame jf){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - jf.getWidth()) / 2;
        int y = (screen.height - jf.getHeight()) / 2 - 32;
        jf.setLocation(x, y);
    }

    // This methods take a JFrame and set its size depending of the screen resolution
    public static void setFrameSizeFromScreenResolution(JFrame jf){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Width is approx 4/6 of the actual screen resolution and height is 2/3
        jf.setBounds(0,0, (screenSize.width / 6) * 4, (screenSize.height / 3) * 2);
    }

    // This methods take a JFrame and the name of an image located in the assets folder and display this image as the
    // JFrame Background
    public static void setFrameImgBg(JFrame jf, String name) {

        jf.setContentPane(new JPanel() {

            @Override
            protected void paintComponent(Graphics g){

                try {
                    Image img = Toolkit.getDefaultToolkit().getImage(FrameUtils.class.getResource("/assets/".concat(name)));

                    if(img != null){
                        g.drawImage(img, 0, 0, jf.getWidth(), jf.getHeight(), jf);
                    }

                } catch (Exception e){
                   e.printStackTrace();
                }
            }
        });
    }
}
