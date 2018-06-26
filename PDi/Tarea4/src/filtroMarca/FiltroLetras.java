/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtroMarca;

import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * 
 * @author hectorsama.
 * @url http://la-morsa.blogspot.mx/2007/03/sopa-de-letras.html
 */
public class FiltroLetras extends Filtro {

    public FiltroLetras(Image imagen) {
        super(imagen);
    }

    /**
     * @return Cadena con formato html de la imagen.
     */
    public String getCadenaHtmlLetras(int ancho, int alto, Character caracter) {
        BufferedImage imagen = SwingFXUtils.fromFXImage(getImagen(), null);
        int altura = imagen.getHeight() / alto;
        int anchura = imagen.getWidth() / ancho;
        String[][] tabla = new String[altura][anchura];
        String tablaHtml = "<table border = \"0\" cellspacing=\"0\" "
                + "cellpadding=\"0\"\n";
        int l = 0;
        for (int i = 0; i < imagen.getWidth(); i += ancho) {
            int k = 0;
            for (int j = 0; j < imagen.getHeight(); j += alto) {
                int x = i + ancho;
                int y = j + alto;
                int d1 = ancho;
                int d2 = alto;
                if (x > imagen.getWidth()) {
                    x = imagen.getWidth();
                    d1 = imagen.getWidth() - i;
                }
                if (y > imagen.getHeight()) {
                    y = imagen.getHeight();
                    d2 = imagen.getHeight() - j;
                }
                if (k >= altura || l >= anchura) {
                    break;
                }
                if(caracter.equals('O')){
                    tabla[k++][l] = generaDomino(imagen, i, j, x, y, 
                            d1 * d2);
                }else{
                    if(caracter.equals('N')){
                        tabla[k++][l] = generarNaipes(imagen, i, j, x, y, 
                            d1 * d2);
                    }else{
                        tabla[k++][l] = generarFilaCeldas(imagen, i, j, x, y, d1 * d2,
                            caracter);                
                    }
                }
            }
            l++;
        }
        for (int i = 0; i < altura; i++) {
            tablaHtml += "\t<tr>\n";
            for (int j = 0; j < anchura; j++) {
                tablaHtml += tabla[i][j];
            }
            tablaHtml += "</tr>";
        }
        tablaHtml += "</table>";
        return tablaHtml;
    }

    /**
     * @return Cadena de celdas de una fila en html.
     */
    private String generarFilaCeldas(BufferedImage bufferedImagen, int x, int y,
            int ancho, int alto, int tot, Character caracter) {
        Character auxOpcion = caracter;
        int rojoHex = 0;
        int verde = 0;
        int azul = 0;
        int rojoRgb = 0;
        for (int i = x; i < ancho; i++) {
            for (int j = y; j < alto; j++) {
                java.awt.Color nuevoColor
                        = new java.awt.Color(bufferedImagen.getRGB(i, j));
                rojoHex += nuevoColor.getRed();
                rojoRgb = nuevoColor.getRed();
                verde += nuevoColor.getGreen();
                azul += nuevoColor.getBlue();
            }
        }
        rojoHex /= tot;
        verde /= tot;
        azul /= tot;
        // convierte el color a hexadecimal
        String hex = String.format("#%02x%02x%02x", rojoHex, verde, azul);
        //Usamos 'M' como valor por default.        
        if(!(caracter.equals('D') || caracter.equals('@'))){
            if(rojoRgb >= 0 && rojoRgb < 16)
                caracter = 'M';
            else if(rojoRgb >= 16 && rojoRgb < 32)
                caracter = 'N';
            else if(rojoRgb >= 32 && rojoRgb < 48)
                caracter = 'H';
            else if(rojoRgb >= 48 && rojoRgb < 64)
                caracter = '#';
            else if(rojoRgb >= 64 && rojoRgb < 80)
                caracter = 'Q';
            else if(rojoRgb >= 80 && rojoRgb < 96)
                caracter = 'U';
            else if(rojoRgb >= 96 && rojoRgb < 112)
                caracter = 'A';
            else if(rojoRgb >= 112 && rojoRgb < 128)
                caracter = 'D';
            else if(rojoRgb >= 128 && rojoRgb < 144)
                caracter = 'O';
            else if(rojoRgb >= 144 && rojoRgb < 160)
                caracter = 'Y';
            else if(rojoRgb >= 160 && rojoRgb < 176)
                caracter = '2';
            else if(rojoRgb >= 176 && rojoRgb < 192)
                caracter = '$';
            else if(rojoRgb >= 192 && rojoRgb < 208)
                caracter = '%';
            else if(rojoRgb >= 208 && rojoRgb < 224)
                caracter = '+';
            else if(rojoRgb >= 224 && rojoRgb < 240)
                caracter = '-';
            else if(rojoRgb >= 240 && rojoRgb < 256)
                caracter = 'M';
        }                     
        if(auxOpcion.equals('C')){
            return "\t\t<nobr><td><b><font size=\"1\">"
                    + caracter + "</font></b></td></nobr>\n";            
        }
        return "\t\t<nobr><td><b><font size=\"1\" color=\"" + hex + "\">"
                + caracter + "</font></b></td></nobr>\n";        
    }
    
