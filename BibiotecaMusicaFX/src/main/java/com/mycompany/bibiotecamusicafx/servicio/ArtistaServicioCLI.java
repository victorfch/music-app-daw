package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Artista;

public interface ArtistaServicioCLI {
    public abstract void guardar(Artista artista);
    public abstract boolean eliminar(String nombre);
    public abstract Artista getArtistaPorNombre(String nombre);
    public abstract Artista[] obtenerTodos();
    public abstract int contarArtistas();
}
