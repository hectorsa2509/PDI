/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtroMarca;

/**
 *
 * @author hectorsama
 */
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FiltroOleo extends Filtro{
    
    private static final int radio = 7;
    
    /**
     * Construye un filtro oleo.
     * 
     * @param imagen a la cual se le aplicara el filtro.
     */
    public FiltroOleo(Image imagen) {
        super(imagen);
    }

    public Image ImagenOleo(){        
        int anchura,altura;
        
        anchura = altura = radio;
        Map<Double,Integer> frecuenciaR = new HashMap<>();
        Map<Double,Integer> frecuenciaG = new HashMap<>();
        Map<Double,Integer> frecuenciaB = new HashMap<>();
        int terminoX, terminoY;
        double red, green, blue;
        WritableImage nuevaImagen = new WritableImage(this.getAnchura(), 
                this.getAltura());
        PixelWriter nuevoPixel = nuevaImagen.getPixelWriter();
        PixelReader pixelOriginal = this.getImagen().getPixelReader();
        for (int i = 0; i < this.getAnchura(); i++) {
            terminoX = i + anchura;
            for (int j = 0; j < this.getAltura(); j++) {
                terminoY = j + altura;                
                double maxR = Double.NEGATIVE_INFINITY;
                double maxG = Double.NEGATIVE_INFINITY;
                double maxB = Double.NEGATIVE_INFINITY;                      
                for (int k = i; k < terminoX; k++) {
                    if(k >= this.getAnchura()){
                        break;
                    }
                    for (int l = j; l < terminoY; l++) {
                        if(l >= this.getAltura()){
                            break;
                        }
                        Color colorOriginal = pixelOriginal.getColor(k, l);
     
                        if(maxR == Double.NEGATIVE_INFINITY){
                            maxR = colorOriginal.getRed();
                            maxG = colorOriginal.getGreen();
                            maxB = colorOriginal.getBlue();
                        }
                        //ROJO:
                        if(frecuenciaR.containsKey(colorOriginal.getRed())){
                            frecuenciaR.put(colorOriginal.getRed(), 
                                    frecuenciaR.get(colorOriginal.getRed())+1);                            
                            if(!(frecuenciaR.get(maxR) > 
                                    frecuenciaR.get(colorOriginal.getRed()))){
                                maxR = colorOriginal.getRed();                            
                            }
                        }else{
                            frecuenciaR.put(colorOriginal.getRed(), 1);
                        }
                        //VERDE:
                        if(frecuenciaG.containsKey(colorOriginal.getGreen())){
                            frecuenciaG.put(colorOriginal.getGreen(), 
                                    frecuenciaG.get(colorOriginal.getGreen())+1);
                            if(!(frecuenciaG.get(maxG) > 
                                    frecuenciaG.get(colorOriginal.getGreen()))){
                                maxG = colorOriginal.getGreen();
                            }
                        }else{
                            frecuenciaG.put(colorOriginal.getGreen(), 1);
                        }
                        //AZU:
                        if(frecuenciaB.containsKey(colorOriginal.getBlue())){
                            frecuenciaB.put(colorOriginal.getBlue(),
                                    frecuenciaB.get(colorOriginal.getBlue())+1);
                            if(!(frecuenciaB.get(maxB) > 
                                    frecuenciaB.get(colorOriginal.getBlue()))){
                                maxB = colorOriginal.getBlue();
                            }
                        }else{
                            frecuenciaB.put(colorOriginal.getBlue(), 1);
                        }
                    }
                }
                red = maxR;
                green = maxG;
                blue = maxB;
                frecuenciaR.clear();
                frecuenciaG.clear();
                frecuenciaB.clear();         
                nuevoPixel.setColor(i, j, Color.color(red, green, blue));               
            }           
        }        
        return nuevaImagen;
    }
    
}