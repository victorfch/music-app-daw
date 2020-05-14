package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Artista;

public class ArtistaServicioImplCLI implements ArtistaServicioCLI {

    private static ArtistaServicioImplCLI instancia;
    private Artista[] artistas = new Artista[20];
    private static int tamReal = 0;

    @Override
    public void guardar(Artista artista) {
        artistas[tamReal++] = artista;
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Artista artista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Artista getArtista(String id) {
        boolean encontrado = false;
        Artista artista = null;
        for (int i = 0; i < tamReal && !encontrado; i++) {
            if (artistas[i].getId().equals(id)) {
                encontrado = true;
                artista = artistas[i];
            }
        }
        return artista;
    }

    @Override
    public Artista[] obtenerTodos() {
        return artistas;
    }

    @Override
    public int contarArtistas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static int getTamReal() {
        return tamReal;
    }

    public static ArtistaServicioImplCLI getInstancia() {
        if (instancia == null) {
            instancia = new ArtistaServicioImplCLI();
        }
        return instancia;
    }

}
