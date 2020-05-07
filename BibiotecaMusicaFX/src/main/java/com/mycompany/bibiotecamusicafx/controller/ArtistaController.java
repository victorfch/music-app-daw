package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicioArray;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ArtistaController implements Initializable {

    ArtistaServicio servicioArtistas = ArtistaServicioArray.getInstancia();
    
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
        Parent root = FXMLLoader.load(getClass().getResource(Constantes.VISTA_ARTISTA_EDIT));
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
            Label warning = new Label(Constantes.MSG_NO_HAY_ARTISTAS);
            warning.setTextFill(Paint.valueOf(Constantes.COLOR_ROJO));
            warning.setPadding(new Insets(5, 0, 0, 10));
            contenedorArtistas.setContent(warning);
        } else {
            GridPane cuadricula = new GridPane();
            cuadricula.setHgap(30);
            cuadricula.setVgap(10);
            cuadricula.setPadding(new Insets(5, 0, 0, 10));
            int contador = 1;
            Label etiquetaNombre = new Label(Constantes.ETIQUETA_NOMBRE);
            Label etiquetaNacionalidad = new Label(Constantes.ETIQUETA_NACIONALIDAD);
            Label etiquetaEdad = new Label(Constantes.ETIQUETA_EDAD);
            Label etiquetaAcciones = new Label("Acciones");
            cuadricula.add(etiquetaNombre, 0, 0);
            cuadricula.add(etiquetaNacionalidad, 1, 0);
            cuadricula.add(etiquetaEdad, 2, 0);
            cuadricula.add(etiquetaAcciones, 3, 0);
            for (Artista artista : servicioArtistas.obtenerTodos()) {
                String[] artistaInfo = artista.toString().split(Constantes.GUION);
                Label nombre = new Label(artistaInfo[0]);
                Label nacionalidad = new Label(artistaInfo[1]);
                Label edad = new Label(artistaInfo[2]);
                Button boton = new Button("Ver");
                cuadricula.add(nombre, 0, contador);
                cuadricula.add(nacionalidad, 1, contador);
                cuadricula.add(edad, 2, contador);
                cuadricula.add(boton, 3, contador);
                contador++;
            }
            contenedorArtistas.setContent(cuadricula);
        }
    }

}
