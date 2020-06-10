package com.mycompany.bibiotecamusicafx.model;

import java.sql.Date;
import java.util.UUID;

public class Album {
    private String artistaId;
    private String titulo;
    private String genero;
    private Date fechaLanzamiento;
    private String id;

    public Album(String artistaId, String titulo, String genero, Date fechaLanzamiento, String id) {
        this.artistaId = artistaId;
        this.titulo = titulo;
        this.genero = genero;
        this.fechaLanzamiento = fechaLanzamiento;
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
    }

    public String getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(String artistaId) {
        this.artistaId = artistaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
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
