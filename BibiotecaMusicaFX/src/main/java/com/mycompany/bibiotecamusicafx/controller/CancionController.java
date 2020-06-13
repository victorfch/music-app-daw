package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.model.Album;
import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicio;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicioMySQL;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicioMySQL;
import com.mycompany.bibiotecamusicafx.servicio.ServicioLetraApi;
import com.mycompany.bibiotecamusicafx.utility.Utilidades;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class CancionController implements Initializable {

    private ArtistaServicio servicioArtistas;
    private AlbumServicio servicioAlbumes;
    @FXML
    private TextField titulo;
    @FXML
    private ComboBox<Artista> comboArtistas;
    @FXML
    private ComboBox<Album> comboAlbumes;

    public CancionController() throws SQLException {
        servicioArtistas = ArtistaServicioMySQL.getServicioMySQL();
        servicioAlbumes = AlbumServicioMySQL.getServicioMySQL();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarArtistas();
    }

    @FXML
    private void buscar(ActionEvent event) {
        String letra;
        String alertas = "";
        if (this.comboArtistas.getValue() == null) {
            alertas += "El campo artista no puede estar vacío.\n";
        }
        if (this.comboAlbumes.getValue() == null) {
            alertas += "El campo album no puede estar vacío.\n";
        }
        if (this.titulo.getText().isEmpty()) {
            alertas += "El campo titulo no puede estar vacío.\n";
        }
        if (alertas.isEmpty()) {
            String artista = this.comboArtistas.getValue().getNombre();
            String album = this.comboAlbumes.getValue().getTitulo();
            String titulo = this.titulo.getText();
            String artistaFormateado = Utilidades.formatearEspacios(artista.toLowerCase());
            String tituloFormateado = Utilidades.formatearEspacios(titulo.toLowerCase());
            letra = ServicioLetraApi.getLetraCancion(artistaFormateado, tituloFormateado);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Letra");
            ButtonType botonGuardar = new ButtonType("Guardar");
            ButtonType botonCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(botonGuardar, botonCancelar);
            alert.setHeaderText(null);
            alert.setContentText(artista + " - " + titulo);
            TextArea textArea = new TextArea(letra);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            alert.getDialogPane().setExpandableContent(textArea);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cuidado");
            alert.setHeaderText(null);
            alert.setContentText(alertas);
            alert.showAndWait();
        }
    }

    @FXML
    private void traerAlbumesDeArtista(ActionEvent event) {
        comboAlbumes.setConverter(new StringConverter<Album>() {
            private Map<String, Album> mapaAlbumes = new HashMap<>();

            @Override
            public Album fromString(String nombre) {
                return mapaAlbumes.get(nombre);
            }

            @Override
            public String toString(Album album) {
                mapaAlbumes.put(album.getTitulo(), album);
                return album.getTitulo();
            }
        });
        comboAlbumes.getItems().clear();
        comboAlbumes.getItems().addAll(servicioAlbumes.getAlbumsByArtista(
                comboArtistas.getValue().getId()));
        comboAlbumes.getSelectionModel().selectFirst();
        comboArtistas.setVisibleRowCount(5);
    }

    private void cargarArtistas() {
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
        comboArtistas.setVisibleRowCount(5);
    }

}
