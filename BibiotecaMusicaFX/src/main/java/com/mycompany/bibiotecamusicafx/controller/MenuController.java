//package com.mycompany.bibiotecamusicafx.controller;
//
//import java.io.IOException;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//
//public abstract class MenuController {
//    private Scene escenaInicio;
//    private Scene escenaBuscarArtista;
//    
//    public MenuController() throws IOException {
//        escenaInicio = getEscena("Scene");
//        escenaBuscarArtista = getEscena("PanelBuscarArtista");
//    }
//
//    public Scene getEscenaInicio() {
//        return escenaInicio;
//    }
//    
//    public Scene getEscenaBuscarArtista() {
//        return escenaBuscarArtista;
//    }  
//    
//    
//    private Scene getEscena(String nombre) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + nombre + ".fxml"));
//        return new Scene(root);
//    }
//    
//    
//}
