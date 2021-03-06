
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * 
 * @author hectorsama
 */
public class Main extends Application{
        
    @Override
    public void start(Stage primaryStage) throws Exception {                       
        
        Parent root = FXMLLoader.load(getClass().getResource("vista/Inicio.fxml"));
        root.setStyle("-fx-background-color: #F0591E;");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
    public static void main(String[] args){
        launch(args);
    }
}
