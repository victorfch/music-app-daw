package com.mycompany.bibiotecamusicafx.utility;

import java.util.HashMap;
import javafx.stage.Stage;

public abstract class Ventanas {
    private static HashMap<String, Stage> ventanas = new HashMap<>();
    
    public static void anhadir(String nombre, Stage Ventana) {
        ventanas.put(nombre, Ventana);
    }
    public static Stage get(String nombre) {
        return ventanas.get(nombre);
    }
    
    
    
}
