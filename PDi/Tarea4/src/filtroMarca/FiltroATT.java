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
import java.util.LinkedList;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.awt.image.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**                                                                                                         
 * @author hectorsama                                                                                        
 */
public class FiltroATT extends Filtro{
    
    
    
    /** Tomamos un numero aleatorio  */
    private static final int N = 15;
    
    /**
     * Método constructor de la clase ATT 
     */
    public FiltroATT(Image imagen) {
        super(imagen);
    }
    
  /**
   * Nos da el filtro con un efecto similar al icono de FiltroATT
   * @return La imagen con el efecto FiltroATT, lineas en blanco y negro.
   */
    public Image filtra() {
        Filtro.PROGRESO = 0;
        FiltroAltoContraste altoContraste = new FiltroAltoContraste((Image)this.getImagen());
        BufferedImage ac = SwingFXUtils.fromFXImage(altoContraste.getImagenAltoContraste(),null);
        int w = ac.getWidth();
        int h = ac.getHeight();
        Raster rac = ac.getData();
        BufferedImage nueva = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        WritableRaster wrn = nueva.getRaster();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h - N; j += N) {
                int puntos = 0;
                for (int y = j; y < j + N; y++) {
                    if (rac.getSample(i, y, 0) == 0) {
                        puntos++;
                    }
                }               
                boolean[] acomodados = centra(N, puntos);
                for (int y = j; y < j + N; y++) {
                    if (acomodados[y - j]) {
                        wrn.setSample(i, y, 0, 0);
                        wrn.setSample(i, y, 1, 0);
                        wrn.setSample(i, y, 2, 0);
                    } else {
                        wrn.setSample(i, y, 0, 0xff);
                        wrn.setSample(i, y, 1, 0xff);
                        wrn.setSample(i, y, 2, 0xff);
                    }
                }
                Filtro.PROGRESO = (this.avanzar()/this.getTotal());
                for (int k = 0; k < N; k++) {
                    this.avanzar();
                }
            }
        }
        lineas(nueva);
        return SwingFXUtils.toFXImage(nueva, null);
    }
    
    /**
     * Dibuja lineas horizontales blancas.
     */
    private static void lineas(BufferedImage src) {
        WritableRaster wr = src.getRaster();
        for (int i = 0; i < wr.getWidth(); i++) {
            for (int j = 0; j < wr.getHeight() - N; j += N) {
                wr.setSample(i, j, 0, 0xff);
                wr.setSample(i, j, 1, 0xff);
                wr.setSample(i, j, 2, 0xff);
            }
        }
    }
    
    private static boolean[] centra(int tam, int puntos) {
        boolean[] acomodados = new boolean[tam];
        int n = puntos / 2;
        int m = puntos % 2 == 0 ? n - 1 : n;
        for (int i = (tam / 2) - n; i <= (tam / 2) + m; i++) {
            acomodados[i] = true;
        }
        return acomodados;
    }
}