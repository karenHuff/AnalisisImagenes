/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalisisEspacial;
import analisisImagenes.AbrirImagen;
import GUI.JFrameImagen;
import java.awt.Image;

/**
 *
 * @author karen alvarado
 */
public class main {
    public static void main(String args[]){
        /*Image imagen = AbrirImagen.openImage();
        JFrameImagen frame = new JFrameImagen(imagen);
        frame.setTitle("ImagenOriaginal");
        //imagen iluminada
        Image imagenI = FiltrosEspaciales.iluminarImagen(imagen, 70);
        JFrameImagen frame2 = new JFrameImagen(imagenI);
        //imagen sin iluminacion
        Image imagenIl = FiltrosEspaciales.iluminarImagen(imagen, -30);
        JFrameImagen frame3 = new JFrameImagen(imagenIl);
        //imagen calida
        Image imagenC = FiltrosEspaciales.iluminarImagen(imagen, 40);
        JFrameImagen frame4 = new JFrameImagen(imagenC);
        //imagen fria
        Image imagenF = FiltrosEspaciales.iluminarImagen(imagen, -20);
        JFrameImagen frame5 = new JFrameImagen(imagenF);
        
        //histogramas
        Histogramas hi = new Histogramas(imagenI); 
        Histogramas h2 = new Histogramas(imagenIl); 
        Histogramas h3 = new Histogramas(imagenC); 
        Histogramas h4 = new Histogramas(imagenF); */
        
        Image imagen = AbrirImagen.openImage();
        Histogramas h = new  Histogramas(imagen);
        h.graficarHistogramas();
        JFrameImagen fi = new JFrameImagen(imagen);
        Image contraste = FiltrosEspaciales.expansionLineal(h, imagen);
        JFrameImagen fic = new JFrameImagen(contraste);
        Histogramas h1 = new  Histogramas(contraste);
        h1.graficarHistogramas();
        
        
        System.out.println();
    }
 
}
