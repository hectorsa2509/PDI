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
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class LuzNegra extends Filtro{
    

    private static final int a = 2;
    

    public LuzNegra(Image img){
        super(img);
    }
    
    /**
     * Metodo que saca una imagen con efecto blacklight
     *
     */
    public Image filtroLuzNegra(){
        Filtro.PROGRESO = 0;
        int red,green,blue,blacklight, rojoRGB,verdeRGB,azulRGB;
        WritableImage imagenD = new WritableImage(this.getAnchura(), this.getAltura());
        PixelWriter pixelD = imagenD.getPixelWriter();
        PixelReader pixelI = this.getImagen().getPixelReader();
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                Color colorOriginal = pixelI.getColor(i, j);
                rojoRGB = (int) (colorOriginal.getRed()*255);
                verdeRGB = (int) (colorOriginal.getGreen()*255);
                azulRGB = (int) (colorOriginal.getBlue()*255);
                blacklight = ((rojoRGB+verdeRGB+azulRGB)/3);
                //Para cada color es:
                //Color = |color-blacklight|*alpha
                red = (Math.abs(rojoRGB-blacklight)*a);
                green = (Math.abs(verdeRGB-blacklight)*a);
                blue = (Math.abs(azulRGB-blacklight)+a);
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);                
                pixelD.setColor(i, j, Color.rgb(red, green, blue));
                Filtro.PROGRESO = (this.avanzar()/this.getTotal());
            }
        }
        return imagenD;
    }
} 