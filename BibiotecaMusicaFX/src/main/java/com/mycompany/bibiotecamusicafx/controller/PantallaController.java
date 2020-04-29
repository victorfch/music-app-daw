package com.mycompany.bibiotecamusicafx.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class PantallaController {
    private static PantallaController instancia;
    private HashMap<String, Pane> listaPantallas = new HashMap<>();
    private Scene principal;

    public PantallaController(Scene principal) {
        this.principal = principal;
    }
    
    public void init() {
        try {
            listaPantallas.put(
                    "inicio",
                    (Pane) FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"))
            );
            listaPantallas.put(
                    "panelBuscarArtista",
                    (Pane) FXMLLoader.load(getClass().getResource("/fxml/PanelBuscarArtista.fxml"))
            );
        } catch (IOException ex) {
            Logger.getLogger(PantallaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void activar(String nombre) {
        principal.setRoot(listaPantallas.get(nombre));
    }
    
    public static PantallaController getInstancia(Scene escena) {
        if (instancia == null) {
            instancia = new PantallaController(escena);
        }
        
        return instancia;
    }


    
}
