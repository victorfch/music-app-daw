package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Artista;
import java.util.ArrayList;


public class ArtistaServicioArray implements ArtistaServicio {
    private static ArtistaServicioArray instancia;
    private ArrayList<Artista> artistas = new ArrayList<>();  //temporal hasta tener MySQL
    

    @Override
    public void guardar(Artista artista) {
        artistas.add(artista);
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Artista artista) {
        boolean encontrado = false;
        for (int i = 0; i < artistas.size() && !encontrado; i++) {
            if (artistas.get(i).getId().equals(artista.getId())) {
                encontrado = true;
                artistas.set(i, artista);
            }
        }
    }

    @Override
    public ArrayList<Artista> obtenerTodos() {
        return artistas;
    }

    @Override
    public int contarArtistas() {
        return artistas.size();
    }

    public static ArtistaServicioArray getInstancia() {
        if (instancia == null) {
            instancia = new ArtistaServicioArray();
        }
        return instancia;
    }

    @Override
    public Artista getArtista(String id) {
        Artista artista = null;
        boolean encontrado = false;
        for (int i = 0; i < artistas.size() && !encontrado; i++) {
            if (artistas.get(i).getId().equals(id)) {
                encontrado = true;
                artista = artistas.get(i);
            }
        }
        return artista;
    }
    
}
