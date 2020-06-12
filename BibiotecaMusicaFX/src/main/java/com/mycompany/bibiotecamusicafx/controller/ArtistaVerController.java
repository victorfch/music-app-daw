package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.model.Album;
import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicio;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicioMySQL;
import com.mycompany.bibiotecamusicafx.utility.VentanasYControladores;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtistaVerController implements Initializable {

    AlbumServicio servicioAlbumes;
    @FXML
    private TextField nombre;
    @FXML
    private TextField nacionalidad;
    @FXML
    private TextField edad;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextArea listaAlbumes;

    public ArtistaVerController() throws SQLException {
        servicioAlbumes = AlbumServicioMySQL.getServicioMySQL();
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VentanasYControladores.anhadirControlador("artista-ver", this);
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
        List<Album> listaAlbumes = servicioAlbumes.getAlbumsByArtista(artista.getId());
        String albumes = "Este artista no tiene ningún álbum relacionado";
        if (listaAlbumes.isEmpty()) {
            this.listaAlbumes.setText(albumes);
        } else {
            albumes = "";
            for (Album album : servicioAlbumes.getAlbumsByArtista(artista.getId())) {
                albumes += album.toString() + System.lineSeparator();
            }
            this.listaAlbumes.setText(albumes);
        }
    }
}
