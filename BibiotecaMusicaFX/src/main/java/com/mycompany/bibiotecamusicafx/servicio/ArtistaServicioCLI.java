package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Artista;

public interface ArtistaServicioCLI {
    public abstract void guardar(Artista artista);
    public abstract void eliminar();
    public abstract void editar(Artista artista);
    public abstract Artista getArtista(String id);
    public abstract Artista[] obtenerTodos();
    public abstract int contarArtistas();
}
