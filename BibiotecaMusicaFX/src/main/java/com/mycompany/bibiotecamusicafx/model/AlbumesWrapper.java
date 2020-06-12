package com.mycompany.bibiotecamusicafx.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "albumes")
public class AlbumesWrapper {
    private List<Album> albumes;

    public List<Album> getAlbumes() {
        return albumes;
    }

    @XmlElement(name = "album")
    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }
}
