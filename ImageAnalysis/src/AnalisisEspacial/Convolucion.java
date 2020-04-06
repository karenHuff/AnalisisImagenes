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
public class Convolucion {
    public static Image convolucion(Image io, int [][]mascara, int div, int offset){
        //recorrer el buffer
        BufferedImage bi = AbrirImagen.toBufferedImage(io); 
        BufferedImage bNuevo = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB); 
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                int rgb = calcularNuevoTono(x, y, bi, mascara, div, offset);//x, y es el punto en el que nos encontramos
                bNuevo.setRGB(x, y, rgb);
            }
        }
        return AbrirImagen.toImage(bNuevo); 
    }
    
    private static int calcularNuevoTono(int x, int y, BufferedImage bi, int[][] mascara, int div, int offset) {
        int auxRojito=0, auxGreen = 0, auxBlue =0;//acumular
        Color col = null;
        for(int i=0, r = x-1; i<mascara.length; i++,r++){
            for(int j=0, c = y-1; j<mascara[0].length;j++, c++){
                   try{
                       //konta++; 
                       int rgb = bi.getRGB(r, c);         
                       col =  new Color(rgb); 
                       auxRojito += col.getRed() * mascara[i][j]; //multiplicar por la máscara
                       auxGreen += col.getGreen()*mascara[i][i]; 
                       auxBlue += col.getBlue()*mascara[j][i];  
                   } catch(Exception e){
                        
                   }
                }
            
        }
        auxRojito/=div;
        auxGreen/=div;
        auxBlue/=div;
        col =  new Color(validarLimites(auxRojito+offset), validarLimites(auxGreen+offset), validarLimites(auxBlue+offset)); // o se puede instanciar aca
        return col.getRGB(); 
    }
    // utilizando una imagen original a escala de grises, 
    //se le aplicará el operador Sobel obteniendo un gradiente que en este caso 
    //para la implementacion se utilizará la convolucion ya existente. 
    public static Image sobel(Image io){
        Image GX = convolucion(io, DeteccionBordes.sobelx(),1,0);
        Image GY = convolucion(io, DeteccionBordes.sobely(),1,0);
        BufferedImage bGX = AbrirImagen.toBufferedImage(GX);
        BufferedImage bGY = AbrirImagen.toBufferedImage(GY);
        BufferedImage buffer = new BufferedImage(bGX.getWidth(), bGX.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < bGX.getWidth(); i++){
            for(int j = 0; j < bGX.getHeight(); j++){
                buffer.setRGB(i,j,bGX.getRGB(i,j)+bGY.getRGB(i,j));
            }
        }
        return  AbrirImagen.toImage(buffer);
    }
    
    public static Image prewitt(Image io){//el operador prewitt es similar al sobel dado que
        //tambien utiliza la obtencion de un gradiente para los bordes la diferecia es que 
        //utiliza una máscara diferente 
        Image GX = convolucion(io, DeteccionBordes.prewittx(),1,0);
        Image GY = convolucion(io, DeteccionBordes.prewitty(),1,0);
        BufferedImage bGX = AbrirImagen.toBufferedImage(GX);
        BufferedImage bGY = AbrirImagen.toBufferedImage(GY);
        BufferedImage buffer = new BufferedImage(bGX.getWidth(), bGX.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < bGX.getWidth(); i++){
            for(int j = 0; j < bGX.getHeight(); j++){
                buffer.setRGB(i,j,bGX.getRGB(i,j)+bGY.getRGB(i,j));
            }
        }
        return  AbrirImagen.toImage(buffer);
    }    
    
    //El operador de Roberts a diferencia de los dos anteriores, 
    //solo marca dos puntos en cada mascara, 
    //es más simple y funciona bastante bien para imágenes binarias
     public static Image roberts(Image io){
        Image GX = convolucion(io, DeteccionBordes.robertsX(),1,0);
        Image GY = convolucion(io, DeteccionBordes.robertsY(),1,0);
        BufferedImage bGX = AbrirImagen.toBufferedImage(GX);
        BufferedImage bGY = AbrirImagen.toBufferedImage(GY);
        BufferedImage buffer = new BufferedImage(bGX.getWidth(), bGX.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < bGX.getWidth(); i++){
            for(int j = 0; j < bGX.getHeight(); j++){
                buffer.setRGB(i,j,bGX.getRGB(i,j)+bGY.getRGB(i,j));
            }
        }
        return  AbrirImagen.toImage(buffer);
    }
    public static int validarLimites (int aux){
        if (aux<0)return 0;
        if (aux>255)return 255;
        return aux;
    }
}
