package com.mycompany.bibiotecamusicafx;

public class Cancion {
    private final String nombre;
    private final String artista;
    private final String genero;
    private final String letra;

    public Cancion(String nombre, String artista, String genero, String letra) {
        this.nombre = nombre;
        this.artista = artista;
        this.genero = genero;
        this.letra = letra;
    }

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public String getLetra() {
        return letra;
    }
}
