package com.mycompany.bibiotecamusicafx.utility;

import java.util.HashMap;
import javafx.stage.Stage;

public abstract class VentanasYControladores {
    private static HashMap<String, Stage> ventanas = new HashMap<>();
    private static HashMap<String, Object> controladores = new HashMap<>();
    
    public static void anhadirVentana(String nombre, Stage Ventana) {
        ventanas.put(nombre, Ventana);
    }
    
    public static Stage getVentana(String nombre) {
        return ventanas.get(nombre);
    }
    
    public static void anhadirControlador(String nombre, Object controlador) {
        controladores.put(nombre, controlador);
    }
    
    public static Object getControlador(String nombre) {
        return controladores.get(nombre);
    }
    
}
