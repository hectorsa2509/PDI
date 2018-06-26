/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 *
 * @author hectorsama
 */
public class Filtros {

    private BufferedImage imagen;

    private BufferedImage original;
    private BufferedImage img = null;

    public BufferedImage cargarImagen() {
   

        //Abrir venata para buscar imagen
        JFileChooser search = new JFileChooser();
        //Titulo de la ventana
        search.setDialogTitle("Cargar Imagen");
        FileNameExtensionFilter fne = new FileNameExtensionFilter("JPG & GIF & BMP",
                "jpg", "gif", "bmp");
        search.setFileFilter(fne);
        int dialog = search.showOpenDialog(null);

        if (dialog == JFileChooser.APPROVE_OPTION) {

            //Leemos la imagen
            try {
                File archivo = search.getSelectedFile();
                img = ImageIO.read(archivo);
            } catch (Exception e) {
                System.out.println("Error ");
            }

        }
        imagen = img;
        return img;

    }

    public BufferedImage copia() {
        BufferedImage copia = new BufferedImage(img.getWidth(),
                img.getHeight(), BufferedImage.TYPE_INT_BGR);
        Graphics g = copia.createGraphics();
        g.drawImage(img, 0, 0, null);
        imagen = copia;
        return copia;
    }

    /*
    Filtro Rojo
    Recorremos la imagen y vamos aplicando el color rojo a cada uno de los pixeles
    de la imagen.
     */
    public BufferedImage rojo() {

        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                int aux = imagen.getRGB(i, j);
                int rojo = (aux & 0xFF0000) >> 16;
                aux = rojo << 16;

                imagen.setRGB(i, j, aux);
            }
        }

        return imagen;
    }

    /*
    Filtro Verde
    Recorremos la imagen y vamos aplicando el color verde a cada uno de los pixeles
    de la imagen.
     */

    public BufferedImage verde() {

        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                int aux = imagen.getRGB(i, j);
                int rojo = (aux & 0xFF0000) >> 16;
                aux = rojo << 8;
                imagen.setRGB(i, j, aux);
            }
        }

        return imagen;
    }

    /*
    Filtro Azul
    Recorremos la imagen y vamos aplicando el color azul a cada uno de los pixeles
    de la imagen.
     */

    public BufferedImage azul() {

        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                int aux = imagen.getRGB(i, j);
                int blue = aux & 0xFF;
                aux = blue;
                imagen.setRGB(i, j, aux);
            }
        }

        return imagen;
    }

    /*
    Filtro Gris
    Recorremos la imagen y vamos aplicando el color gris a cada uno de los pixeles
    de la imagen.
     */
    public BufferedImage gris() {
        int aux;
        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                Color c = new Color(this.imagen.getRGB(i, j));
                aux = (int) ((c.getRed() + c.getGreen() + c.getBlue()) / 3);
                int p = (aux << 16) | (aux << 8) | aux;

                imagen.setRGB(i, j, p);
            }
        }

        return imagen;
    }

    /*
    Filtro Gris
    Recorremos la imagen y vamos aplicando el color gris a cada uno de los pixeles
    de la imagen.
     */
    public BufferedImage grsi1() {

        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                Color c = new Color(this.imagen.getRGB(i, j));
                int gray = (int) (c.getRed() * 0.3 + c.getGreen() * 0.59 + c.getBlue() * 0.11);
                int p = (gray << 24) | (gray << 16) | (gray << 8) | gray;

                imagen.setRGB(i, j, p);
            }
        }

        return imagen;
    }

    /*
    Filtro Gris
    Recorremos la imagen y vamos aplicando el color gris a cada uno de los pixeles
    de la imagen. 
     */
    public BufferedImage gris2() {
        int aux;
        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                Color c = new Color(this.imagen.getRGB(i, j));
                aux = (int) ((c.getRed() + c.getGreen()) / 2);
                int p = (aux << 16) | (aux << 8) | aux;
                imagen.setRGB(i, j, p);
            }
        }

        return imagen;
    }

    /*
    Filtro Gris
    Recorremos la imagen y vamos aplicando el color gris a cada uno de los pixeles
    de la imagen. 
     */
    public BufferedImage gris3() {
        int aux;
        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                Color c = new Color(this.imagen.getRGB(i, j));
                aux = (int) ((c.getRed() + c.getBlue()) / 2);
                int p = (aux << 16) | (aux << 8) | aux;
                imagen.setRGB(i, j, aux);
            }
        }

        return imagen;
    }

    /*
    Filtro Gris
    Recorremos la imagen y vamos aplicando el color gris a cada uno de los pixeles
    de la imagen. En este caso (b+g)/2
     */
    public BufferedImage gris4() {
        int aux;
        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                Color c = new Color(this.imagen.getRGB(i, j));
                aux = (int) ((c.getBlue() + c.getGreen()) / 2);
                int p = (aux << 16) | (aux << 8) | aux;
                imagen.setRGB(i, j, p);
            }
        }

        return imagen;
    }

    /*
    Filtro Brillo
    Recorremos la imagen y vamos aplicando brillo a cada uno de los pixeles
    de la imagen.
     */
    public BufferedImage brillo(int k) {
        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                Color aux = new Color(imagen.getRGB(i, j));
                int r = aux.getRed() + k;
                int g = aux.getGreen() + k;
                int b = aux.getBlue() + k;
                r = nivel(r);
                g = nivel(g);
                b = nivel(b);
                Color color_nuevo = new Color(r, g, b);
                imagen.setRGB(i, j, color_nuevo.getRGB());
            }
        }
        return imagen;
    }

    /*
    Filtro Inverso
     */
    public BufferedImage inverso() {
        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                Color aux = new Color(imagen.getRGB(i, j));
                int promedio = (aux.getRed() + aux.getGreen() + aux.getBlue()) / 3;
                if (promedio < 127) {
                    aux = new Color(255, 255, 255);
                } else {
                    aux = new Color(0, 0, 0);
                }
                imagen.setRGB(i, j, aux.getRGB());
            }
        }
        return imagen;
    }

    /**
     * Filtro que aplica el filtro de alto constraste a una imagen
     */
    public BufferedImage altoContraste() {
        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                Color aux = new Color(imagen.getRGB(i, j));
                int promedio = (aux.getRed() + aux.getGreen() + aux.getBlue()) / 3;
                if (promedio < 127) {
                    aux = new Color(0, 0, 0);
                } else {
                    aux = new Color(255, 255, 255);
                }
                imagen.setRGB(i, j, aux.getRGB());
            }
        }
        return imagen;
    }
    
    
       
     //Metodo que aplica el filtro de convolucion a una imagen.

    public BufferedImage convolucion(double[][] filter, double factor,
            double bias) {
        BufferedImage nueva = new BufferedImage(imagen.getWidth(),
                imagen.getHeight(), imagen.getType());
        int w = imagen.getWidth();
        int h = imagen.getHeight();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                double red = 0.0;
                double green = 0.0;
                double blue = 0.0;
                for (int filterX = 0; filterX < filter.length; filterX++) {
                    for (int filterY = 0; filterY < filter[0].length; filterY++) {
                        int imageX = (x - filter.length / 2 + filterX + w) % w;
                        int imageY = (y - filter[0].length / 2 + filterY + h) % h;
                        Color aux = new Color(imagen.getRGB(imageX, imageY));
                        red += aux.getRed() * filter[filterX][filterY];
                        green += aux.getGreen() * filter[filterX][filterY];
                        blue += aux.getBlue() * filter[filterX][filterY];
                    }
                }
                int r = Math.min(Math.max((int) (factor * red + bias), 0), 255);
                int g = Math.min(Math.max((int) (factor * green + bias), 0), 255);
                int b = Math.min(Math.max((int) (factor * blue + bias), 0), 255);
                Color nuevo = new Color(r, g, b);
                nueva.setRGB(x, y, nuevo.getRGB());
            }
        }
        imagen = nueva;
        return imagen;
    }
    
    
        /**
     * Método que aplica el filtro de blur.
     *
     * @return Imagen con el filtro de blur.
     */
    public BufferedImage blur() {
        double[][] filter = {{0.0, 0.2, 0.0}, {0.2, 0.2, 0.2}, {0.0, 0.2, 0.0}};
        return convolucion(filter, 1.0, 0.0);
    }
    
    
       
     //Método que aplica el filtro de motionBlur 

    public BufferedImage motionBlur() {
        double[][] filter = new double[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j) {
                    filter[i][j] = 1.0;
                } else {
                    filter[i][j] = 0.0;
                }
            }
        }
        return convolucion(filter, 1.0 / 9.0, 0.0);
    }
    
      
    // Método que aplica el filtro de borde.
     
        public BufferedImage borde() {
        double[][] filter = {{0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {-1, -1, 2, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}};
        return convolucion(filter, 1.0, 0.0);
    }
        
    /*
    Metodo Emboss 
    */
    public BufferedImage emboss(){
        double [][] matriz ={ {-1,-1, 0},
                          {-1, 0, 1},
                          { 0, 1, 1}};
        return auxConvulcion(matriz,1.0,128.0);
    }
    
    
        //Método que aplica el filtro de sharpen.
    
    public BufferedImage sharpen() {
        double[][] filter = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};
        return convolucion(filter, 1.0, 0.0);
    }
    
    //Metodo para tener una acota del nivel de brillo

    private int nivel(int x) {
        if (x < 0) {
            return 0;
        }
        if (x > 255) {
            return 255;
        }
        return x;
    }
    
    
        /*
    Metodo auxiliar para la convolucion
    */
    public BufferedImage auxConvulcion(double[][] matriz, double fr, double bl){
        try{
            int width = imagen.getWidth();
            int height = imagen.getHeight();
            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            for(int i = 0; i < width; i++){
              for(int j = 0; j <  height; j++){
                Color c = null;
                double red = 0;
                double green = 0;
                double blue = 0;
                int matriz_i = 0;
                int matriz_j = 0;
                int matriz_pixel = (int)matriz.length/2;
                for(int k = i-matriz_pixel;k<=i+matriz_pixel;k++){
                  for(int l = j-matriz_pixel;l<=j+matriz_pixel;l++){
                    int rgb = 0;
                    try{
                      rgb = imagen.getRGB(k,l);
                    }catch (Exception e) {
                        System.out.println(e);
                    }
                    Color color = new Color(rgb);
                    red += color.getRed()*matriz[matriz_i][matriz_j];
                    green += color.getGreen()*matriz[matriz_i][matriz_j];
                    blue += color.getBlue()*matriz[matriz_i][matriz_j];
                    matriz_j++;
                  }
                  matriz_j = 0;
                  matriz_i++;
                }
                c = new Color(nivel((int)(fr*red+bl)),nivel((int)(fr*green+bl)),nivel((int)(fr*blue+bl)));
                bufferedImage.setRGB(i,j,c.getRGB());
              }
          }
          return bufferedImage;
        }catch (Exception e) {
         
        }
        return null;
        }
    
    
       public BufferedImage getImagenOleo(){        
        int anchura,altura;
        //Cantidad de vecindades que se visitan.        
        anchura = altura = radio;
        Map<Double,Integer> frecuenciaR = new HashMap<>();
        Map<Double,Integer> frecuenciaG = new HashMap<>();
        Map<Double,Integer> frecuenciaB = new HashMap<>();
        int terminoX, terminoY;
        double red, green, blue;
        WritableImage nuevaImagen = new WritableImage(this.getAnchura(), this.getAltura());
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
                        //Si es la primera vez que se lee un pixel se sustituye
                        //en los valores por default.
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
