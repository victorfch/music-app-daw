package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.utility.VentanasYControladores;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ArtistaVerController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField nacionalidad;
    @FXML
    private TextField edad;
    @FXML
    private DatePicker fecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VentanasYControladores.anhadirControlador("artista-ver", this);
        // TODO
    }

    @FXML
    private void cerrar(ActionEvent event) {
        VentanasYControladores.getVentana("artista-ver").close();
    }

    public void iniciarDatos(Artista artista) {
        nombre.setText(artista.getNombre());
        nacionalidad.setText(artista.getNacionalidad());
        edad.setText(String.valueOf(artista.getEdad()));
        fecha.setValue(artista.getFechaNacimiento().toLocalDate());
    }
    
}
