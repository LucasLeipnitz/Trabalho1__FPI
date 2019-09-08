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
public class ImageProcessor {
        
    /*Image Info*/
    private Color[][] rgb = null;
    private int width = 0;
    private int height = 0;
    
    /*Processed Image*/
    private Color[][] new_lum_rgb = null;
    
    public Color[][] getNewLumRGB(){
        return new_lum_rgb;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    private void setWidth(int width){
        this.width = width;
    }
    
    private void setHeight(int height){
        this.height = height;
    }
    
    private void setRGBDimension(){
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
    
    public void setNewLuminanceRGB(){
        new_lum_rgb = new Color[width][height];
        int[][] li = getNewLuminanceValues();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                new_lum_rgb[i][j] = new Color(li[i][j],li[i][j],li[i][j]);
            }
        }
    }
    
    private int[][] getNewLuminanceValues(){
        int[][] li = new int[width][height];
        double value;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                value =  0.299*rgb[i][j].getRed() +  0.587*rgb[i][j].getGreen() + 0.114*rgb[i][j].getBlue();
                li[i][j] = (int)value;
            }
        }
        return li;
    }
}
