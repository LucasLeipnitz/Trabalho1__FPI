/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private boolean is_closed = true;
    
    private void setClosed(boolean option){
        is_closed = option;
    }
    
    public boolean getClosed(){
        return is_closed;
    }
    
    private void setImage(BufferedImage image){
        label.setIcon(new ImageIcon(image));
    }
    
    private void setFrame(String filepath){
        frame = new JFrame(filepath);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                is_closed = true;
            }
        });
        
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(label,BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void setScreen(BufferedImage image, String filepath){
        setClosed(false);
        setImage(image);
        setFrame(filepath);
    }
}
