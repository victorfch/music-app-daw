package com.mycompany.bibiotecamusicafx.model;

import com.mycompany.bibiotecamusicafx.utility.AdaptadorFecha;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;
import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Artista implements Comparable<Artista> {

    private String id;
    private String nombre;
    private String nacionalidad;

    @XmlJavaTypeAdapter(AdaptadorFecha.class)
    private Date fechaNacimiento;
    
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

    public LocalDate getFechaNacimientoLocalDate() {
        return fechaNacimiento.toLocalDate();
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
}
