package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Artista;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public interface ArtistaServicio {
    public abstract void guardar(Artista artista);
    public abstract void eliminar();
    public abstract void editar(Artista artista); 
    public abstract Artista getArtista(String id);
    public abstract ArrayList<Artista> obtenerTodos();
    public abstract int contarArtistas();
}
