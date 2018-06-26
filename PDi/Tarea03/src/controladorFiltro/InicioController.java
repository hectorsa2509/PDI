
package controladorFiltro;

import filtroMarca.FiltroAltoContraste;
import filtroMarca.FiltroMarcaAgua;
import filtroMarca.FiltroAltoContraste;
import filtroMarca.FiltroTonosGris;
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
     * Permite guardar una imágen en los directorios locales.
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
}
