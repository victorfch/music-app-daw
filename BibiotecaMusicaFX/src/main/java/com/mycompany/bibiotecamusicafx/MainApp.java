package com.mycompany.bibiotecamusicafx;

import com.mycompany.bibiotecamusicafx.servicio.AlbumServicio;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicioMySQL;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicioMySQL;
import com.mycompany.bibiotecamusicafx.utility.VentanasYControladores;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ArtistaServicio servicioArtistas = ArtistaServicioMySQL.getServicioMySQL();
        AlbumServicio servicioAlbumes = AlbumServicioMySQL.getServicioMySQL();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Orange Music");
        stage.setScene(scene);
        VentanasYControladores.anhadirVentana("principal", stage);
        stage.show();
        stage.setOnCloseRequest((WindowEvent t) -> {
            servicioArtistas.cerrarSesion();
            servicioAlbumes.cerrarSesion();
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
