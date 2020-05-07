package com.mycompany.bibiotecamusicafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

public class MenuController implements Initializable {

    @FXML
    private MenuBar menuBar;
    @FXML
    private AnchorPane contenedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            contenedor.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/Inicio.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void irVentanaInicio(ActionEvent event) throws IOException {
        contenedor.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/Inicio.fxml")));
    }

    @FXML
    private void irVentanaArtista(ActionEvent event) throws IOException {
        contenedor.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/Artista.fxml")));
    }

    @FXML
    private void irVentanaAlbum(ActionEvent event) throws IOException {
        contenedor.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/Album.fxml")));
    }

    @FXML
    private void irVentanaCancion(ActionEvent event) throws IOException {
        contenedor.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/Cancion.fxml")));
    }
    
}
