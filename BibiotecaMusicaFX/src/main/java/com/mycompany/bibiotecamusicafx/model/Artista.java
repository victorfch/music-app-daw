package com.mycompany.bibiotecamusicafx.model;

import com.mycompany.bibiotecamusicafx.utility.Constantes;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Artista {
    private static ZoneId defaultZoneId = ZoneId.systemDefault();
    private String id;
    private String nombre;
    private String nacionalidad;
    private Date fechaNacimiento;
    private ArrayList<Album> albums = new ArrayList();

    public Artista(String nombre, String nacionalidad, LocalDate fechaNacimiento) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = Date.from(fechaNacimiento.atStartOfDay(defaultZoneId).toInstant());
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
        LocalDate fechaNacimientoLocalDate = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoy = LocalDate.now(); 
        return Period.between(fechaNacimientoLocalDate, hoy).getYears();
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = Date.from(fechaNacimiento.atStartOfDay(defaultZoneId).toInstant());
    }

    public void anhadirAlbum(Album album) {
        albums.add(album);
    }

    @Override
    public String toString() {
        return this.nombre + Constantes.GUION + this.nacionalidad + Constantes.GUION + getEdad();
     }
    
    
    
}
