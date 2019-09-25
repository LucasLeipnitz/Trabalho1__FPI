/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Color;

/**
 *
 * @author Lucas
 */
public class ImageFilter extends Image{   

    public Color[][] zoomOut(int sx, int sy){
        //int new_width = (int)Math.ceil(width/(double)sx);
        //int new_height = (int)Math.ceil(height/(double)sy);
        int new_width = width/sx;
        int new_height = height/sy;
        Color[][] zoom_rgb = new Color[new_width][new_height];
        int current_x = 0;
        int current_y = 0;
        int current_sx = sx;
        int current_sy = sy;
        //System.out.println(new_width);
        //System.out.println(new_height);
        
        for(int i = 0; i < new_width; i++){
            for(int j = 0; j < new_height; j++){
                zoom_rgb[i][j] = pixelsMedia(split(rgb, current_x, current_y, current_x + sx - 1, current_y + sy - 1),sx,sy);
                current_y = current_y + sy;
            }
            current_x = current_x + sx;
            current_y = 0;
        }
        
        return zoom_rgb;
    }
    
    private Color pixelsMedia(Color[][] pixels, int width, int height){
        int result_red = 0;
        int result_green = 0;
        int result_blue = 0;
        int amout = width*height;
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                result_red = result_red + pixels[i][j].getRed();
                result_green = result_green + pixels[i][j].getGreen();
                result_blue = result_blue + pixels[i][j].getBlue();
            } 
        }
        
        result_red = result_red/amout;
        result_green = result_green/amout;
        result_blue = result_blue/amout;
        
        Color result_color = new Color(result_red,result_green,result_blue);
        return result_color;
    }
    
    private Color[][] split(Color[][] rgb, int init_x, int init_y, int final_x, int final_y){
        Color[][] detached = new Color[final_x - init_x + 1][final_y - init_y + 1];
        //System.out.println(rgb[0][0].getRed());
        int current_x = init_x;
        int current_y = init_y;
        
        for(int i = 0; i < final_x - init_x + 1; i++){
            for(int j = 0; j < final_y - init_y + 1; j++){
                detached[i][j] = rgb[current_x][current_y];
                current_y++;
            }
            current_x++;
            current_y = init_y;
        }
        //detached[0][0] = rgb[0][0];
        
        /*for(int i = init_x; i < final_x + 1; i++){
            for(int j = init_y; j < final_y + 1; j++){
                //System.out.println("a");
                System.out.println(rgb[i][j].getRed());
            }
        }
        System.out.println("/////////////////////");
        for(int i = 0; i < final_x - init_x + 1; i++){
            for(int j = 0; j < final_y - init_y + 1; j++){
                //System.out.println("b");
                System.out.println(detached[i][j].getRed());
            }
        }*/
        
        return detached;
    }
    
    public Color[][] zoomIn(){
        Color[][] zoom_rgb;
        zoom_rgb = resize();
        
        int zoom_width = zoom_rgb.length;
        int zoom_height = zoom_rgb[0].length;
        int red,green,blue;
        int i,j;
        
        /*Color[][] teste = new Color[4][3];
        for(i = 0; i < 4; i++){
            for(j = 0; j < 3; j++){
                teste[i][j] = new Color(i+j + 10,i+j + 10,i+j + 10);
            }
        }
        Color[][] rgb = teste;*/
        int current_x = 0;
        int current_y = 0;
        
        for(i = 0; i < width; i++){
            for(j = 0; j < height; j++){
                zoom_rgb[current_x][current_y] = rgb[i][j];
                current_y = current_y + 2;
            }
            current_x = current_x + 2;
            current_y = 0;
        }
        
        
        /*for(i = 0; i < height; i++){
            for(j = 0; j < width; j++){
                System.out.print(teste[j][i].getRed() + " ");
            }
            System.out.println("");
        }
        System.out.println("////////////////////");*/
        /*for(i = 0; i < zoom_width; i = i + 2){
            for(j = 0; j < zoom_height; j = j + 2){
                System.out.println(zoom_rgb[i][j].getRed());
            }
        }
        */
        
        current_x = 0;
        current_y = 1;
        //System.out.println(zoom_rgb[6][2].getRed());
        for(i = 0; i < width; i++){
            for(j = 0; j < height - 1; j++){
                red = (int)linearInterpolation(current_y, 
                       current_y - 1, zoom_rgb[current_x][current_y - 1].getRed(), 
                       current_y + 1, zoom_rgb[current_x][current_y + 1].getRed());
                green = (int)linearInterpolation(current_y, 
                       current_y - 1, zoom_rgb[current_x][current_y - 1].getGreen(), 
                       current_y + 1, zoom_rgb[current_x][current_y + 1].getGreen());
                blue = (int)linearInterpolation(current_y, 
                       current_y - 1, zoom_rgb[current_x][current_y - 1].getBlue(), 
                       current_y + 1, zoom_rgb[current_x][current_y + 1].getBlue());
                zoom_rgb[current_x][current_y] = new Color(red,green,blue);
                current_y = current_y + 2; 
            }
            current_x = current_x + 2;
            current_y = 1;
        }
        
        
        current_x = 1;  
        for(i = 0; i < width - 1; i++){
            for(j = 0; j < zoom_height; j++){
                red = (int)linearInterpolation(current_x, 
                       current_x - 1, zoom_rgb[current_x - 1][j].getRed(), 
                       current_x + 1, zoom_rgb[current_x + 1][j].getRed());
                green = (int)linearInterpolation(current_x, 
                       current_x - 1, zoom_rgb[current_x - 1][j].getGreen(), 
                       current_x + 1, zoom_rgb[current_x + 1][j].getGreen());
                blue = (int)linearInterpolation(current_x, 
                       current_x - 1, zoom_rgb[current_x - 1][j].getBlue(), 
                       current_x + 1, zoom_rgb[current_x + 1][j].getBlue());
                zoom_rgb[current_x][j] = new Color(red,green,blue);
            }
            current_x = current_x + 2;
        }          
        
        /*for(i = 0; i < zoom_height; i++){
            for(j = 0; j < zoom_width; j++){
                System.out.print(zoom_rgb[j][i].getRed() + " ");
            }
            System.out.println("");
        }*/
        
        return zoom_rgb;
    }
    
    private Color[][] resize(){
        int new_width = width*2 - 1;
        int new_height = height*2 - 1;
        Color[][] res_rgb = new Color[new_width][new_height];
        //System.out.println(res_rgb.length);
        //System.out.println(res_rgb[0].length);  
        return res_rgb;
    }
    
    private double linearInterpolation(double x, double x0, double y0, double x1, double y1){
        double y;
        y = y0 + (y1 - y0)*((x - x0)/(x1 - x0));
        //System.out.println(y);
        return y;
    }
    
    public Color[][] imageConvolution(Color[][] rgb, double[][] kernel){
        Color[][] result = new Color[width][height];
        
        kernel = kernelRotate(kernel);
        
        int[][] pixels_red = new int[3][3];
        int[][] pixels_blue = new int[3][3];
        int[][] pixels_green = new int[3][3];
        int i,j;
        
        for(i = 0; i < width; i++){
            result[i][0] = rgb[i][0];
        }
        for(i = 0; i < width; i++){
            result[i][height - 1] = rgb[i][height - 1];
        }
        for(i = 0; i < height; i++){
            result[0][i] = rgb[0][i];
        }for(i = 0; i < height; i++){
            result[width - 1][i] = rgb[width - 1][i];
        }
        
        for(i = 1; i < width - 1; i++){
            for(j = 1; j < height - 1; j++){
                pixels_red[0][0] = rgb[i - 1][j - 1].getRed();
                pixels_red[0][1] = rgb[i][j - 1].getRed();
                pixels_red[0][2] = rgb[i + 1][j - 1].getRed();
                pixels_red[1][0] = rgb[i - 1][j].getRed();
                pixels_red[1][1] = rgb[i][j].getRed();
                pixels_red[1][2] = rgb[i + 1][j].getRed();
                pixels_red[2][0] = rgb[i - 1][j + 1].getRed();
                pixels_red[2][1] = rgb[i][j + 1].getRed();
                pixels_red[2][2] = rgb[i + 1][j + 1].getRed();
                
                pixels_green[0][0] = rgb[i - 1][j - 1].getGreen();
                pixels_green[0][1] = rgb[i][j - 1].getGreen();
                pixels_green[0][2] = rgb[i + 1][j - 1].getGreen();
                pixels_green[1][0] = rgb[i - 1][j].getGreen();
                pixels_green[1][1] = rgb[i][j].getGreen();
                pixels_green[1][2] = rgb[i + 1][j].getGreen();
                pixels_green[2][0] = rgb[i - 1][j + 1].getGreen();
                pixels_green[2][1] = rgb[i][j + 1].getGreen();
                pixels_green[2][2] = rgb[i + 1][j + 1].getGreen();
                
                pixels_blue[0][0] = rgb[i - 1][j - 1].getBlue();
                pixels_blue[0][1] = rgb[i][j - 1].getBlue();
                pixels_blue[0][2] = rgb[i + 1][j - 1].getBlue();
                pixels_blue[1][0] = rgb[i - 1][j].getBlue();
                pixels_blue[1][1] = rgb[i][j].getBlue();
                pixels_blue[1][2] = rgb[i + 1][j].getBlue();
                pixels_blue[2][0] = rgb[i - 1][j + 1].getBlue();
                pixels_blue[2][1] = rgb[i][j + 1].getBlue();
                pixels_blue[2][2] = rgb[i + 1][j + 1].getBlue();
                
                result[i][j] = new Color(pixelConvolution(pixels_red,kernel),pixelConvolution(pixels_green,kernel),pixelConvolution(pixels_blue,kernel));
            }
        }
        return result;
    }
    
    private double[][] kernelRotate(double[][] kernel){
        double auxiliar;
        
        auxiliar = kernel[0][0];
        kernel[0][0] = kernel[2][2];
        kernel[2][2] = auxiliar;
        
        auxiliar = kernel[0][2];
        kernel[0][2] = kernel[2][0];
        kernel[2][0] = auxiliar;
        
        auxiliar = kernel[0][1];
        kernel[0][1] = kernel[2][1];
        kernel[2][1] = auxiliar;
        
        auxiliar = kernel[1][0];
        kernel[1][0] = kernel[1][2];
        kernel[1][2] = auxiliar;
        
        /*for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(kernel[i][j] + " ");
            }
            System.out.println("");
        }*/
        
        return kernel;
    }
    
    private int pixelConvolution(int[][] pixels, double[][] kernel){
        
        double result = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                result = result + pixels[i][j]*kernel[i][j];
            }  
        }
        
        if(result > 255){
            result = 255;
        }
        if(result < 0){
            result = 0;
        }
        
        return (int)result;
    }
}
