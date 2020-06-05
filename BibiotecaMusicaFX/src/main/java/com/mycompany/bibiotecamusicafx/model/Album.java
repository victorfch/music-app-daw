package com.mycompany.bibiotecamusicafx.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public class Album {
    private String id;
    private String artistaId;
    private String titulo;
    private String genero;
    private Date fechaLanzamiento;

    public Album(String artistaId, String titulo, String genero, LocalDate fechaLanzamiento) {
        this.id = UUID.randomUUID().toString();
        this.artistaId = artistaId;
        this.titulo = titulo;
        this.genero = genero;
        this.fechaLanzamiento = Date.valueOf(fechaLanzamiento);
    }

    public String getId() {
        return id;
    }

    public String getArtistaId() {
        return artistaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }
    
    public LocalDate getFechaLanzamientoLocalDate() {
        return fechaLanzamiento.toLocalDate();
    }

    @Override
    public String toString() {
        return titulo + ";" + genero + ";" 
                + this.getFechaLanzamiento().toString();
    }
}
