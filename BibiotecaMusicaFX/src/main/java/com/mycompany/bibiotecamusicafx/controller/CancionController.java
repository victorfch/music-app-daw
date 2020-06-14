package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.model.Album;
import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.model.Cancion;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicio;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicioMySQL;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicioMySQL;
import com.mycompany.bibiotecamusicafx.servicio.CancionServicioMySQL;
import com.mycompany.bibiotecamusicafx.servicio.ServicioLetraApi;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.utility.Utilidades;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.util.StringConverter;

public class CancionController implements Initializable {

    private ArtistaServicio servicioArtistas;
    private AlbumServicio servicioAlbumes;
    private CancionServicioMySQL servicioCanciones;
    @FXML
    private TextField titulo;
    @FXML
    private ComboBox<Artista> comboArtistas;
    @FXML
    private ComboBox<Album> comboAlbumes;
    @FXML
    private ScrollPane panelCanciones;

    public CancionController() throws SQLException {
        servicioArtistas = ArtistaServicioMySQL.getServicioMySQL();
        servicioAlbumes = AlbumServicioMySQL.getServicioMySQL();
        servicioCanciones = CancionServicioMySQL.getServicioMySQL();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarArtistas();
        actualizarPanel(servicioCanciones.obtenerTodos());
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
            String artistaId = this.comboArtistas.getValue().getId();
            String albumId = this.comboAlbumes.getValue().getId();
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
            Optional<ButtonType> resultado = alert.showAndWait();
            if (resultado.get() == botonGuardar) {
                Cancion cancion = new Cancion(titulo, artistaId, albumId, letra, null);
                servicioCanciones.guardar(cancion);
                actualizarPanel(servicioCanciones.obtenerTodos());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cuidado");
            alert.setHeaderText(null);
            alert.setContentText(alertas);
            alert.showAndWait();
        }
    }

    private void actualizarPanel(List<Cancion> canciones) {
        if (canciones.isEmpty()) {
            panelCanciones.setContent(null);
            Label warning = new Label("No hay canciones añadidas");
            warning.setTextFill(Paint.valueOf(Constantes.COLOR_ROJO));
            warning.setPadding(new Insets(5, 0, 0, 10));
            panelCanciones.setContent(warning);
        } else {
            GridPane cuadricula = new GridPane();
            cuadricula.setHgap(30);
            cuadricula.setVgap(10);
            cuadricula.setPadding(new Insets(5, 0, 0, 10));
            int contador = 1;
            Label etiquetaTitulo = new Label("Titulo");
            Label etiquetaAcciones = new Label();
            cuadricula.add(etiquetaTitulo, 0, 0);
            cuadricula.add(etiquetaAcciones, 1, 0);
            for (Cancion cancion : canciones) {
                Label titulo = new Label(cancion.getTitulo());
                MenuItem menuItem1 = new MenuItem("Eliminar");
                MenuItem menuItem2 = new MenuItem("Ver");
                MenuButton menuButton = new MenuButton("Acciones", null, menuItem1, menuItem2);
                menuItem1.setOnAction((ActionEvent event) -> {
                    System.out.println("eliminar");
                    System.out.println("id: " + cancion.getId());
                    servicioCanciones.eliminar(cancion.getId());
                    actualizarPanel(servicioCanciones.obtenerTodos());
                });
                menuItem2.setOnAction((ActionEvent event) -> {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Cancion");
                    ButtonType botonCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(botonCancelar);
                    alert.setHeaderText(null);
                    alert.setContentText(cancion.getTitulo());
                    TextArea textArea = new TextArea(cancion.getLetra());
                    textArea.setEditable(false);
                    textArea.setWrapText(true);
                    alert.getDialogPane().setExpandableContent(textArea);
                    alert.showAndWait();
                });
                cuadricula.add(titulo, 0, contador);
                cuadricula.add(menuButton, 2, contador);
                contador++;
            }
            panelCanciones.setContent(cuadricula);
        }
    }

}
