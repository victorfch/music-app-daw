package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.model.Album;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicio;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicioMySQL;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.utility.VentanasYControladores;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlbumController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField genero;
    @FXML
    private ScrollPane contenedorAlbums;

    ArtistaServicio servicioArtistas;
    AlbumServicio servicioAlbumes;

    public AlbumController() throws SQLException {
        servicioAlbumes = AlbumServicioMySQL.getServicioMySQL();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VentanasYControladores.anhadirControlador("album", this);
        actualizarPanel(servicioAlbumes.obtenerTodos());
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Constantes.VISTA_ALBUM_EDIT));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage ventana = new Stage();
        ventana.setTitle("ALBUM");
        ventana.setScene(scene);
        VentanasYControladores.anhadirVentana("album-editar", ventana);
        ventana.initOwner(VentanasYControladores.getVentana("principal"));
        ventana.initModality(Modality.WINDOW_MODAL);
        ventana.showAndWait();
    }

    @FXML
    private void buscarPorFiltro(ActionEvent event) {
    }

    public void actualizarPanel(Map<String, Album> albumes) {
        if (albumes.isEmpty()) {
            contenedorAlbums.setContent(null);
            Label warning = new Label(Constantes.MSG_NO_HAY_ALBUMES);
            warning.setTextFill(Paint.valueOf(Constantes.COLOR_ROJO));
            warning.setPadding(new Insets(5, 0, 0, 10));
            contenedorAlbums.setContent(warning);
        } else {
            GridPane cuadricula = new GridPane();
            cuadricula.setHgap(30);
            cuadricula.setVgap(10);
            cuadricula.setPadding(new Insets(5, 0, 0, 10));
            int contador = 1;
            Label etiquetaNombreArtista = new Label("Artista");
            Label etiquetaTitulo = new Label("Titulo");
            Label etiquetaGenero = new Label("Género");
            Label etiquetaFecha = new Label("Fecha");
            Label etiquetaAcciones = new Label();
            cuadricula.add(etiquetaNombreArtista, 0, 0);
            cuadricula.add(etiquetaTitulo, 1, 0);
            cuadricula.add(etiquetaGenero, 2, 0);
            cuadricula.add(etiquetaFecha, 3, 0);
            cuadricula.add(etiquetaAcciones, 4, 0);

            for (Map.Entry<String, Album> entry : albumes.entrySet()) {
                Label nombreArtista = new Label(entry.getKey());
                Label titulo = new Label(entry.getValue().getTitulo());
                Label genero = new Label(entry.getValue().getGenero());
                Label fecha = new Label(entry.getValue().getFechaLanzamiento().toString());
                MenuItem menuItem1 = new MenuItem("Editar");
                MenuItem menuItem2 = new MenuItem("Eliminar");
                MenuItem menuItem3 = new MenuItem("Ver");
                MenuButton menuButton = new MenuButton("Acciones", null, menuItem1, menuItem2, menuItem3);
                cuadricula.add(nombreArtista, 0, contador);
                cuadricula.add(titulo, 1, contador);
                cuadricula.add(genero, 2, contador);
                cuadricula.add(fecha, 3, contador);
                cuadricula.add(menuButton, 4, contador);
                contador++;   
            }
            contenedorAlbums.setContent(cuadricula);
        }
    }

}
