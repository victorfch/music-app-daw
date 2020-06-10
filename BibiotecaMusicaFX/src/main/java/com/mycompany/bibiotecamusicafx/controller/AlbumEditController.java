package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.model.Album;
import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicio;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicioMySQL;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicioMySQL;
import com.mycompany.bibiotecamusicafx.utility.VentanasYControladores;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

public class AlbumEditController implements Initializable {

    private AlbumServicio servicioAlbumes;

    private ArtistaServicio servicioArtistas;
    @FXML
    private AnchorPane panelHayArtistas;
    @FXML
    private AnchorPane panelNoHayArtistas;
    @FXML
    private TextField titulo;
    @FXML
    private TextField genero;
    @FXML
    private DatePicker fecha;
    @FXML
    private ComboBox<Artista> comboArtistas;
    @FXML
    private TextField id;

    public AlbumEditController() throws SQLException {
        this.servicioArtistas = ArtistaServicioMySQL.getServicioMySQL();
        this.servicioAlbumes = AlbumServicioMySQL.getServicioMySQL();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (servicioArtistas.contarArtistas() > 0) {
            panelNoHayArtistas.setVisible(false);
            cargarDatosListaArtista();
        } else {
            panelHayArtistas.setVisible(false);
        }
        VentanasYControladores.anhadirControlador("album-editar", this);
    }

    private void cargarDatosListaArtista() {
        comboArtistas.setConverter(new StringConverter<Artista>() {
            private Map<String, Artista> mapaArtista = new HashMap<>();

            @Override
            public Artista fromString(String nombre) {
                return mapaArtista.get(nombre);
            }

            @Override
            public String toString(Artista artista) {
                mapaArtista.put(artista.getNombre(), artista);
                return artista.getNombre();
            }

        });
        comboArtistas.getItems().addAll(servicioArtistas.obtenerTodos());
        comboArtistas.getSelectionModel().selectFirst();
        comboArtistas.setVisibleRowCount(5);
    }

    @FXML
    private void guardar(ActionEvent event) {
        String nombre = titulo.getText().trim();
        String genero = this.genero.getText().trim();
        String msg = "";
        if (nombre.isEmpty()) {
            msg += "El campo título no puede estar vacío \n";
        }
        if (genero.isEmpty()) {
            msg += "El campo género no puede estar vacío \n";
        }
        if (fecha.getValue() == null) {
            msg += "El campo fecha no puede estar vacío \n";
        }
        if (msg.isEmpty()) {
            if (id.getText().isEmpty()) {
                Album album = new Album(comboArtistas.getValue().getId(), nombre, genero, Date.valueOf(fecha.getValue()), null);
                servicioAlbumes.guardar(album);
                AlbumController albumController = (AlbumController) VentanasYControladores.getControlador("album");
                albumController.actualizarPanel(servicioAlbumes.obtenerTodos());
                VentanasYControladores.getVentana("album-editar").close();
            } else {
                editar();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cuidado");
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        VentanasYControladores.getVentana("album-editar").close();
    }

    void iniciarDatos(Album album) {
        id.setText(album.getId());
        comboArtistas.setValue(servicioArtistas.getArtista(album.getArtistaId()));
        titulo.setText(album.getTitulo());
        genero.setText(album.getGenero());
        fecha.setValue(album.getFechaLanzamiento().toLocalDate());
    }

    private void editar() {
        Album album = servicioAlbumes.getAlbum(id.getText());
        album.setArtistaId(comboArtistas.getValue().getId());
        album.setFechaLanzamiento(Date.valueOf(fecha.getValue()));
        album.setTitulo(titulo.getText());
        album.setGenero(genero.getText());
        servicioAlbumes.editar(album);
        AlbumController controladorAlbum = (AlbumController) VentanasYControladores.getControlador("album");
        controladorAlbum.actualizarPanel(servicioAlbumes.obtenerTodos());
        VentanasYControladores.getVentana("album-editar").close();
    }
}
