package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Artista;
import java.util.List;
import java.util.Map;

/**
 *
 * @author victor
 */
public interface ArtistaServicio {
    public abstract void guardar(Artista artista);
    public abstract void eliminar(String id);
    public abstract void editar(Artista artista);
    public abstract List<Artista> buscarConFiltro(Map filtro);
    public abstract Artista getArtista(String id);
    public abstract List<Artista> obtenerTodos();
    public abstract int contarArtistas();
    public abstract void cerrarSesion();
}
