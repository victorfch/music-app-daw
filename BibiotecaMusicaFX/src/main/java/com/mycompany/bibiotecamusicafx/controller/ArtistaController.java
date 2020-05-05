package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.utility.Ventanas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ArtistaController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField edad;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ArtistaEdit.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        Stage ventana = new Stage();
        ventana.setTitle("ARTISTA");
        ventana.setScene(scene);
        Ventanas.anhadir("editar-artista", ventana);
        ventana.initOwner(Ventanas.get("principal"));
        ventana.initModality(Modality.WINDOW_MODAL);
        ventana.showAndWait();
    }

}
