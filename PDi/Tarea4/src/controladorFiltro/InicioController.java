
package controladorFiltro;

import filtroMarca.FiltroAltoContraste;
import filtroMarca.FiltroMarcaAgua;
import filtroMarca.FiltroAltoContraste;
import filtroMarca.FiltroTonosGris;
import filtroMarca.FiltroATT;
import filtroMarca.FiltroLetras;
import filtroMarca.FiltroOleo;
import filtroMarca.ImagenRecursiva;
import filtroMarca.LuzNegra;
import filtroMarca.FiltroSemitonos;
import filtroMarca.FiltroSepia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;

/**
 *
 * @author hectorsama.
 */
public class InicioController implements Initializable {
    
    @FXML
    private ImageView imagenOriginal;
    @FXML
    private ImageView imagenNueva;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Permite buscar una imágen.
     * 
     **/
    @FXML
    public void abrirImagen(){        
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Abrir imágen");
        File file = chooser.showOpenDialog(null);
        try {
            Image imagenEntrada = new Image(new FileInputStream(file));
            imagenOriginal.setImage(imagenEntrada);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE,
                    null, ex);
        }                       
    }
    
    /**
     * Permite guardar una imágen.
     * 
     **/
    @FXML
    public void guardarImagen(){        
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Guardar imágen");
        File file = chooser.showSaveDialog(null);        
        try {            
            ImageIO.write(SwingFXUtils.fromFXImage(imagenNueva.getImage(), null), "png", file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
        }                       
    }
     
    /**
     * Intenta quitar la marca de agua ya que es un proceso complejo.
     */
    @FXML
    public void quitarMarcaAgua(){
        FiltroMarcaAgua filtroMarcaAgua = new FiltroMarcaAgua(
                imagenOriginal.getImage());
        imagenNueva.setImage(filtroMarcaAgua.quitarMarcaAgua());
    }
    
    
        /**
     * Asigna el filtro oleo a la imágen original.
     * 
     */
    @FXML
    public void asignarFiltroATT(){
        FiltroATT filtro = new FiltroATT(imagenOriginal.getImage());
        imagenNueva.setImage(filtro.filtra());
    } 
    
        @FXML
    public void FiltroOleo(){
        FiltroOleo filtro = new FiltroOleo(imagenOriginal.getImage());
        imagenNueva.setImage(filtro.ImagenOleo());
    } 
    
    
        @FXML
    public void FiltroRecursionGrises(){
        FiltroTonosGris filtroTonosGris = new FiltroTonosGris(imagenOriginal.getImage());
        ImagenRecursiva filtroRecursion = new ImagenRecursiva(
                filtroTonosGris.getImagenGrisesPromedio());
        try {
            filtroRecursion.colorReal("recursion.html", 33, 33);
        } catch (IOException ex) {
            Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @FXML
    public void FiltroLuzNegra(){
        LuzNegra filtroLuzNegra = new LuzNegra(
        imagenOriginal.getImage());
        imagenNueva.setImage(filtroLuzNegra.filtroLuzNegra());
    }
    
    @FXML
    public void FiltroSepia(){        
        Stage nuevoStageBrillo = new Stage(); 
        BorderPane borderPane = new BorderPane();        
        Spinner valorBrillo = new Spinner(0, 255, 0);
        valorBrillo.setEditable(false);        
        Button ok = new Button("Ok");
        Button cancelar = new Button("Cancelar");        
        HBox botones = new HBox(ok, cancelar);
        botones.setSpacing(50);                
        borderPane.setCenter(valorBrillo);
        borderPane.setBottom(botones);               
        Scene nuevoSceneBrillo = new Scene(borderPane);
        nuevoStageBrillo.setScene(nuevoSceneBrillo);
        nuevoStageBrillo.setMinHeight(120);
        nuevoStageBrillo.setMinWidth(280);        
        nuevoStageBrillo.show();        
        cancelar.setOnAction((ActionEvent event1) -> {
            nuevoStageBrillo.close();            
        });        
        ok.setOnAction((ActionEvent event1) -> {
            int sepia = (int)valorBrillo.getValue();            
            FiltroSepia filtroSepia = new FiltroSepia(
                imagenOriginal.getImage());
            imagenNueva.setImage(filtroSepia.getImagenSepia(sepia));         
            nuevoStageBrillo.close();            
        });      
}
    
    
    
    /**
     * Asigna el filtro de semitonos a la imágen original.
     * 
     */
    public void FiltroSemitonos(){        
        Stage nuevoStageBrillo = new Stage(); 
        nuevoStageBrillo.setTitle("Semitonos");
        BorderPane borderPane = new BorderPane();        
        Spinner valorBrillo = new Spinner(1, 255, 0);
        //Se desactiva para evitar error con entrada por teclado.
        valorBrillo.setEditable(false);        
        Button ok = new Button("Ok");
        Button cancelar = new Button("Cancelar");        
        HBox botones = new HBox(ok, cancelar);
        botones.setSpacing(50);                
        borderPane.setCenter(valorBrillo);
        borderPane.setBottom(botones);               
        Scene nuevoSceneBrillo = new Scene(borderPane);
        nuevoStageBrillo.setScene(nuevoSceneBrillo);
        nuevoStageBrillo.setMinHeight(120);
        nuevoStageBrillo.setMinWidth(280);        
        nuevoStageBrillo.show();        
        cancelar.setOnAction((ActionEvent event1) -> {
            nuevoStageBrillo.close();            
        });        
        ok.setOnAction((ActionEvent event1) -> {
            int cantidad = (int)valorBrillo.getValue();            
            FiltroSemitonos filtroSemitonos = new FiltroSemitonos(
                imagenOriginal.getImage());
            try {
                filtroSemitonos.semitono(cantidad, "semitonos.html");
            } catch (IOException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            }
            nuevoStageBrillo.close();            
        });     
    }
    
    
    public void FiltroDithering(){        
        Stage nuevoStageBrillo = new Stage(); 
        BorderPane borderPane = new BorderPane();        
        Spinner valorBrillo = new Spinner(1, 255, 0);
      
        valorBrillo.setEditable(false);        
        Button ok = new Button("Ok");
        Button cancelar = new Button("Cancelar");        
        HBox botones = new HBox(ok, cancelar);
        botones.setSpacing(50);                
        borderPane.setCenter(valorBrillo);
        borderPane.setBottom(botones);               
        Scene nuevoSceneBrillo = new Scene(borderPane);
        nuevoStageBrillo.setScene(nuevoSceneBrillo);
        nuevoStageBrillo.setMinHeight(120);
        nuevoStageBrillo.setMinWidth(280);        
        nuevoStageBrillo.show();        
        cancelar.setOnAction((ActionEvent event1) -> {
            nuevoStageBrillo.close();            
        });        
        ok.setOnAction((ActionEvent event1) -> {
            int cantidad = (int)valorBrillo.getValue();            
            FiltroTonosGris filtroTonosGris = new FiltroTonosGris(
                imagenOriginal.getImage());
            imagenNueva.setImage(filtroTonosGris.ImagenDithering(cantidad));         
            nuevoStageBrillo.close();            
        });     
}
    
    
        @FXML
    public void FiltroLetras(){
            Stage nuevoStageMosaico = new Stage();            
            BorderPane borderPane = new BorderPane();                        
            Spinner valorMosaico = new Spinner(1, 9999, 50);
            valorMosaico.setEditable(false);            
            Button ok = new Button("Ok");
            Button cancelar = new Button("Cancelar");            
            HBox botones = new HBox(ok, cancelar);
            botones.setSpacing(50);                        
            borderPane.setCenter(valorMosaico);
            borderPane.setBottom(botones);
            Scene sceneMosaico = new Scene(borderPane);
            nuevoStageMosaico.setScene(sceneMosaico);
            nuevoStageMosaico.setMinHeight(120);
            nuevoStageMosaico.setMinWidth(220);
            nuevoStageMosaico.show();
            cancelar.setOnAction((ActionEvent) -> {
                nuevoStageMosaico.close();                
            });
            ok.setOnAction((ActionEvent) -> {               
                FiltroLetras filtroLetras = new FiltroLetras(imagenOriginal.getImage());
                String html = filtroLetras.getCadenaHtmlLetras(
                        (int) valorMosaico.getValue(), 
                        (int) valorMosaico.getValue(),
                        'M');                
                ArchivoController archivoController = new ArchivoController(""
                        + "LetrasxColor.html");
                archivoController.escribirArchivo(html);
               nuevoStageMosaico.close();              
            });                                  
} 
         


}
