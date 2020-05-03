package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.MenuInterface;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;


public class AlbumController implements Initializable, MenuInterface {

    @FXML
    private MenuBar menuBar;
    @FXML
    private AnchorPane contenedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    @FXML
    public void irVentanaArtista(ActionEvent event) throws IOException {
        contenedor.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/Artista.fxml")));

    }

    @Override
    @FXML
    public void irVentanaAlbum(ActionEvent event) throws IOException {
    }

    @Override
    @FXML
    public void irVentanaInicio(ActionEvent event) throws IOException {
        contenedor.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/Inicio.fxml")));
    }

    @Override
    @FXML
    public void irVentanaCancion(ActionEvent event) throws IOException {
        contenedor.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/Cancion.fxml")));
    }
    
}
