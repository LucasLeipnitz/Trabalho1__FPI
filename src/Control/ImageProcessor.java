/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import static java.util.Arrays.sort;

/**
 *
 * @author Lucas
 */
public class ImageProcessor extends Image{
         
    /*Processed Image*/
    private Color[][] new_lum_rgb = null;
    
    /*Mirroring*/
    private Color[][] hflip_rgb = null;
    private Color[][] vflip_rgb = null;
    
    /*Equalization*/
    private Color[][] equa_rgb = null;
    
    
    public Color[][] getRGB(){
        return super.getRGB();
    }
    
    public Color[][] getNewLumRGB(){
        return new_lum_rgb;
    }
    
    public Color[][] getVFlipRGB(){
        return vflip_rgb;
    }
    
    public Color[][] getHFlipRGB(){
        return hflip_rgb;
    }
    
    public Color[][] getEquaRGB(){
        return equa_rgb;
    }
    
    public int getWidth(){
        return super.getWidth();
    }
    
    public int getHeight(){
        return super.getHeight();
    }
    
    protected void setWidth(int width){
        this.width = width;
    }
    
    protected void setHeight(int height){
        this.height = height;
    }
    
    protected void setRGBDimension(){
        rgb = new Color[width][height];
        new_lum_rgb = new Color[width][height];
        hflip_rgb = new Color[width][height];
        vflip_rgb = new Color[width][height];
        equa_rgb = new Color[width][height];
    }
    
    public void setDimensions(int width, int height){
        super.setDimensions(width, height);
    }
    
    public void setRGBValues(BufferedImage image){
        super.setRGBValues(image);
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
    
    public double[] getHistogram(Color[][] rgb, int weigth, int height){
        double[] histogram = new double[256];
        int i,j;
        /*Inicializa posições do histrograma com 0*/
        for(i = 0; i < 256; i++){
            histogram[i] = 0;
        }
        
        /*Cria histograma, como é tons de cinza, todos os tons
        (azul, vermelho e verde) são o mesmo valor, então pega qualquer um*/
        for(i = 0; i < weigth; i++){
            for(j = 0; j < height; j++){
                histogram[rgb[i][j].getRed()]++;
            }
        }
        
        /*Ordena em ordem crescente*/
        sort(histogram);
        
        
        /*for(i = 0; i < 256; i++){
            System.out.println(histogram[i]);
        }*/
        
        return histogram;
    }
    
    public void histogramEqualization(Color[][] rgb){ 
        double[] histogram = new double[256];
        double[] hist_cum = new double[256];
        double a = 255.0/(width*height);
        int i,j;
        
        histogram = getHistogram(rgb,width,height);
        hist_cum[0] = a*histogram[0];
        for(i = 1; i < 256; i++){
            hist_cum[i] = hist_cum[i-1] + a*histogram[i];
        }
        
        for(i = 0; i < width; i++){
            for(j = 0; j < height; j++){
                equa_rgb[i][j] = new Color((int)hist_cum[rgb[i][j].getRed()],(int)hist_cum[rgb[i][j].getGreen()],(int)hist_cum[rgb[i][j].getBlue()]);
            }
        }
    }
    
    private int getTargetShade(int level, double[] src_histogram, double[] trgt_histogram){
        double src_value,trgt_value,current_value;
        int position;
        
        src_value = src_histogram[level];
        
        trgt_value = src_value - trgt_histogram[0];
        position = 0;
        
        for(int i = 1; i < 256; i++){
            if(src_value > trgt_histogram[i]){
                current_value = src_value - trgt_histogram[i];
                if(current_value < trgt_value){
                    trgt_value = current_value;
                    position = i;
                }
            }else{
                current_value = trgt_histogram[i] - src_value;
                if(current_value < trgt_value){
                    trgt_value = current_value;
                    position = i;
                }
            }   
        }
        
        return position;
    }
    
    public Color[][] histogramMatching(Color[][] src_rgb,int src_width, int src_height, Color[][] trgt_rgb, int trgt_width, int trgt_height){
        Color[][] hm_rgb = new Color[width][height];
        double[] hm_histogram = new double[256];
        double[] src_histogram = new double[256];
        double[] src_hist_cum = new double[256];
        double[] trgt_histogram = new double[256];
        double[] trgt_hist_cum = new double[256];
        int i,j;
        double a;
        
        src_histogram = getHistogram(src_rgb,src_width,src_height);
        trgt_histogram = getHistogram(trgt_rgb,trgt_width,trgt_height);
        
        a = 255.0/(src_width*src_height);
        src_hist_cum[0] = a*src_histogram[0];
        for(i = 1; i < 256; i++){
            src_hist_cum[i] = src_hist_cum[i-1] + a*src_histogram[i];
        }
        
        a = 255.0/(trgt_width*trgt_height);
        trgt_hist_cum[0] = a*trgt_histogram[0];
        for(i = 1; i < 256; i++){
            trgt_hist_cum[i] = trgt_hist_cum[i-1] + a*trgt_histogram[i];
        }        
        
        for(i = 0; i < 256; i++){
            hm_histogram[i] = getTargetShade(i,src_hist_cum,trgt_hist_cum);
        }
        
        for(i = 0; i < width; i++){
            for(j = 0; j < height; j++){
                hm_rgb[i][j] = new Color((int)hm_histogram[rgb[i][j].getRed()],(int)hm_histogram[rgb[i][j].getGreen()],(int)hm_histogram[rgb[i][j].getBlue()]);
            }
        }
        
        return hm_rgb;
    }
    
    public Color[][] linearTransformation(int a, int b){
        Color[][] trans_rgb = new Color[width][height];
        int red,green,blue;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                red = a*rgb[i][j].getRed() + b;
                if(red > 255){
                    red = 255;
                }
                if(red < 0){
                    red = 0;
                }
                green = a*rgb[i][j].getGreen() + b;
                if(green > 255){
                    green = 255;
                }
                if(green < 0){
                    green = 0;
                }
                blue = a*rgb[i][j].getBlue() + b;
                if(blue > 255){
                    blue = 255;
                }
                if(blue < 0){
                    blue = 0;
                }
                trans_rgb[i][j] = new Color(red,green,blue);
            }
        } 
        return trans_rgb;
    }
    
    public Color[][] getNegative(){
        Color[][] negative_rgb = new Color[width][height];
        int red,green,blue;
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                red = 255 - rgb[i][j].getRed();
                green = 255 - rgb[i][j].getGreen();
                blue = 255 - rgb[i][j].getBlue();
                negative_rgb[i][j] = new Color(red,green,blue);
            }
        } 
        return negative_rgb;
    }
}
