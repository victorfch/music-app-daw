package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.model.Album;
import com.mycompany.bibiotecamusicafx.model.AlbumesWrapper;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicio;
import com.mycompany.bibiotecamusicafx.servicio.AlbumServicioMySQL;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.utility.VentanasYControladores;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
        HashMap<String, String> filtro = new HashMap<>();

        if (!nombre.getText().isEmpty()) {
            filtro.put("nombre", nombre.getText());
        }
        if (!genero.getText().isEmpty()) {
            filtro.put("genero", genero.getText());
        }
        if (filtro.size() > 0) {
            List<Album> albumesFiltrados = servicioAlbumes.buscarConFiltro(filtro);
            actualizarPanel(albumesFiltrados);
        } else {
            actualizarPanel(servicioAlbumes.obtenerTodos());
        }
    }

    public void actualizarPanel(List<Album> albumes) {
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
            Label etiquetaTitulo = new Label("Titulo");
            Label etiquetaGenero = new Label("Género");
            Label etiquetaFecha = new Label("Fecha");
            Label etiquetaAcciones = new Label();
            cuadricula.add(etiquetaTitulo, 0, 0);
            cuadricula.add(etiquetaGenero, 1, 0);
            cuadricula.add(etiquetaFecha, 2, 0);
            cuadricula.add(etiquetaAcciones, 3, 0);
            for (Album album : albumes) {
                Label titulo = new Label(album.getTitulo());
                Label genero = new Label(album.getGenero());
                Label fecha = new Label(album.getFechaLanzamiento().toString());
                MenuItem menuItem1 = new MenuItem("Editar");
                MenuItem menuItem2 = new MenuItem("Eliminar");
                MenuItem menuItem3 = new MenuItem("Ver");
                MenuButton menuButton = new MenuButton("Acciones", null, menuItem1, menuItem2, menuItem3);
                menuItem1.setOnAction((ActionEvent event) -> {
                    Parent root = null;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.VISTA_ALBUM_EDIT));
                        root = (Parent) loader.load();
                        Scene scene = new Scene(root);
                        AlbumEditController albumEditController = (AlbumEditController) VentanasYControladores.getControlador("album-editar");
                        albumEditController.iniciarDatos(album);
                        scene.getStylesheets().add("/styles/Styles.css");
                        Stage ventana = new Stage();
                        ventana.setTitle("ALBUM");
                        ventana.setScene(scene);
                        VentanasYControladores.anhadirVentana("album-editar", ventana);
                        ventana.initOwner(VentanasYControladores.getVentana("principal"));
                        ventana.initModality(Modality.WINDOW_MODAL);
                        ventana.showAndWait();
                    } catch (IOException ex) {
                        Logger.getLogger(AlbumController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                menuItem2.setOnAction((ActionEvent event) -> {
                    servicioAlbumes.eliminar(album.getId());
                    actualizarPanel(servicioAlbumes.obtenerTodos());
                });
                cuadricula.add(titulo, 0, contador);
                cuadricula.add(genero, 1, contador);
                cuadricula.add(fecha, 2, contador);
                cuadricula.add(menuButton, 3, contador);
                contador++;
            }
            contenedorAlbums.setContent(cuadricula);
        }
    }

    @FXML
    private void exportar(ActionEvent event) throws JAXBException {
        AlbumesWrapper albumesWrapper = new AlbumesWrapper();
        albumesWrapper.setAlbumes(servicioAlbumes.obtenerTodos());
        JAXBContext jaxbContext = JAXBContext.newInstance(AlbumesWrapper.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(albumesWrapper, new File("albumes.xml"));
        jaxbMarshaller.marshal(albumesWrapper, System.out);
    }
}
