package com.mycompany.bibiotecamusicafx.model;

import java.sql.Blob;

public class Cancion {
    private final String titulo;
    private final String idArtista;
    private final String idAlbum;
    private final Blob letra;

    public Cancion(String titulo, String idArtista, String idAlbum, Blob letra) {
        this.titulo = titulo;
        this.idArtista = idArtista;
        this.idAlbum = idAlbum;
        this.letra = letra;
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
    
    public Blob getLetra() {
        return letra;
    }
}
