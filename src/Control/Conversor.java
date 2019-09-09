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
public class Conversor {
    
    public static Color[][] imageToRGB(BufferedImage image){
        Color[][] rgb = null;
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++){
                rgb[i][j] = new Color(image.getRGB(i,j));
            }
        } 
        return rgb;
    }
    
    public static BufferedImage RGBToImage(int[][] rgb, int width, int height){
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++){
                image.setRGB(i,j,rgb[i][j]);
                //System.out.print(image.getRGB(i,j) + "\n");
            }
        } 
        return image;
    }
    
    public static int[][] colorToInt(Color[][] rgb_color, int width, int height){
        int[][] rgb_int = new int[width][height];
        int red;
        int green;
        int blue;
        int a;
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                red = rgb_color[i][j].getRed();
                green = rgb_color[i][j].getGreen();
                blue = rgb_color[i][j].getBlue();
                a = 255;
                rgb_int[i][j] = (a << 24) | (red << 16) | (green << 8) | blue;
            }
        }
        return rgb_int;
    }
}
