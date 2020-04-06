/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalisisEspacial;

/**
 *
 * @author karen alvarado
 */
public class DeteccionBordes {
    
    public static int[][] sobelx(){
        int [][] sobel1 =  new int[][] {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}}; 
        return sobel1; 
    }
    public static int[][] sobely(){
       int [][] sobel2 =  new int[][] {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}}; 
        return sobel2; 
    }
    
    public static int[][] prewittx(){
        int [][] prewitt1 =  new int[][] {{-1, 0, 1}, {-1, 0, 1}, {-1, 0, 1}}; 
        return prewitt1; 
    }
    public static int[][] prewitty(){
       int [][] prewitt2 =  new int[][] {{1, 1, 1}, {0, 0, 0}, {-1, -1, -1}}; 
        return prewitt2; 
    }
    
    public static int[][] robertsX(){
       int [][] roberts1 =  new int[][] {{-1, 0, 0}, {0, 1, 0}, {0,0,0}}; 
        return roberts1; 
    }
    public static int[][] robertsY(){
       int [][] roberts1 =  new int[][] {{0, 0, 0}, {0, 1, 0}, {0,0,-1}}; 
        return roberts1; 
    }
}