    /**
     * @return una cadena con la representación de una imágen
     */
    public String getCadenaHtmlLetrasTonosGris(int ancho, int alto, Character caracter) {
        FiltroTonosGris filtroTonosGris = new FiltroTonosGris(getImagen());
        Image imagenFiltroGris = filtroTonosGris.getImagenGrisesPromedio();
        return new FiltroLetras(imagenFiltroGris).getCadenaHtmlLetras(ancho, alto, caracter);
    }        
    
    /**
     * Domino negro.
     */
    private String generaDomino(BufferedImage bufferedImagen, int x, int y,
            int ancho, int alto, int tot) {               
        int rojoHex = 0;
        int verde = 0;
        int azul = 0;
        int rojoRgb = 0;
        for (int i = x; i < ancho; i++) {
            for (int j = y; j < alto; j++) {
                java.awt.Color nuevoColor
                        = new java.awt.Color(bufferedImagen.getRGB(i, j));
                rojoHex += nuevoColor.getRed();
                rojoRgb = nuevoColor.getRed();
                verde += nuevoColor.getGreen();
                azul += nuevoColor.getBlue();
            }
        }
        rojoHex /= tot;
        verde /= tot;
        azul /= tot;
        // convierte el color a hexadecimal
        String hex = String.format("#%02x%02x%02x", rojoHex, verde, azul);
        Character caracter = '@';        
            if(rojoRgb >= 0 && rojoRgb < 25)
                caracter = '0';
            else if(rojoRgb >= 25 && rojoRgb < 50)
                caracter = '1';
            else if(rojoRgb >= 50 && rojoRgb < 75)
                caracter = '2';
            else if(rojoRgb >= 75 && rojoRgb < 100)
                caracter = '3';
            else if(rojoRgb >= 100 && rojoRgb < 125)
                caracter = '4';
            else if(rojoRgb >= 125 && rojoRgb < 150)
                caracter = '5';
            else if(rojoRgb >= 150 && rojoRgb < 175)
                caracter = '6';
            else if(rojoRgb >= 175 && rojoRgb < 200)
                caracter = '7';
            else if(rojoRgb >= 200 && rojoRgb < 225)
                caracter = '8';
            else if(rojoRgb >= 225 && rojoRgb < 250)
                caracter = '9';            
            
            return "\t\t<nobr><td><b><font size=\"1\">"
                    + caracter + "</font></b></td></nobr>\n";                    
    }
            
    private String generarNaipes(BufferedImage bufferedImagen, int x, int y,
            int ancho, int alto, int tot) {               
        int rojoHex = 0;
        int verde = 0;
        int azul = 0;
        int rojoRgb = 0;
        for (int i = x; i < ancho; i++) {
            for (int j = y; j < alto; j++) {
                java.awt.Color nuevoColor
                        = new java.awt.Color(bufferedImagen.getRGB(i, j));
                rojoHex += nuevoColor.getRed();
                rojoRgb = nuevoColor.getRed();
                verde += nuevoColor.getGreen();
                azul += nuevoColor.getBlue();
            }
        }
        rojoHex /= tot;
        verde /= tot;
        azul /= tot;
        // convierte el color a hexadecimal
        String hex = String.format("#%02x%02x%02x", rojoHex, verde, azul);
        Character caracter = '@';        
            if(rojoRgb >= 0 && rojoRgb < 19)
                caracter = 'A';
            else if(rojoRgb >= 19 && rojoRgb < 38)
                caracter = 'B';
            else if(rojoRgb >= 38 && rojoRgb < 57)
                caracter = 'C';
            else if(rojoRgb >= 57 && rojoRgb < 76)
                caracter = 'D';
            else if(rojoRgb >= 76 && rojoRgb < 95)
                caracter = 'E';
            else if(rojoRgb >= 95 && rojoRgb < 114)
                caracter = 'F';
            else if(rojoRgb >= 114 && rojoRgb < 133)
                caracter = 'G';
            else if(rojoRgb >= 133 && rojoRgb < 152)
                caracter = 'H';
            else if(rojoRgb >= 152 && rojoRgb < 171)
                caracter = 'I';
            else if(rojoRgb >= 171 && rojoRgb < 190)
                caracter = 'J';            
            else if(rojoRgb >= 190 && rojoRgb < 209)
                caracter = 'K';   
            else if(rojoRgb >= 209 && rojoRgb < 228)
                caracter = 'L';   
            else if(rojoRgb >= 228 && rojoRgb < 256)
                caracter = 'M';   
            
            return "\t\t<nobr><td><b><font size=\"1\">"
                    + caracter + "</font></b></td></nobr>\n";                    
    }
}