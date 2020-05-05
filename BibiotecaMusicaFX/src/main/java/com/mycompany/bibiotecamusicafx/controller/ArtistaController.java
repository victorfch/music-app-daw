package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicioImpl;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ArtistaController implements Initializable {

    ArtistaServicio servicioArtistas = ArtistaServicioImpl.getInstancia();
    
    @FXML
    private TextField nombre;
    @FXML
    private TextField edad;
    @FXML
    private ScrollPane contenedorArtistas;
    

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VentanasYControladores.anhadirControlador("artista", this);
        actualizarPanelArtistas();
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ArtistaEdit.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage ventana = new Stage();
        ventana.setTitle("ARTISTA");
        ventana.setScene(scene);
        VentanasYControladores.anhadirVentana("artista-editar", ventana);
        ventana.initOwner(VentanasYControladores.getVentana("principal"));
        ventana.initModality(Modality.WINDOW_MODAL);
        ventana.showAndWait();
    }
    
    public void actualizarPanelArtistas() {
        contenedorArtistas.setContent(null);
        if (servicioArtistas.contarArtistas() == 0) {
            Label texto = new Label();
            texto.setTextFill(Paint.valueOf("#f53434"));
            texto.setText("No hay artistas añadidos");
            texto.setPadding(new Insets(5, 0, 0, 10));
            contenedorArtistas.setContent(texto);
        } else {
            VBox panel = new VBox();
            for (Artista artista : servicioArtistas.obtenerTodos()) {
                Label texto = new Label();
                texto.setText(artista.toString());
                texto.setPadding(new Insets(5, 0, 0, 10));
                panel.getChildren().add(texto);
            }
            contenedorArtistas.setContent(panel);
        }
    }

}
