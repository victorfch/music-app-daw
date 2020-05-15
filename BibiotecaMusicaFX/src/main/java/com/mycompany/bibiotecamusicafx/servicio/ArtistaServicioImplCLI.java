package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.utility.Constantes;

public class ArtistaServicioImplCLI implements ArtistaServicioCLI {

    private static ArtistaServicioImplCLI instancia;
    private Artista[] artistas = new Artista[Constantes.TAMANHO_MAX];
    private static int tamReal = 0;

    @Override
    public void guardar(Artista artista) {
        artistas[tamReal++] = artista;
    }

    @Override
    public boolean eliminar(String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < tamReal; i++) {
            if ((artistas[i].getNombre().equals(nombre)) && (tamReal - i > 1)) {
                artistas[i] = artistas[i+1];
                encontrado = true;
            } else if (artistas[i].getNombre().equals(nombre) && (tamReal - i == 1)) {
                encontrado = true;
            } else if (encontrado){
                artistas[i] = artistas[i+1];       
            }
        }
        if (encontrado) {
            tamReal--;
        }
        return encontrado;
    }

    @Override
    public Artista getArtistaPorNombre(String nombre) {
        boolean encontrado = false;
        Artista artista = null;
        for (int i = 0; i < tamReal && !encontrado; i++) {
            if (artistas[i].getNombre().equals(nombre)) {
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
        return tamReal;
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
