/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisImagenes;
//visual estudio code 
import AnalisisEspacial.Convolucion;
import AnalisisEspacial.DeteccionBordes;
import AnalisisEspacial.FiltrosEspaciales;
import GUI.JFrameImagen;
import AnalisisEspacial.Histogramas;
import AnalisisEspacial.Suavizado;
import GUI.JFrameSegmentacion;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Image;
import static javafx.scene.paint.Color.color; 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author karen alvarado
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Image imagen = AbrirImagen.openImage();
        JFrameImagen frame = new JFrameImagen(imagen);
        frame.setTitle("Imagen original");
        Histogramas hi = new Histogramas(imagen);
        hi.graficarHistogramas();
        
        
        /*Image solbel = Convolucion.sobel(imagen);
        JFrameImagen fram = new JFrameImagen(solbel);
        fram.setTitle("Imagen resultante");
        Histogramas h2 = new Histogramas(solbel);
        h2.graficarHistogramas();*/
        
        //prewitt
        Image prewitt = Convolucion.prewitt(imagen);
        JFrameImagen fram = new JFrameImagen(prewitt);
        fram.setTitle("Imagen resultante");
        Histogramas h2 = new Histogramas(prewitt);
        h2.graficarHistogramas();
        
        /*Image roberts = Convolucion.roberts(imagen);
        JFrameImagen fram = new JFrameImagen(roberts);
        fram.setTitle("Imagen resultante");
        Histogramas h2 = new Histogramas(roberts);
        h2.graficarHistogramas();*/
    }
    //tarea: trazar un radio constante y dibujar un circulo 
    //utilizando buffered o Image
    // del colo que sea, no importa, o si quiere un <3
}
        /*Image ruido = Suavizado.agregarRuidoSustractivo(imagen, 50); //
        JFrameImagen frame1 = new JFrameImagen(ruido); //
        Histogramas h1 = new Histogramas(ruido); //tarea: agregar titulo
        h1.graficarHistogramas()*/

/*Image imagenS = FiltrosEspaciales.generarImagenGrises(imagen);        
        JFrameImagen frame2 = new JFrameImagen(imagenS); //
        frame.setTitle("Imagen resultante");//
        Histogramas hi = new Histogramas(imagenS); 
        hi.graficarHistogramaGrises();
        
        int u = FiltrosEspaciales.metodoIterativo(hi.getHRed()); 
        Image bi = FiltrosEspaciales.binarizacion(imagen, u); 
        JFrameImagen bin1 =  new JFrameImagen(bi); 
        Histogramas h3 =  new Histogramas(bi); 
        h3.graficarHistogramas();*/
        //        metodoIterativo(hi); 
        
        
        /*JFrameImagen f2 = new JFrameImagen(imagenS); 
        hi = new Histogramas(imagenS); 
        hi.graficarHistogramas();*/
        //JFrameSegmentacion f = new JFrameSegmentacion("Segmemntacion", imagen)
        
        /*
        //int rgb = bi.getRGB(512, 342);
        Color co = new Color(255,0,0); 
        //bi.setRGB(50, 50, co.getRGB());
        int centroX = (int)bi.getWidth()/2; 
        int centroY = (int)bi.getHeight()/2; 
        int radio=25; 
        //int radio2=12; 
        for(int x = centroX-radio; x<=centroX+radio; x++){
            bi.setRGB(x, (int)Math.sqrt(Math.pow(radio, 2)-Math.pow(x-centroX, 2)) +centroY, co.getRGB()); 
            bi.setRGB(x, -(int)Math.sqrt(Math.pow(radio, 2)-Math.pow(x-centroX, 2)) +centroY, co.getRGB());
        }
        //circlo relleno
        /*for(int i= centroX-radio2; i<= centroX+radio2; i++){
            for(int j= centroY-radio2; j<=centroY+radio2; j++){
                if((Math.pow(i-centroX, 2)+Math.pow(j-centroY, 2))<Math.pow(radio2, 2))
                    bi.setRGB(i, j, co.getRGB());        
            }
        }
       
        Color red = new Color(255); 
        Color green = new Color(255); 
        Color blue= new Color(255); 
               
        JFrameImagen fame2 = new JFrameImagen(AbrirImagen.toImage(bi)); 
        //hacer un JFrame 
        System.out.println();*/
//investigar otro método para el calculo del umbral  e implementarlo 
