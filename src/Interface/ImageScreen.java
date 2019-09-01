/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Lucas
 */

public class ImageScreen {
    
    private JFrame frame;
    private JLabel label = new JLabel();
    private boolean is_hidden = true;
    
    private void setHidden(boolean option){
        is_hidden = option;
    }
    
    private void setImage(BufferedImage image){
        label.setIcon(new ImageIcon(image));
    }
    
    private void setFrame(String filepath){
        frame = new JFrame(filepath);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(label,BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void setScreen(BufferedImage image,String filepath){
        setHidden(false);
        setImage(image);
        setFrame(filepath);
    }
}
