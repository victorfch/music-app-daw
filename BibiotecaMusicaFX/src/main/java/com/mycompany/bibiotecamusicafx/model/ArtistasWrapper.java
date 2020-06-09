package com.mycompany.bibiotecamusicafx.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "artistas")
public class ArtistasWrapper {
    private List<Artista> artistas;

    public List<Artista> getArtistas() {
        return artistas;
    }

    @XmlElement(name = "artista")
    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }
    
}
