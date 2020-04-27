package com.mycompany.bibiotecamusicafx;

import java.util.ArrayList;
import java.util.Date;

public class Album {
    private final String titulo;
    private final Date fechaLanzamiento;
    private ArrayList<Cancion> canciones = new ArrayList();

    public Album(String titulo, Date fechaLanzamiento) {
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
    }
    
    public void anhadirCancion(Cancion cancion) {
        canciones.add(cancion);
    }
}
