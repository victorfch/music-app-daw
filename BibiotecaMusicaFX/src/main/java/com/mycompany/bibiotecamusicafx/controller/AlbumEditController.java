package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.model.Album;
import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicio;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicioMySQL;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicioMySQL;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.utility.VentanasYControladores;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

public class AlbumEditController implements Initializable {
    
    private AlbumServicio servicioAlbums;

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
    private Label warning;

    public AlbumEditController() throws SQLException {
        this.servicioArtistas = ArtistaServicioMySQL.getServicioMySQL();
        this.servicioAlbums = AlbumServicioMySQL.getServicioMySQL();
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
        if (!nombre.isEmpty() && !genero.isEmpty() && fecha.getValue() != null) {
            Album album = new Album(comboArtistas.getValue().getId(), nombre, genero, fecha.getValue());
            servicioAlbums.guardar(album);
            AlbumController albumController = (AlbumController) VentanasYControladores.getControlador("album");
            albumController.actualizarPanel();
            VentanasYControladores.getVentana("album-editar").close();
            
        } else {
            warning.setText(Constantes.MSG_COMPLETAR_CAMPOS);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        VentanasYControladores.getVentana("album-editar").close();
    }

}
