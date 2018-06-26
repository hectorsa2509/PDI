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

import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;


public class FiltroSepia extends Filtro{
    
    /**
     * Construye un filtro sepia para ser aplicado
     */
    public FiltroSepia(Image imagen) {
        super(imagen);
    }
    
    /**
     * Método que modifica los tonos sepia de la imagen. 
     */
    public Image getImagenSepia(int tonos) {
        Image filtroGrises = new FiltroTonosGris(getImagen()).getImagenGrisesPromedio();
        WritableImage imagenNueva = new WritableImage(this.getAnchura(), 
                this.getAltura());
        PixelWriter nuevoPixel = imagenNueva.getPixelWriter();
        PixelReader pixelOriginal = filtroGrises.getPixelReader();
        for(int i = 0; i < filtroGrises.getWidth(); i++){
            for(int j = 0; j < filtroGrises.getHeight(); j++){
                Color colorOriginal = pixelOriginal.getColor(i, j);
                int rojo = (int) (colorOriginal.getRed() * 2 * tonos);
                int verde = (int) (colorOriginal.getGreen() * tonos);
                rojo = acota(rojo);
                verde = acota(verde);                
                nuevoPixel.setColor(i, j, Color.rgb(rojo, verde, 
                        (int) colorOriginal.getBlue()));
            }
        }
        return imagenNueva;
    }
    
    /**
     * Método auxliar.
     */
    private int acota(int x) {
        if (x < 0)
            return 0;
        if (x > 255)
            return 255;
        return x;
    }
    
}
