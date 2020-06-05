package com.mycompany.bibiotecamusicafx.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public class Album {
    private String artistaId;
    private String titulo;
    private String genero;
    private Date fechaLanzamiento;
    private String id;

    public Album(String artistaId, String titulo, String genero, LocalDate fechaLanzamiento, String id) {
        this.artistaId = artistaId;
        this.titulo = titulo;
        this.genero = genero;
        this.fechaLanzamiento = Date.valueOf(fechaLanzamiento);
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
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
    
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return titulo + ";" + genero + ";" 
                + this.getFechaLanzamiento().toString();
    }
}
