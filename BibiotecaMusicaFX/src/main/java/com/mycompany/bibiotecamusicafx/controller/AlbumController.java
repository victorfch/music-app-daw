package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.utility.VentanasYControladores;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlbumController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField genero;
    @FXML
    private ScrollPane contenedorAlbums;

    ArtistaServicio servicioArtistas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VentanasYControladores.anhadirControlador("album", this);
        actualizarPanel();
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Constantes.VISTA_ALBUM_EDIT));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage ventana = new Stage();
        ventana.setTitle("ALBUM");
        ventana.setScene(scene);
        VentanasYControladores.anhadirVentana("album-editar", ventana);
        ventana.initOwner(VentanasYControladores.getVentana("principal"));
        ventana.initModality(Modality.WINDOW_MODAL);
        ventana.showAndWait();
    }

    @FXML
    private void buscarPorFiltro(ActionEvent event) {
    }

    public void actualizarPanel() {
        contenedorAlbums.setContent(null);
        Label warning = new Label(Constantes.MSG_NO_HAY_ALBUMES);
        warning.setTextFill(Paint.valueOf(Constantes.COLOR_ROJO));
        warning.setPadding(new Insets(5, 0, 0, 10));
        contenedorAlbums.setContent(warning);
    }

}
