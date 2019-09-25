/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Lucas
 */
public class Image {
    /*Image Info*/
    protected Color[][] rgb = null;
    protected int width = 0;
    protected int height = 0;
    
    public Color[][] getRGB(){
        return rgb;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    protected void setWidth(int width){
        this.width = width;
    }
    
    protected void setHeight(int height){
        this.height = height;
    }
    
    protected void setRGBDimension(){
        rgb = new Color[width][height];
    }
    
    public void setDimensions(int width, int height){
        setWidth(width);
        setHeight(height);
        setRGBDimension();
    }
    
    public void setRGBValues(BufferedImage image){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                rgb[i][j] = new Color(image.getRGB(i,j));
            }
        }
    }  
}
