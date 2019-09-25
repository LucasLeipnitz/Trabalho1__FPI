/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author Lucas
 */
public class ImageControl {
    
    private BufferedImage image = null;
    private BufferedImage current_image = null;
    private BufferedImage rst_image = null;
    private String filepath = null;
    private ImageProcessor processor = new ImageProcessor();
    private ImageFilter filter = new ImageFilter();
    
    public void setFilePath(String filepath){
        this.filepath = filepath;
    }
    
    public String getFilePath(){
        return filepath;
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public BufferedImage getRstImage(){
        return rst_image;
    }
    
    public void setImage(){
        try{
            image = ImageIO.read(new File(filepath));
            current_image = image;
            
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }
    
    public void saveImage(String name){
         File outputfile = new File(name);
         try{
            ImageIO.write(rst_image, "jpg", outputfile);
         }catch (Exception exc){
            throw new NullPointerException("Image = null");
         }
    }
    
    public void selectFile(){
        JFileChooser chooser = new JFileChooser();//Selecionador de arquivos
        chooser.showOpenDialog(chooser);
        try{
            //filepath tenta receber caminho do arquivo que usuário escolheu
            filepath = chooser.getSelectedFile().getAbsolutePath();
        }catch(NullPointerException exc){
            //Usuário clicou em cancel e não escolheu nenhuma imagem
            filepath = null;
        }
    }
    
    public void copyImage(){
        rst_image = image;
        current_image = image;
    }
    
    public void greyImage(){       
        int width = current_image.getWidth();
        int height = current_image.getHeight();
        
        processor.setDimensions(width, height);
        processor.setRGBValues(current_image);
        processor.setNewLuminanceRGB();
        rst_image = Conversor.RGBToImage(Conversor.colorToInt(processor.getNewLumRGB(),width,height),width,height);
        current_image = rst_image;
    }
    
    public void equaImage(){
        int width = current_image.getWidth();
        int height = current_image.getHeight();
        
        processor.setDimensions(width, height);
        processor.setRGBValues(current_image);
        //processor.setNewLuminanceRGB();
        processor.histogramEqualization(processor.getRGB());
        rst_image = Conversor.RGBToImage(Conversor.colorToInt(processor.getEquaRGB(),width,height),width,height);
        current_image = rst_image;
    }
    
    public void hFlipImage(){
        int width = current_image.getWidth();
        int height = current_image.getHeight();
        
        processor.setDimensions(width, height);
        processor.setRGBValues(current_image);
        processor.setHorizontalFlipRGB();
        rst_image = Conversor.RGBToImage(Conversor.colorToInt(processor.getHFlipRGB(),width,height),width,height);
        current_image = rst_image;
    }
    
    public void vFlipImage(){
        int width = current_image.getWidth();
        int height = current_image.getHeight();
        
        processor.setDimensions(width, height);
        processor.setRGBValues(current_image);
        processor.setVerticalFlipRGB();
        rst_image = Conversor.RGBToImage(Conversor.colorToInt(processor.getVFlipRGB(),width,height),width,height);
        current_image = rst_image;
    }
    
    public double[] imageHistogram(){
        int width = current_image.getWidth();
        int height = current_image.getHeight();
        
        processor.setDimensions(width, height);
        processor.setRGBValues(current_image);
        return processor.getHistogram(processor.getRGB(),width,height);
    }
    
    public void setBright(int a,int b){
        int width = current_image.getWidth();
        int height = current_image.getHeight();
        
        processor.setDimensions(width, height);
        processor.setRGBValues(current_image);
        rst_image = Conversor.RGBToImage(Conversor.colorToInt(processor.linearTransformation(a,b),width,height),width,height);
        current_image = rst_image;
    }
    
    public void setNegative(){
        int width = current_image.getWidth();
        int height = current_image.getHeight();
        
        processor.setDimensions(width, height);
        processor.setRGBValues(current_image);
        rst_image = Conversor.RGBToImage(Conversor.colorToInt(processor.getNegative(),width,height),width,height);
        current_image = rst_image;
    }
    
    public void imageMatching(){
        int width = current_image.getWidth();
        int height = current_image.getHeight();
        
        processor.setDimensions(width, height);
        processor.setRGBValues(current_image);
        rst_image = Conversor.RGBToImage(Conversor.colorToInt(processor.histogramMatching
        (processor.getRGB(),width,height,processor.getNewLumRGB(),width,height),width,height),width,height);
        current_image = rst_image;
    }
    
    public void imageZoomOut(){
        int width = current_image.getWidth();
        int height = current_image.getHeight();
        
        filter.setDimensions(width, height);
        filter.setRGBValues(current_image);
        //filter.split(processor.getRGB(),2,1,5,3);
        //Color[][] a = filter.zoomOut(2, 2);
        
        //System.out.println(a.length);
        //System.out.println(a[0].length);
        
        rst_image = Conversor.RGBToImage(Conversor.colorToInt(filter.zoomOut(2, 2),width/2,height/2),width/2,height/2);
        current_image = rst_image;
    }
    
    public void imageZoomIn(){
        int width = current_image.getWidth();
        int height = current_image.getHeight();
        
        filter.setDimensions(width, height);
        filter.setRGBValues(current_image);
        Color[][] zoom_rgb = filter.zoomIn();
        int zoom_width = zoom_rgb.length;
        int zoom_height = zoom_rgb[0].length;
        rst_image = Conversor.RGBToImage(Conversor.colorToInt(zoom_rgb,zoom_width,zoom_height),zoom_width,zoom_height);
        current_image = rst_image;
    }
    
    public void imageConvolution(double[][] kernel){
        int width = current_image.getWidth();
        int height = current_image.getHeight();
        
        filter.setDimensions(width, height);
        filter.setRGBValues(current_image);
        rst_image = Conversor.RGBToImage(Conversor.colorToInt(filter.imageConvolution(filter.getRGB(),kernel),width,height),width,height);
        current_image = rst_image;
    }
}
