package filtroMarca;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author hectorsama.
 * @url http://la-morsa.blogspot.mx/2012/03/un-estudio-informal-de-los-semitonos.html
 */
public class FiltroTonosGris extends Filtro {

    /**
     * Construe un filtro de gris.
     *
     * @param imagen a la cual se le aplicara el filtro.
     */
    public FiltroTonosGris(Image imagen) {
        super(imagen);
    }

    /**
     * Filtro de tonos de gris. Donde calcula el promedio de las componentes rgb
     *
     * @return Imágen con el filtro de escala de grises.
     */
    public Image getImagenGrisesPromedio() {
        WritableImage nuevaImagen = new WritableImage(this.getAnchura(),
                this.getAltura());
        PixelReader pixelImagenOriginal = this.getImagen().getPixelReader();
        PixelWriter pixelNuevaImagen = nuevaImagen.getPixelWriter();
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                Color colorOriginal = pixelImagenOriginal.getColor(i, j);
                double gris = ((colorOriginal.getBlue()
                        + colorOriginal.getGreen() + colorOriginal.getRed()) / 3);
                Color nuevoColor = new Color(gris, gris, gris,
                        colorOriginal.getOpacity());
                pixelNuevaImagen.setColor(i, j, nuevoColor);
            }
        }
        return nuevaImagen;
    }

    /**
     * Filtro de tonos de gris. Donde utiliza distintos porcentajes de rojo,
     * verde y azul fijos.
     *
     * @return Imágen con el filtro de escala de grises.
     */
    public Image getImagenGrisesPorcentaje() {
        WritableImage nuevaImagen = new WritableImage(this.getAnchura(),
                this.getAltura());
        PixelReader pixelImagenOriginal = this.getImagen().getPixelReader();
        PixelWriter pixelNuevaImagen = nuevaImagen.getPixelWriter();
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                Color colorOriginal = pixelImagenOriginal.getColor(i, j);
                double gris = (colorOriginal.getRed() * .3)
                        + (colorOriginal.getGreen() * .59)
                        + (colorOriginal.getBlue() * .11);
                Color nuevoColor = new Color(gris, gris, gris,
                        colorOriginal.getOpacity());
                pixelNuevaImagen.setColor(i, j, nuevoColor);
            }
        }
        return nuevaImagen;
    }

    /**
     * Filtro de tonos de gris. Donde obtiene el mínimo de las componentes rgb y
     * el máximo y los divide entre 2.
     *
     * @return Imágen con el filtro de escala de grises.
     */
    public Image getImagenGrisesDesaturacion() {
        double rojo, verde, azul;
        WritableImage nuevaImagen = new WritableImage(this.getAnchura(),
                this.getAltura());
        PixelReader pixelImagenOriginal = this.getImagen().getPixelReader();
        PixelWriter pixelNuevaImagen = nuevaImagen.getPixelWriter();
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                Color colorOriginal = pixelImagenOriginal.getColor(i, j);
                rojo = colorOriginal.getRed();
                verde = colorOriginal.getGreen();
                azul = colorOriginal.getBlue();
                double gris = (Math.max(Math.max(rojo, verde), azul)
                        + Math.min(Math.min(rojo, verde), azul)) / 2;
                Color nuevoColor = new Color(gris, gris, gris,
                        colorOriginal.getOpacity());
                pixelNuevaImagen.setColor(i, j, nuevoColor);
            }
        }
        return nuevaImagen;
    }

    /**
     * Filtro de tonos de gris. Donde calcula el máximo o mínimo de RGB según el
     * parámetro indicado.
     *
     * @param maximo True para que tome el valor más grande de los tres.
     * @return Imágen con el filtro de escala de grises.
     */
    public Image getImagenGrisesDescomposicion(boolean maximo) {
        double rojo, verde, azul;
        WritableImage nuevaImagen = new WritableImage(this.getAnchura(),
                this.getAltura());
        PixelReader pixelImagenOriginal = this.getImagen().getPixelReader();
        PixelWriter pixelNuevaImagen = nuevaImagen.getPixelWriter();
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                Color colorOriginal = pixelImagenOriginal.getColor(i, j);
                rojo = colorOriginal.getRed();
                verde = colorOriginal.getGreen();
                azul = colorOriginal.getBlue();
                double gris;
                if (maximo) {
                    gris = Math.max(Math.max(rojo, verde), azul);
                } else {
                    gris = Math.min(Math.min(rojo, verde), azul);
                }
                Color nuevoColor = new Color(gris, gris, gris,
                        colorOriginal.getOpacity());
                pixelNuevaImagen.setColor(i, j, nuevoColor);
            }
        }
        return nuevaImagen;
    }

    /**
     * Filtro de tonos de gris. Donde utiliza un número de color pasado como
     * parámetro.
     *
     * @param color 0 es a rojo, 1 es a verde y 2 es a azul.
     *
     * @return Imágen con el filtro de escala de grises.
     */
    public Image getImagenGrisesColor(int color) {
        WritableImage nuevaImagen = new WritableImage(this.getAnchura(),
                this.getAltura());
        PixelReader pixelImagenOriginal = this.getImagen().getPixelReader();
        PixelWriter pixelNuevaImagen = nuevaImagen.getPixelWriter();
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                Color colorOriginal = pixelImagenOriginal.getColor(i, j);
                double gris;
                switch (color) {
                    case 0:
                        gris = colorOriginal.getRed();
                        break;
                    case 1:
                        gris = colorOriginal.getGreen();
                        break;
                    default:
                        gris = colorOriginal.getBlue();
                        break;
                }
                Color nuevoColor = new Color(gris, gris, gris,
                        colorOriginal.getOpacity());
                pixelNuevaImagen.setColor(i, j, nuevoColor);
            }
        }
        return nuevaImagen;
    }

    /**
     * Filtro de tonos de gris. Donde utiliza un número de sombras.
     *
     * @param sombras Número de sombras (2 - 256).
     * @return Imágen con el filtro de escala de grises.
     */
    public Image getImagenGrisesSombras(int sombras) {
        int rojo, verde, azul;
        double conversion = 255 / (sombras - 1);
        WritableImage nuevaImagen = new WritableImage(this.getAnchura(),
                this.getAltura());
        PixelReader pixelImagenOriginal = this.getImagen().getPixelReader();
        PixelWriter pixelNuevaImagen = nuevaImagen.getPixelWriter();
        for (int i = 0; i < this.getAnchura(); i++) {
            for (int j = 0; j < this.getAltura(); j++) {
                Color color = pixelImagenOriginal.getColor(i, j);
                rojo = (int) (color.getRed() * 255);
                verde = (int) (color.getGreen() * 255);
                azul = (int) (color.getBlue() * 255);
                double promedio = (rojo + verde + azul) / 3;
                double gris = (int) ((promedio / conversion) + 0.5)
                        * conversion;
                gris = gris / 256;
                gris = Math.min(Math.max((gris), 0), 255);
                pixelNuevaImagen.setColor(i, j, Color.color(gris, gris, gris));
            }
        }
        return nuevaImagen;
    }

    /**
     * Filtro de tonos de gris. Donde utiliza un número de umbral.
     *
     * @param sombras Constante de la base (2 - 256).
     * @return Imágen con el filtro con escala de grises.
     */
    public Image getImagenGrisesDifuminado(int sombras) {
        int rojo, verde, azul;
        double conversion = 255 / (sombras - 1);
        WritableImage nuevaImagen = new WritableImage(this.getAnchura(),
                this.getAltura());
        PixelReader pixelImagenOriginal = this.getImagen().getPixelReader();
        PixelWriter pixelNuevaImagen = nuevaImagen.getPixelWriter();
        for (int i = 0; i < this.getAnchura(); i++) {
            double error = 0;
            for (int j = 0; j < this.getAltura(); j++) {
                Color color = pixelImagenOriginal.getColor(i, j);
                rojo = (int) (color.getRed() * 255);
                verde = (int) (color.getGreen() * 255);
                azul = (int) (color.getBlue() * 255);
                double promedio = (rojo + verde + azul) / 3;
                double gris = promedio + error;
                gris = (int) ((gris / conversion) + 0.5) * conversion;
                error = promedio + error - gris;
                gris = gris / 256;
                gris = Math.min(Math.max((gris), 0), 255);
                pixelNuevaImagen.setColor(i, j, Color.color(gris, gris, gris));
            }
        }
        return nuevaImagen;
    }

    /**
     * Filtro con cierto numero de grises con dithering.
     *
     * @param cantidad color que se quiere tomar como base 1-255.
     * @return imagen en escala de grises.
     */
    public Image ImagenDithering(int cantidad) {
        double conversionFactor = 255 / (cantidad - 1);
        int rojo, verde, azul;
        WritableImage nuevaImagen = new WritableImage(this.getAnchura(), this.getAltura());
        PixelReader pixelOriginal = this.getImagen().getPixelReader();
        PixelWriter nuevoPixel = nuevaImagen.getPixelWriter();
        Color color;
        for (int i = 0; i < this.getAnchura(); i++) {
            double error = 0;
            for (int j = 0; j < this.getAltura(); j++) {
                color = pixelOriginal.getColor(i, j);
                rojo = (int) (color.getRed() * 255);
                verde = (int) (color.getGreen() * 255);
                azul = (int) (color.getBlue() * 255);
                double averageValue = (rojo + verde + azul) / 3;
                double grisTemp = averageValue + error;
                grisTemp = (int) ((grisTemp / conversionFactor) + 0.5) * conversionFactor;
                error = averageValue + error - grisTemp;
                grisTemp = grisTemp / 256;
                grisTemp = Math.min(Math.max((grisTemp), 0), 255);
                nuevoPixel.setColor(i, j, Color.color(grisTemp, grisTemp, grisTemp));
            }
        }
        return nuevaImagen;
    }
}
