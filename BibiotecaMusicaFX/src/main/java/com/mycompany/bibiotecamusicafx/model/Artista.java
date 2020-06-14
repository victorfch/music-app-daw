package com.mycompany.bibiotecamusicafx.model;

import com.mycompany.bibiotecamusicafx.utility.AdaptadorFecha;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Artista implements Comparable<Artista> {

    private String id;
    private String nombre;
    private String nacionalidad;

    @XmlJavaTypeAdapter(AdaptadorFecha.class)
    private Date fechaNacimiento;
    @XmlTransient
    private int tamRealAlbums = 0;      //para la version cli
    @XmlTransient
    private Album[] albums = new Album[Constantes.TAMANHO_MAX];     //para la version cli
    
    public Artista() {}

    public Artista(String nombre, String nacionalidad, Date fechaNacimiento, String id) {
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getEdad() {
        LocalDate fechaNacimientoLocalDate = fechaNacimiento.toLocalDate();
        LocalDate hoy = LocalDate.now();
        return Period.between(fechaNacimientoLocalDate, hoy).getYears();
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return this.nombre + Constantes.GUION + this.nacionalidad + Constantes.GUION + getEdad();
    }

    @Override
    public int compareTo(Artista o) {
        return this.nombre.compareTo(o.getNombre());
    }
    
    public String getAlbumesString() {
        String albumes = "No tiene ningun album relacionado";
        if (tamRealAlbums > 0) {
            albumes = "Titulo;Genero;Fecha de lanzamiento;Canciones" + System.lineSeparator();
            for (int i = 0; i < tamRealAlbums; i++) {
                albumes = albumes.concat(albums[i].toString() + System.lineSeparator());
            }
        }
        return albumes;
    }
    
    public boolean sePuedenAnhadirMasAlbumes() {
        return tamRealAlbums < Constantes.TAMANHO_MAX;
    }
    
    public void anhadirAlbum(Album album) {
        albums[tamRealAlbums++] = album;
    }
    
    public boolean eliminarAlbum(String titulo) {
        boolean encontrado = false;
        for (int i = 0; i < tamRealAlbums; i++) {
            if ((albums[i].getTitulo().equals(titulo)) && (tamRealAlbums - i > 1)) {
                albums[i] = albums[i + 1];
                encontrado = true;
            } else if (albums[i].getTitulo().equals(titulo) && (tamRealAlbums - i == 1)) {
                encontrado = true;
            } else if (encontrado && (tamRealAlbums - i > 1)) {
                albums[i] = albums[i + 1];
            }
        }
        if (encontrado) {
            tamRealAlbums--;
        }
        return encontrado;
    }
    
    public void exportarAlbums() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("albums.txt"));
            String msg = Constantes.MSG_NO_HAY_ALBUMES;
            if (tamRealAlbums > 0) {
                msg = "";
                for (int i = 0; i < tamRealAlbums; i++) {
                    msg = msg.concat(albums[i].toString() + System.lineSeparator());
                }
                bw.write(msg);
            }   
        } catch (IOException ex) {
            Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
