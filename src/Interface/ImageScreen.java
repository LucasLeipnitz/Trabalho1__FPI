/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
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
    private BufferedImage img = null;
    private JLabel label = new JLabel();
    private String filepath = null;
    
    /*
    private int width = 0;
    private int height = 0;
    */
    
    public ImageScreen(String filepath){
        this.filepath = filepath;
    }
    
    private void readImage(){
        try{
            img = ImageIO.read(new File(filepath));
            
            /*
            width = img.getWidth();
            height = img.getHeight();
            System.out.printf("%dx%d\n",width,height);
            */
            
        }catch(Exception exc){
            exc.printStackTrace();
        }
        
        /*
        Color[][] rgb = new Color[width][height];
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                rgb[i][j] = new Color(img.getRGB(i,j));
            }
        }
        */  
    }
    
    private void setImage(){
        label.setIcon(new ImageIcon(img));
    }
    
    private void setFrame(){
        frame = new JFrame(filepath);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(label,BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void setScreen(){
        readImage();
        setImage();
        setFrame();
    }
    
    /*Não deveria fazer parte da interface*/
    public void saveImage(String name){
         File outputfile = new File(name);
         try{
            ImageIO.write(img, "jpg", outputfile);
         }catch (Exception exc){
             exc.printStackTrace();
         }
    }
}
