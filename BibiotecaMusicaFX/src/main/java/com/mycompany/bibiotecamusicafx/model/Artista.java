package com.mycompany.bibiotecamusicafx.model;

import com.mycompany.bibiotecamusicafx.utility.Constantes;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Artista {
    private final String nombre;
    private final String nacionalidad;
    private final Date fechaNacimiento;
    private ArrayList<Album> albums = new ArrayList();

    public Artista(String nombre, String nacionalidad, Date fechaNacimiento) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public void anhadirAlbum(Album album) {
        albums.add(album);
    }
    
    public int getEdad() {
        LocalDate fechaNacimientoLocalDate = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoy = LocalDate.now(); 
        return Period.between(fechaNacimientoLocalDate, hoy).getYears();
    }

    @Override
    public String toString() {
        return this.nombre + Constantes.GUION + this.nacionalidad + Constantes.GUION + getEdad();
     }
    
    
    
}
