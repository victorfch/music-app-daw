package com.mycompany.bibiotecamusicafx.utility;

import java.util.HashMap;
import javafx.stage.Stage;

public abstract class VentanasYControladores {

    private static HashMap<String, Stage> ventanas = new HashMap<>();
    private static HashMap<String, Object> controladores = new HashMap<>();

    private VentanasYControladores() {
    }

    public static void anhadirVentana(String nombre, Stage ventana) {
        ventanas.put(nombre, ventana);
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
