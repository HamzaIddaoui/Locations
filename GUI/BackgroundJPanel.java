package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundJPanel extends JPanel {
    private ImageIcon backgroundImage;

    public BackgroundJPanel(ImageIcon image,int width,int height){
        this.backgroundImage = new ImageIcon(image.getImage().getScaledInstance(
                width,height,Image.SCALE_SMOOTH
        ));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.backgroundImage.getImage(),0,0,null);
    }

}
