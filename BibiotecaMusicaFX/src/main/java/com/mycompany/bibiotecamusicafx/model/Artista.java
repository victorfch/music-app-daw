package com.mycompany.bibiotecamusicafx.model;

import com.mycompany.bibiotecamusicafx.utility.Constantes;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class Artista implements Comparable<Artista> {

    private static ZoneId zonaPorDefecto = ZoneId.systemDefault();
    private String id;
    private String nombre;
    private String nacionalidad;
    private Date fechaNacimiento;
    private int tamRealAlbums = 0;
    private Album[] albums = new Album[Constantes.TAMANHO_MAX];

    public Artista(String nombre, String nacionalidad, LocalDate fechaNacimiento) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = Date.from(fechaNacimiento.atStartOfDay(zonaPorDefecto).toInstant());
    }

    public String getId() {
        return id;
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
        LocalDate fechaNacimientoLocalDate = fechaNacimiento.toInstant().atZone(zonaPorDefecto).toLocalDate();
        LocalDate hoy = LocalDate.now();
        return Period.between(fechaNacimientoLocalDate, hoy).getYears();
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento.toInstant().atZone(zonaPorDefecto).toLocalDate();
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = Date.from(fechaNacimiento.atStartOfDay(zonaPorDefecto).toInstant());
    }
    
    public String getAlbumesString() {
        String albumes = "No tiene ningun album relacionado";
        if (tamRealAlbums > 0) {
            albumes = "Titulo  Genero Fecha de lanzamiento Canciones: " + System.lineSeparator();
            for (int i = 0; i < tamRealAlbums; i++) {
                albumes += albums[i].toString() + System.lineSeparator();
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
    
    public void importarAlbums() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return this.nombre + Constantes.GUION + this.nacionalidad + Constantes.GUION + getEdad();
    }

    @Override
    public int compareTo(Artista o) {
        return this.nombre.compareTo(o.getNombre());
    }
}
