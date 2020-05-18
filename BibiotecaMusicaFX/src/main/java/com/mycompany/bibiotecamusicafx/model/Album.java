package com.mycompany.bibiotecamusicafx.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Album {
    private static ZoneId zonaPorDefecto = ZoneId.systemDefault();
    private String titulo;
    private String genero;
    private Date fechaLanzamiento;
    private int numCanciones;

    public Album(String titulo, String genero, LocalDate fechaLanzamiento, int numCanciones) {
        this.titulo = titulo;
        this.genero = genero;
        this.fechaLanzamiento = Date.from(fechaLanzamiento.atStartOfDay(zonaPorDefecto).toInstant());
        this.numCanciones = numCanciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento.toInstant().atZone(zonaPorDefecto).toLocalDate();
    }

    public int getNumCanciones() {
        return numCanciones;
    }

    @Override
    public String toString() {
        return titulo + ";" + genero + ";" 
                + this.getFechaLanzamiento().toString() + ";" + numCanciones;
    }
}
