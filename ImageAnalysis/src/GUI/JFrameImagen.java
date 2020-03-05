/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author karen alvarado
 */
public class JFrameImagen extends JFrame{
    private Image imagenOriginal; 
    
    public JFrameImagen(Image aux){
        this.imagenOriginal=aux; 
        JLabel etiqueta = new JLabel(); 
        etiqueta.setIcon(new ImageIcon(this.imagenOriginal)); 
        add(etiqueta); 
        setSize(this.imagenOriginal.getWidth(null), this.imagenOriginal.getHeight(null)); 
        setVisible(true); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
    }
    
    public Image getImagenOriginal(){
        return this.imagenOriginal; 
    }
    
    
    public void setImagenOriginal(Image imagenOriginal) {
        this.imagenOriginal = imagenOriginal;
    }
}
