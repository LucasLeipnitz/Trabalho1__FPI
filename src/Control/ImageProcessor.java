/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

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
    
    /*Mirroring*/
    private Color[][] hflip_rgb = null;
    private Color[][] vflip_rgb = null;
    
    /*Histogram Equalization*/
    private int[] histogram = new int[256];
    private int maximum_pixels = 0;
    private int[][] equa_image = null;
    
    
    public Color[][] getNewLumRGB(){
        return new_lum_rgb;
    }
    
    public Color[][] getVFlipRGB(){
        return vflip_rgb;
    }
    
    public Color[][] getHFlipRGB(){
        return hflip_rgb;
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
        new_lum_rgb = new Color[width][height];
        hflip_rgb = new Color[width][height];
        vflip_rgb = new Color[width][height];
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
    
    public void setHorizontalFlipRGB(){        
        for(int i = 0; i < width; i++){
            hflip_rgb[i] = Arrays.copyOf(rgb[(width - 1) - i], height);
        }
    }
    
    public void setVerticalFlipRGB(){  
        Color[][] transpose = matrixTranspose(rgb,width,height);
        for(int i = 0; i < height; i++){
            vflip_rgb[i] = Arrays.copyOf(transpose[(height - 1) - i], width);
        }
        vflip_rgb = matrixTranspose(vflip_rgb,height,width);
    }
    
    private Color[][] matrixTranspose(Color[][] matrix, int width, int height){
        Color[][] transpose = new Color[height][width];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                transpose[j][i] = matrix[i][j];
            }
        }  
        return transpose;
    }
}
