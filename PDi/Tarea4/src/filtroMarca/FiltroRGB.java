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
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FiltroRGB extends Filtro{
    

    public FiltroRGB(Image imagen) {
        super(imagen);
    }
    

    public Image RGB(int rojo, int verde, int azul){
        int red,green,blue;
        WritableImage imagenD = new WritableImage(this.getAnchura(), this.getAltura());
        PixelWriter pixelD = imagenD.getPixelWriter();
        PixelReader pixelI = this.getImagen().getPixelReader();
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                Color colorOriginal = pixelI.getColor(i, j);
                red = (int) (colorOriginal.getRed()*255);
                green = (int) (colorOriginal.getGreen()*255);
                blue = (int) (colorOriginal.getBlue()*255);
                red += rojo;
                green += verde;
                blue += azul;
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);
                pixelD.setColor(i, j, Color.rgb(red, green, blue));
            }
        }
        return imagenD;
    }
    
    
    /**
     * Metodo que nos da el filtro verde.
     * @return la imagen con el filtro verde.
     */
    public Image verde(){
        WritableImage imagenD = new WritableImage(this.getAnchura(), this.getAltura());
        PixelReader pixelI = this.getImagen().getPixelReader();
        PixelWriter pixelD = imagenD.getPixelWriter();
        Color color;
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                color = pixelI.getColor(i, j);
                Color color2 = new Color(0, color.getGreen(), 0, color.getOpacity());
                pixelD.setColor(i, j, color2);
            }
        }
        return imagenD;
    }
    
    /**
     * Metodo que nos da el filtro azul.
     * @return la imagen con el filtro azul.
     */
    public Image azul(){
        WritableImage imagenD = new WritableImage(this.getAnchura(), this.getAltura());
        PixelReader pixelI = this.getImagen().getPixelReader();
        PixelWriter pixelD = imagenD.getPixelWriter();
        Color color;
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                color = pixelI.getColor(i, j);
                Color color2 = new Color(0, 0, color.getBlue(), color.getOpacity());
                pixelD.setColor(i, j, color2);
            }
        }
        return imagenD;
    }
    
    /**
     * Metodo que nos da el filtro rojo
     * @return la imagen con el filtro rojo.
     */
    public Image rojo(){
        WritableImage imagenD = new WritableImage(this.getAnchura(), this.getAltura());
        PixelReader pixelI = this.getImagen().getPixelReader();
        PixelWriter pixelD = imagenD.getPixelWriter();
        Color color;
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                color = pixelI.getColor(i, j);
                Color color2 = new Color(color.getRed(), 0, 0, color.getOpacity());
                pixelD.setColor(i, j, color2);
            }
        }
        return imagenD;
    }
    
    /**
     * Metodo que nos da el filtro azar
     * @return la imagen con el filtro azar.
     */
    public Image azar(){
        Random aleatorio = new Random();
        int r,g,b;
        WritableImage imagenD = new WritableImage(this.getAnchura(), this.getAltura());
        PixelWriter pixelD = imagenD.getPixelWriter();
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                r = aleatorio.nextInt(255);
                g = aleatorio.nextInt(255);
                b = aleatorio.nextInt(255);
                pixelD.setColor(i, j, Color.rgb(r, g, b));
            }
        }
        return imagenD;
    }
    
} //Fin de FiltroRGB.java