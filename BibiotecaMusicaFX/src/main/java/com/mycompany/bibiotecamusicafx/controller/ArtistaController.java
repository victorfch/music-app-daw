package com.mycompany.bibiotecamusicafx.controller;

import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicio;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicioMySQL;
import com.mycompany.bibiotecamusicafx.utility.VentanasYControladores;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
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

public class ArtistaController implements Initializable {

    private ArtistaServicio servicioArtistas;

    @FXML
    private TextField nombre;
    @FXML
    private TextField nacionalidad;
    @FXML
    private ScrollPane contenedorArtistas;

    public ArtistaController() throws SQLException {
        this.servicioArtistas = ArtistaServicioMySQL.getServicioMySQL();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VentanasYControladores.anhadirControlador("artista", this);
        actualizarPanel(servicioArtistas.obtenerTodos());
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Constantes.VISTA_ARTISTA_EDIT));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage ventana = new Stage();
        ventana.setTitle("ARTISTA");
        ventana.setScene(scene);
        VentanasYControladores.anhadirVentana("artista-editar", ventana);
        ventana.initOwner(VentanasYControladores.getVentana("principal"));
        ventana.initModality(Modality.WINDOW_MODAL);
        ventana.showAndWait();
    }

    public void actualizarPanel(List artistas) {
        contenedorArtistas.setContent(null);
        if (artistas.isEmpty()) {
            Label warning = new Label(Constantes.MSG_NO_HAY_ARTISTAS);
            warning.setTextFill(Paint.valueOf(Constantes.COLOR_ROJO));
            warning.setPadding(new Insets(5, 0, 0, 10));
            contenedorArtistas.setContent(warning);
        } else {
            GridPane cuadricula = new GridPane();
            cuadricula.setHgap(30);
            cuadricula.setVgap(10);
            cuadricula.setPadding(new Insets(5, 0, 0, 10));
            int contador = 1;
            Label etiquetaNombre = new Label(Constantes.ETIQUETA_NOMBRE);
            Label etiquetaNacionalidad = new Label(Constantes.ETIQUETA_NACIONALIDAD);
            Label etiquetaEdad = new Label(Constantes.ETIQUETA_EDAD);
            Label etiquetaAcciones = new Label();
            cuadricula.add(etiquetaNombre, 0, 0);
            cuadricula.add(etiquetaNacionalidad, 1, 0);
            cuadricula.add(etiquetaEdad, 2, 0);
            cuadricula.add(etiquetaAcciones, 3, 0);
            for (Iterator it = artistas.iterator(); it.hasNext();) {
                Artista artista = (Artista) it.next();
                Label nombre = new Label(artista.getNombre());
                Label nacionalidad = new Label(artista.getNacionalidad());
                Label edad = new Label(String.valueOf(artista.getEdad()));
                MenuItem menuItem1 = new MenuItem("Editar");
                MenuItem menuItem2 = new MenuItem("Eliminar");
                MenuItem menuItem3 = new MenuItem("Ver");
                MenuButton menuButton = new MenuButton("Acciones", null, menuItem1, menuItem2, menuItem3);
                menuItem1.setOnAction((ActionEvent event) -> {
                    Parent root = null;
                    try {
                        FXMLLoader loader = new FXMLLoader(ArtistaController.this.getClass().getResource(Constantes.VISTA_ARTISTA_EDIT));
                        root = (Parent) loader.load();
                        Scene scene = new Scene(root);
                        ArtistaEditController editarControlador = (ArtistaEditController) VentanasYControladores.getControlador("artista-editar");
                        editarControlador.iniciarDatos(artista);
                        scene.getStylesheets().add("/styles/Styles.css");
                        Stage ventana = new Stage();
                        ventana.setTitle("ARTISTA");
                        ventana.setScene(scene);
                        VentanasYControladores.anhadirVentana("artista-editar", ventana);
                        ventana.initOwner(VentanasYControladores.getVentana("principal"));
                        ventana.initModality(Modality.WINDOW_MODAL);
                        ventana.showAndWait();
                    } catch (IOException ex) {
                        Logger.getLogger(ArtistaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                menuItem2.setOnAction((ActionEvent event) -> {
                    servicioArtistas.eliminar(artista.getId());
                    actualizarPanel(servicioArtistas.obtenerTodos());
                });
                menuItem3.setOnAction((ActionEvent event) -> {
                    System.out.println("Ver");
                });
                cuadricula.add(nombre, 0, contador);
                cuadricula.add(nacionalidad, 1, contador);
                cuadricula.add(edad, 2, contador);
                cuadricula.add(menuButton, 3, contador);
                contador++;
            }
            contenedorArtistas.setContent(cuadricula);
        }
    }

    @FXML
    private void buscarPorFiltro(ActionEvent event) {
        HashMap<String, String> filtro = new HashMap<>();

        if (!nombre.getText().isEmpty()) {
            filtro.put("nombre", nombre.getText());
        }
        if (!nacionalidad.getText().isEmpty()) {
            filtro.put("nacionalidad", nacionalidad.getText());
        }
        if (filtro.size() > 0) {
            List<Artista> artistasFiltrados = servicioArtistas.buscarConFiltro(filtro);
            actualizarPanel(artistasFiltrados);
        } else {
            actualizarPanel(servicioArtistas.obtenerTodos());
        }
    }

}
