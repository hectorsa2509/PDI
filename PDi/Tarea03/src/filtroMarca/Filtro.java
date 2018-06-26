
package filtroMarca;

import javafx.scene.image.Image;

/**
 * 
 * @author hectorsama.
 */
public class Filtro {
        
    private Image imagen;
    private int anchura;
    private int altura;
    
    /**
     * Construye un filtro con una imagen y sus dimensiones.
     * 
     * @param imagen Original.
     */
    public Filtro(Image imagen){
        this.imagen = imagen;
        this.anchura = (int) imagen.getWidth();
        this.altura = (int) imagen.getHeight();        
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public int getAnchura() {
        return anchura;
    }

    public void setAnchura(int anchura) {
        this.anchura = anchura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

}
