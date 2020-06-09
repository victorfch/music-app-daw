package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicioMySQL;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.utility.VentanasYControladores;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ArtistaEditController implements Initializable {

    private ArtistaServicio servicioArtistas;

    @FXML
    private TextField nombre;
    @FXML
    private TextField nacionalidad;
    @FXML
    private DatePicker fechaNacimiento;
    @FXML
    private Label alerta;
    @FXML
    private TextField id;

    public ArtistaEditController() throws SQLException {
        this.servicioArtistas = ArtistaServicioMySQL.getServicioMySQL();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VentanasYControladores.anhadirControlador("artista-editar", this);
    }

    @FXML
    private void guardar(ActionEvent event) {
        String nombre = this.nombre.getText().trim();
        String nacionalidad = this.nacionalidad.getText().trim();

        if (!nombre.isEmpty() && !nacionalidad.isEmpty() && (fechaNacimiento.getValue() != null)) {
            if (id.getText().isEmpty()) {
                Artista artista = new Artista(nombre, nacionalidad, Date.valueOf(fechaNacimiento.getValue()), null);
                servicioArtistas.guardar(artista);
                ArtistaController controlador = (ArtistaController) VentanasYControladores.getControlador("artista");
                controlador.actualizarPanel(servicioArtistas.obtenerTodos());
                VentanasYControladores.getVentana("artista-editar").close();
            } else {
                editar();
            }
        } else {
            alerta.setText(Constantes.MSG_COMPLETAR_CAMPOS);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        VentanasYControladores.getVentana("artista-editar").close();
    }

    public void iniciarDatos(Artista artista) {
        id.setText(artista.getId());
        nombre.setText(artista.getNombre());
        nacionalidad.setText(artista.getNacionalidad());
        fechaNacimiento.setValue(artista.getFechaNacimientoLocalDate());
    }

    public void editar() {
        Artista artista = servicioArtistas.getArtista(id.getText());
        artista.setNombre(this.nombre.getText().trim());
        artista.setNacionalidad(this.nacionalidad.getText().trim());
        artista.setFechaNacimiento(Date.valueOf(this.fechaNacimiento.getValue()));
        servicioArtistas.editar(artista);
        ArtistaController controlador = (ArtistaController) VentanasYControladores.getControlador("artista");
        controlador.actualizarPanel(servicioArtistas.obtenerTodos());
        VentanasYControladores.getVentana("artista-editar").close();
    }
}
