package com.mycompany.bibiotecamusicafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class FXMLController implements Initializable {
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void openFindArtist(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PanelBuscarArtista.fxml"));
        Scene escena = new Scene(root);
        PantallaController pantalla = PantallaController.getInstancia(escena);
        pantalla.activar("panelBuscarArtista");
        System.out.println(event);
        System.out.println("Has pinchado en buscar artista");
    }

    @FXML
    private void openFindAlbums(ActionEvent event) {
        System.out.println("Has pinchado en buscar album");
    }

    @FXML
    private void openFindSong(ActionEvent event) {
    }

    @FXML
    private void openInicio(ActionEvent event) {
    }
}
