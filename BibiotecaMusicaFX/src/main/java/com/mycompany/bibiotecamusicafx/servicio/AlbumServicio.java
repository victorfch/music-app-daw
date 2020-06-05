package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Album;
import java.util.List;
import java.util.Map;

public interface AlbumServicio {
    public abstract void guardar(Album album);
    public abstract void eliminar(String id);
    public abstract void editar(Album album);
    public abstract List<Album> buscarConFiltro(Map filtro);
    public abstract Album getAlbum(String id);
    public abstract Map<String, Album> obtenerTodos();
    public abstract int contarAlbums();
    public abstract void cerrarSesion();
}
