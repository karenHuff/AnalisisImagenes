/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import analisisImagenes.AbrirImagen;
import AnalisisEspacial.Histogramas; 
import GUI.listeners.SegmentacionListener;
import javax.swing.*; 
import java.awt.*; 
import java.awt.image.BufferedImage;


/**
 *
 * @author karen alvarado
 */
public class JFrameSegmentacion extends JFrame{
    private JSlider slideru1, slideru2; 
    private JLabel labelImagen; 
    private JButton btnAbrir; 
    private Image imagenEscalada; 
    
    
    public JFrameSegmentacion(String title, Image io){
        setTitle(title); 
        int ancho = io.getWidth(null)/2; 
        int alto = io.getHeight(null)/2; 
        setSize(ancho,alto);
        this.imagenEscalada = AbrirImagen.toBufferedImage(io).getScaledInstance(ancho,alto, BufferedImage.TYPE_INT_RGB);
        initcomponents();
        setVisible(true);
        
    }
    
    private void initcomponents(){
        //layout
        setLayout(new GridLayout(4,1)); 
        this.labelImagen = new JLabel(new ImageIcon(this.imagenEscalada));
        add(this.labelImagen); 
        this.slideru1 = new JSlider(); 
        this.slideru1.setMinimum(0);
        this.slideru1.setMaximum(255);
        this.slideru1.setValue(0); 
        this.slideru1.setPaintLabels(true);
        this.slideru1.setPaintTicks(true);
        this.slideru1.setMinorTickSpacing(1);
        this.slideru1.setMajorTickSpacing(25);
       
        this.slideru2 = new JSlider(); 
        this.slideru2.setPaintLabels(true);
        this.slideru2.setPaintTicks(true);
        this.slideru2.setMinorTickSpacing(1);
        this.slideru2.setMajorTickSpacing(25);
        this.slideru2.setMinimum(0);
        this.slideru2.setMaximum(255);
        this.slideru2.setValue(255); 
        
        //modificar el escuchador de los slider
        add(this.slideru1); 
        add(this.slideru2); 
        this.btnAbrir = new JButton("Segmentar"); 
        SegmentacionListener lis = new SegmentacionListener(this);
        this.btnAbrir.addActionListener(lis);
        add(this.btnAbrir); 
    }
    
    public JSlider getJSliderU1(){
        return this.slideru1;
    }
    public JSlider getJSliderU2(){
        return this.slideru2;
    }
    public JLabel getLabelImagen(){

       return  this.labelImagen;
    }

    public Image getImagenEscalada(){

        return  this.imagenEscalada;
     }

    
}
