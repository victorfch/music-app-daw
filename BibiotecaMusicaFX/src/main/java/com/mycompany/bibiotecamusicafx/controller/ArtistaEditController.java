package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.utility.DatePickerFecha;
import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.utility.Ventanas;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ArtistaEditController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField nacionalidad;
    @FXML
    private DatePicker fechaNacimiento;
    @FXML
    private Label alerta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void guardar(ActionEvent event) throws ParseException {
        String nombre = this.nombre.getText().trim();
        String nacionalidad = this.nacionalidad.getText().trim();
        if (!nombre.isEmpty() && !nacionalidad.isEmpty() && !fechaNacimiento.getValue().toString().isEmpty()) {
            Date fNac = DatePickerFecha.convertirFechaDatePickerADate(fechaNacimiento.getValue());
            Artista artista = new Artista(nombre, nacionalidad, fNac);
            Ventanas.get("editar-artista").close();
        } else {
            alerta.setText("Por favor, complete todos los campos");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Ventanas.get("editar-artista").close();
    }

}
