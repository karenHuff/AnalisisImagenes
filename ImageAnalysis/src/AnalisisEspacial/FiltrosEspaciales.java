/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalisisEspacial;

import analisisImagenes.AbrirImagen;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author karen alvarado
 */
public class FiltrosEspaciales {
    public static Image generarImagenGrises(Image io){
        BufferedImage bi = AbrirImagen.toBufferedImage(io); 
        Color color; 
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                color = new Color(bi.getRGB(x, y));
                int prom = (color.getRed()+color.getGreen()+color.getBlue())/3; 
                color =  new Color(prom, prom, prom); 
                bi.setRGB(x, y, color.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
    
    public static Image generarImagenNegativa(Image io){
        BufferedImage bi = AbrirImagen.toBufferedImage(io); 
        Color color; 
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                color=new Color(bi.getRGB(x, y)); 
                color =  new Color(255-color.getRed(),
                        255-color.getGreen(),
                        255-color.getBlue()); 
                bi.setRGB(x, y, color.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
     
    public static Image iluminarImagen(Image imagen, int alpha){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
            color = new Color(bi.getRGB(x, y));
            int r = color.getRed() + alpha;
            int g = color.getGreen() + alpha;
            int b = color.getBlue() + alpha;
            color = new Color(validarLimites(r),
            validarLimites(g),
            validarLimites(b));
            bi.setRGB(x,y,color.getRGB());
        }
        return AbrirImagen.toImage(bi);
    }
    
    public static Image expansionLineal(int r1, int r2, Image imagen){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
            color = new Color(bi.getRGB(x, y));
            int r = (color.getRed()-r1)*(255/r2-r1);
            int g = (color.getGreen()-r1)*(255/r2-r1);
            int b = (color.getBlue()-r1)*(255/r2-r1);
            color = new Color(validarLimites(r),
            validarLimites(g),
            validarLimites(b));
            bi.setRGB(x,y,color.getRGB());
        }
        return AbrirImagen.toImage(bi);
    }

    public static Image expansionLineal(Histogramas h, Image imagen){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
            color = new Color(bi.getRGB(x, y));
            int r = (color.getRed()-h.getMinR())*(255/h.getMaxR()-h.getMinR());
            int g = (color.getGreen()-h.getMinG())*(255/h.getMaxG()-h.getMinG());
            int b = (color.getBlue()-h.getMinB())*(255/h.getMaxB()-h.getMinB());
             color = new Color(validarLimites(r),
             validarLimites(g),
             validarLimites(b));
             bi.setRGB(x,y,color.getRGB());
        }
        return AbrirImagen.toImage(bi);
    }
    
    public static Image modificarTemperatura(Image imagen, int alpha){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen); 
        Color color; 
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                color = new Color(bi.getRGB(x,y)); 
                int r = color.getRed() + alpha; 
                int g = color.getGreen(); 
                int b = color.getBlue() - alpha; 
                color = new Color(validarLimites(r), 
                        validarLimites(g), 
                        validarLimites(b)); 
                bi.setRGB(x,y, color.getRGB()); 
            }
        }
        return AbrirImagen.toImage(bi);
    }
    public static int validarLimites(int aux){
         if(aux<0) return 0; 
         if(aux>255) return 255; 
         return aux; 
     }
     
 public static Image segmentarImagen(Image imagen, int umbral){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color, colorFondo;
        colorFondo = new Color(255,255,255);
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
            color = new Color(bi.getRGB(x, y));
            int prom = (color.getRed()+ color.getGreen()+color.getBlue())/3;
            if (prom>umbral){
                bi.setRGB(x,y,colorFondo.getRGB());
            }
                       
        }
        return AbrirImagen.toImage(bi);
    }

    public static Image segmentarImagen(Image imagen, int u1, int u2){
        // TODO: garantizar  que el u2>u1
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color, colorFondo;
        colorFondo = new Color(255,255,255);
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
            color = new Color(bi.getRGB(x, y));
            int prom = (color.getRed()+ color.getGreen()+color.getBlue())/3;
            if (!(prom>= u1 && prom<=u2)){
                bi.setRGB(x,y,colorFondo.getRGB());
            }
                       
        }
        return AbrirImagen.toImage(bi);
    }
    
    public static Image binarizacion(Image imagen, int umbral){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color, colorFondo;
        colorFondo = new Color(255,255,255);
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
            color = new Color(bi.getRGB(x, y));
            int prom = (color.getRed()+ color.getGreen()+color.getBlue())/3;
            if (!(prom>= umbral)){
                bi.setRGB(x,y,colorFondo.getRGB());
            }          
        }
        return AbrirImagen.toImage(bi);
    }
     
    public static Image expansionExponencial(Histogramas h, Image imagen, double z){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
            color = new Color(bi.getRGB(x, y));
            int r = validar((int)(Math.pow(1+z,color.getRed())/z));
              int g = validar((int)(Math.pow(1+z,color.getGreen())/z));
              int b = validar((int)(Math.pow(1+z,color.getBlue())/z));     
            // validamos 
            color = new Color(r, g, b);
            bi.setRGB(x, y, color.getRGB());  
        }
        return AbrirImagen.toImage(bi);
    }
    
    public static Image expansionLogaritmica(Histogramas h, Image imagen){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        
        Color color;
        for (int x=0; x< bi.getWidth();x++)
            for(int y=0; y< bi.getHeight();y++){
              // obtener el color
              color = new Color(bi.getRGB(x, y));
              int r = (int)((255*Math.log(1+color.getRed()))/(Math.log(1+255)));
              int g = (int)((255*Math.log(1+color.getGreen()))/(Math.log(1+255)));
              int b = (int)((255*Math.log(1+color.getBlue()))/(Math.log(1+255)));
             
            // validamos 
            color = new Color(r, g, b);
            bi.setRGB(x, y, color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }
    //hacer pruebas 
    public static int metodoIterativo(double[] histograma){
    
        int ui = calcularUmbralInicial(histograma);
        int uNuevo=0;
        System.out.println(ui);
        do{
        uNuevo = ui;
        ui = reajustarUmbral(ui,histograma);
        System.out.println(ui);
        }while(uNuevo!=ui);
        
        return ui;
        
    }


    private static int calcularUmbralInicial(double[] histograma) {
        int numPixels = 0;
        int suma = 0;
        for(int x=0;x<histograma.length;x++){
        numPixels+=histograma[x];
        suma+=histograma[x]*x;
        }
        return (int)(suma/numPixels);
    }


    private static int reajustarUmbral(int ui, double[] histograma) {
       int u1,u2;
       int a1=0,a2=0,n1=0,n2=0;
       a1+=histograma[0];
       for(int x=1;x<ui;x++){
        a1+=histograma[x]*x;
        n1+=histograma[x];
       }
       
        for(int y=ui;y<=255;y++){
        a2+=histograma[y]*y;
        n2+=histograma[y];
       }
        if (n1==0 || n2==0) return 0;
        u1 = a1/n1;
        u2 = a2/n2;
       return (int)((u1+u2)/2);
    }
    
    private static int validar(int i) {
        if(i<0)return 0;
        if(i>255)return 255;
        
        return i;
    }
}
     //implementar dos clases 
    // control de iluminacion si se encuentra en -40 subirlo a 0 
    // control de temperatura 
    /*
        azules 
        rojos
    */
//el fondo siempre se define como blanco 