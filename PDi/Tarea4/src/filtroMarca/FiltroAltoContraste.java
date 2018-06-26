
package filtroMarca;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Permite aplicar un filtro de alto contraste a una imágen.
 * 
 * @author hectorsama.
 */
public class FiltroAltoContraste extends Filtro{
    
    /**
     * Construye un filtro de alto contraste para ser aplicado despues a una imágen.
     * 
     * @param imagen es a la cual se le aplicara el filtro.
     */
    public FiltroAltoContraste(Image imagen) {
        super(imagen);
    }
    
    /**
     * Aplica el filtro alto contraste a la imágen dada.
     * 
     * @return Imágen con el filtro alto contraste aplicado.
     */
    public Image getImagenAltoContraste(){        
        int rojo, verde, azul;
        WritableImage nuevaImagen = new WritableImage(this.getAnchura(), 
                this.getAltura());
        PixelWriter pixelNuevaImagen = nuevaImagen.getPixelWriter();
        PixelReader pixelImagenOriginal = this.getImagen().getPixelReader();
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                Color colorOriginal = pixelImagenOriginal.getColor(i, j);
                rojo = (int) (colorOriginal.getRed()*255);
                verde = (int) (colorOriginal.getGreen()*255);
                azul = (int) (colorOriginal.getBlue()*255);
                //Si el promedio es mayor que 127 cambia a blanco.
                if( ((rojo + verde + azul)/3) > 127 ){
                    pixelNuevaImagen.setColor(i, j, Color.rgb(255, 255, 255));
                }else{
                    pixelNuevaImagen.setColor(i, j, Color.rgb(0, 0, 0));
                }                
            }
        }
        return nuevaImagen;
    }    
    
}
