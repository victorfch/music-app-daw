package com.mycompany.bibiotecamusicafx.controller.comparadores;

import com.mycompany.bibiotecamusicafx.model.Artista;
import java.util.Comparator;

public class OrdenarArtistaPorEdad implements Comparator<Artista> {

    @Override
    public int compare(Artista a1, Artista a2) {
        return a1.getEdad() - a2.getEdad();
    }


    
}
