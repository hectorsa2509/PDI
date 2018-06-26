package filtroMarca;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author  hectorsama.
 */
public class FiltroMarcaAgua extends Filtro{

    // Representa a cada pixel de una imagen.
    private static Color pixel;
    // Representa a cada componente de un pixel.
    private static double r, g, b, rGris, gGris, bGris, rContraste,
            gContraste, bContraste;    
    // Nos permite crear un "lienzo" para colocar la imagen procesada.
    private static WritableImage imagenProcesada;
    // Nos permite leer los pixeles de una imagen.
    private static PixelReader pixelReader;
    // Nos permite escribir sobre un lienzo.
    private static PixelWriter pixelWriter;

    /**
     * Construye un filtro que permitia quitar la marca de agua lo más posible.
     * 
     * @param imagen A la cual se le quitara la marca de agua.
     */
    public FiltroMarcaAgua(Image imagen) {
        super(imagen);
    }
    
    /**
     * Metodo que INTENTA eliminar las marcas de agua.
     *
     * @return La imagen sin la marca de agua.
     */
    public Image quitarMarcaAgua() {        
        // Creamos un "lienzo" del mismo tamanio de la imagen pasada como parametro.
        imagenProcesada = new WritableImage(this.getAnchura(), this.getAltura());
        // Nos permite leer los pixeles de la imagen pasada como parametro.
        pixelReader = this.getImagen().getPixelReader();
        // Nos permite escribir sobre el lienzo.
        pixelWriter = imagenProcesada.getPixelWriter();

        // Reconocemos la posicion de la marca de agua.
        PixelReader imagenRevelada = filtro_revelador(pixelReader, this.getAnchura(), 
                this.getAltura()).getPixelReader();
        // Procesamos la imagen con el filtro tonos de gris [R, R, R] (Funcionaba mejor que otros).
        //Nos servira de referencia.        
        PixelReader imagenTonosGris = new FiltroTonosGris(
                this.getImagen()).getImagenGrisesColor(0).getPixelReader();
        // Procesamos la imagen con el filtro alto contraste, con un ajuste de brillo de -3. Nos servira de referencia.       
        PixelReader imagenAltoContraste = new FiltroAltoContraste(
                this.getImagen()).getImagenAltoContraste().getPixelReader();

        // Recorremos los pixeles de la imagen revelada.
        for (int x = 0; x < this.getAnchura(); x++) {
            for (int y = 0; y < this.getAltura(); y++) {
                pixel = imagenRevelada.getColor(x, y);
                // Obtenemos los componentes de cada pixel.
                r = pixel.getRed() * 255;
                g = pixel.getGreen() * 255;
                b = pixel.getBlue() * 255;
                // Si son tonos obscuros los ignoramos (No se tratan de la marca de agua).
                if (r < 11 && b < 11 && g < 11) {
                    pixelWriter.setColor(x, y, pixelReader.getColor(x, y));
                } // Si son claros si nos interesan.
                else {
                    // Recuperamos los componentes del pixel de la imagen en tonos de gris.
                    rGris = imagenTonosGris.getColor(x, y).getRed();
                    gGris = imagenTonosGris.getColor(x, y).getGreen();
                    bGris = imagenTonosGris.getColor(x, y).getBlue();
                    // Recuperamos los componente de la imagen con alto contraste.
                    rContraste = imagenAltoContraste.getColor(x, y).getRed();
                    gContraste = imagenAltoContraste.getColor(x, y).getGreen();
                    bContraste = imagenAltoContraste.getColor(x, y).getBlue();
                    // Procuramos preservar los pixeles blancos del mismo color.
                    if ((rGris < rContraste) && (gGris < gContraste) && (bGris < bContraste)) {
                        pixelWriter.setColor(x, y, imagenAltoContraste.getColor(x, y));
                    } // Los otros los rellenamos con el color en gris.
                    else {
                        pixelWriter.setColor(x, y, imagenTonosGris.getColor(x, y));
                    }
                }
            }
        }
        return imagenProcesada;
    }

    /*
     * Metodo auxiliar que nos ayuda a resaltar la marca de agua de una imagen.
     */
    private static Image filtro_revelador(PixelReader pixelReader, int anchura, 
            int altura) {
        // Creamos un "lienzo" para colocar la imagen revelada..
        WritableImage imagenRevelada = new WritableImage(anchura, altura);
        // Nos permite escribir sobre un lienzo.
        PixelWriter pWriter = imagenRevelada.getPixelWriter();

        // Recorremos pixel por pixel.
        for (int x = 0; x < anchura; x++) {
            for (int y = 0; y < altura; y++) {
                pixel = pixelReader.getColor(x, y);
                // Como las carcas de agua de estas imagenes son de color rojo,
                // entonces solo nos interesa reconocer los pixeles con un tono rojizo.
                r = 0;
                g = Math.abs(pixel.getGreen() - pixel.getRed());
                b = Math.abs(pixel.getBlue() - pixel.getRed());
                pWriter.setColor(x, y, Color.color(r, g, b));
            }
        }
        return imagenRevelada;
    }
    
}
