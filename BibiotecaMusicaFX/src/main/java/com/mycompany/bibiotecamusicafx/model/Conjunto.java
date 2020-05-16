package com.mycompany.bibiotecamusicafx.model;

import com.mycompany.bibiotecamusicafx.controller.comparadores.OrdenarArtistaPorEdad;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import java.util.Arrays;

public class Conjunto {

    private Artista[] artistas = new Artista[Constantes.TAMANHO_MAX];
    private static int tamReal = 0;

    public void guardar(Artista artista) {
        artistas[tamReal++] = artista;
    }

    public boolean sePuedeGuardar() {
        return tamReal != Constantes.TAMANHO_MAX;
    }

    public boolean eliminar(String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < tamReal; i++) {
            if ((artistas[i].getNombre().equals(nombre)) && (tamReal - i > 1)) {
                artistas[i] = artistas[i + 1];
                encontrado = true;
            } else if (artistas[i].getNombre().equals(nombre) && (tamReal - i == 1)) {
                encontrado = true;
            } else if (encontrado && (tamReal - i > 1)) {
                artistas[i] = artistas[i + 1];
            }
        }
        if (encontrado) {
            tamReal--;
        }
        return encontrado;
    }

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

    public String ordenarArtistasPorOpcion(int opcion) {
        String artistas = Constantes.MSG_NO_HAY_ARTISTAS;
        if (tamReal > 0) {
            if (opcion == Constantes.ORDENAR_POR_NOMBRE) {
                Arrays.sort(this.artistas, 0, tamReal);
            } else {
                OrdenarArtistaPorEdad ordenacion = new OrdenarArtistaPorEdad();
                Arrays.sort(this.artistas, 0, tamReal, ordenacion);
            }
            artistas = Constantes.ETIQUETA_NOMBRE.concat(" ")
                    .concat(Constantes.ETIQUETA_NACIONALIDAD.concat(" "))
                    .concat(Constantes.ETIQUETA_EDAD).concat(System.lineSeparator());

            for (int i = 0; i < tamReal; i++) {
                artistas += this.artistas[i].toString() + System.lineSeparator();
            }
        }
        return artistas;
    }

}
