package com.mycompany.bibiotecamusicafx.model;

import com.mycompany.bibiotecamusicafx.utility.Constantes;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Artista {

    private static ZoneId defaultZoneId = ZoneId.systemDefault();
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
        albums[tamRealAlbums++] = album;
    }

    @Override
    public String toString() {
        return this.nombre + Constantes.GUION + this.nacionalidad + Constantes.GUION + getEdad();
    }

    public String infoCompleta() {
        String info = "ID: " + this.id + System.lineSeparator()
                + Constantes.ETIQUETA_NOMBRE + Constantes.DOS_PUNTOS_ESPACIO
                + this.nombre + System.lineSeparator()
                + Constantes.ETIQUETA_NACIONALIDAD + Constantes.DOS_PUNTOS_ESPACIO
                + this.nacionalidad + System.lineSeparator()
                + "Fecha nacimiento(yyyy/mm/dd)" + Constantes.DOS_PUNTOS_ESPACIO
                + this.fechaNacimiento.toString() + System.lineSeparator()
                + Constantes.ETIQUETA_EDAD + Constantes.DOS_PUNTOS_ESPACIO 
                + getEdad() + System.lineSeparator()
                + "Albumes: " + System.lineSeparator();
        if (tamRealAlbums == 0) {
            info += "No tiene ningun album relacionado" + System.lineSeparator();
        }
        return info;
    }
}
