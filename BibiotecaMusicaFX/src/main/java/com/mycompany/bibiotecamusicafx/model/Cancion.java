package com.mycompany.bibiotecamusicafx.model;

import java.util.UUID;

public class Cancion {
    private final String titulo;
    private final String idArtista;
    private final String idAlbum;
    private final String letra;
    private final String id;

    public Cancion(String titulo, String idArtista, String idAlbum, String letra, String id) {
        this.titulo = titulo;
        this.idArtista = idArtista;
        this.idAlbum = idAlbum;
        this.letra = letra;
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public String getIdAlbum() {
        return idAlbum;
    }
    
    public String getLetra() {
        return letra;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "titulo= " + this.titulo + " ; idArtista= " + this.idArtista
                + " ; idAlbum = " + this.idAlbum + " ; letra = "
                + this.letra;
    }
    
}
