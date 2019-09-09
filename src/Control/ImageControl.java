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
    private int width;
    private int height;
    
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
            width = image.getWidth();
            height = image.getHeight();
            
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
}
