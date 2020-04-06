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
import java.util.Random;

/**
 *
 * @author karen alvarado
 */
public class Suavizado {
    public static Image agregarRuidoAditivo(Image io, int p){
        int dim =  io.getWidth(null)*io.getHeight(null); 
        int cp = dim/100*p;//cantidad de pixeles
        BufferedImage bi = AbrirImagen.toBufferedImage(io); 
        Random ran = new Random(); 
        for(int x=0; x<cp; x++){
            int r = ran.nextInt(bi.getHeight()); //reglon aleatorio
            int c = ran.nextInt(bi.getWidth()); //columna aleatoria
            bi.setRGB(c, r, Color.white.getRGB());
        }
        return AbrirImagen.toImage(bi); 
    }
    
    public static Image agregarRuidoSustractivo(Image io, int p){
        int dim =  io.getWidth(null)*io.getHeight(null); 
        int cp = dim/100*p;//cantidad de pixeles
        BufferedImage bi = AbrirImagen.toBufferedImage(io); 
        Random ran = new Random(); 
        for(int x=0; x<cp; x++){
            int r = ran.nextInt(bi.getHeight()); //reglon aleatorio
            int c = ran.nextInt(bi.getWidth()); //columna aleatoria
            bi.setRGB(c, r, Color.black.getRGB());
        }
        return AbrirImagen.toImage(bi); 
    }
    
    public static Image mezcladito(Image io, int p){
        int dim =  io.getWidth(null)*io.getHeight(null); 
        int cp = dim/100*p;//cantidad de pixeles
        BufferedImage bi = AbrirImagen.toBufferedImage(io); 
        Random ran = new Random(); 
      
            for(int x=0; x<cp; x++){
            int r = ran.nextInt(bi.getHeight()); //reglon aleatorio
            int c = ran.nextInt(bi.getWidth()); //columna aleatoria
            bi.setRGB(r, c, Color.BLACK.getRGB());
            bi.setRGB(r, c, Color.WHITE.getRGB()); 
        }
        return AbrirImagen.toImage(bi); 
    }
    public static Image suavizar(Image io, int [][]mascara){
        //recorrer el buffer
        BufferedImage bi = AbrirImagen.toBufferedImage(io); 
        BufferedImage bNuevo = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB); 
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                int rgb = calcularNuevoTono(x, y, bi, mascara);//x, y es el punto en el que nos encontramos
                bNuevo.setRGB(x, y, rgb);
            }
        }
        return AbrirImagen.toImage(bNuevo); 
    }

    private static int calcularNuevoTono(int x, int y, BufferedImage bi, int[][] mascara) {
        //consultar a los vecinos 
        //recorrer la máscara 
        //int r= x-1; //renglon
        //int c =y-1; //columna
        int auxRojito=0, auxGreen = 0, auxBlue =0;//acumular
        Color col = null; 
        int konta=0; 
        for(int i=0, r = x-1; i<mascara.length; r++){
            for(int j=0, c = y-1; j<mascara.length; c++){
                if(mascara[i][j]!=0 ){
                   try{
                       int rgb = bi.getRGB(r, c); 
                       konta++; 
                       col =  new Color(rgb); 
                       auxRojito += col.getRed(); //multiplicar por la máscara
                       auxGreen += col.getGreen(); 
                       auxBlue += col.getBlue();  
                   } catch(Exception e){
                       //e.printStackTrace(); no va nada de nah'
                   }
                }
            }
        }
        if(konta!=0){
        auxRojito/=konta;
        auxGreen/=konta;
        auxBlue/=konta;}
        col = new Color(auxRojito,auxGreen,auxBlue);
        return col.getRGB();
    }
    
}
/*
- recorrer la imagen pixel x pixel (x, y)

- Colocar la máscara utilizando el punto de interes con la coordenada anterior

- recorremos la máscara y acumulamos la info de los vecinos para obtener un nuevo tono


*/