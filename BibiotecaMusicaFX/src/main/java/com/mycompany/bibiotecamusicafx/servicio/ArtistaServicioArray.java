package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Artista;
import java.util.ArrayList;


public class ArtistaServicioArray implements ArtistaServicio {
    private static ArtistaServicioArray instancia;
    private ArrayList<Artista> artistas = new ArrayList<Artista>();  //temporal hasta tener MySQL
    

    @Override
    public void guardar(Artista artista) {
        artistas.add(artista);
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Artista> obtenerTodos() {
        return artistas;
    }

    @Override
    public void encontrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
