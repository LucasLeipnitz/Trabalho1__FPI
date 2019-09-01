/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Lucas
 */
public class ImageControl {
    
    private BufferedImage image = null;
    private String filepath = null;
    
    public void setFilePath(String filepath){
        this.filepath = filepath;
    }
    
    public String getFilePath(){
        return filepath;
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public void setImage(){
        try{
            image = ImageIO.read(new File(filepath));
            
            /*
            width = img.getWidth();
            height = img.getHeight();
            System.out.printf("%dx%d\n",width,height);
            */
            
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }
    
    public void saveImage(String name){
         File outputfile = new File(name);
         try{
            ImageIO.write(image, "jpg", outputfile);
         }catch (Exception exc){
            throw new NullPointerException("Image = null");
         }
    } 
}