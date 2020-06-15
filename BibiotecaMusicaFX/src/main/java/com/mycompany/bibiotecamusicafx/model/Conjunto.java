package com.mycompany.bibiotecamusicafx.model;

import com.mycompany.bibiotecamusicafx.comparador.ComparadorArtistaPorEdad;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conjunto {

    private Artista[] artistas = new Artista[Constantes.TAMANHO_MAX];
    private static int tamReal = 0;

    public static int getTamReal() {
        return tamReal;
    }

    public void guardar(Artista artista) {
        artistas[tamReal++] = artista;
    }

    public boolean sePuedeGuardar() {
        return tamReal < Constantes.TAMANHO_MAX;
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

    /**
     * Metodo para devolver los artistas ordenados segun el parametro pasado
     * @param opcion ordenar por nombre,1 o edad,2
     * @return devuelve la cadena de artistas ordenado
     */
    public String ordenarArtistasPorOpcion(int opcion) {
        String artistas = Constantes.MSG_NO_HAY_ARTISTAS;
        if (tamReal > 0) {
            if (opcion == Constantes.ORDENAR_POR_NOMBRE) {
                Arrays.sort(this.artistas, 0, tamReal);
            } else {
                ComparadorArtistaPorEdad comparadorEdad = new ComparadorArtistaPorEdad();
                Arrays.sort(this.artistas, 0, tamReal, comparadorEdad);
            }
            artistas = Constantes.ETIQUETA_NOMBRE.concat(" ")
                    .concat(Constantes.ETIQUETA_NACIONALIDAD.concat(" "))
                    .concat(Constantes.ETIQUETA_EDAD).concat(System.lineSeparator());

            for (int i = 0; i < tamReal; i++) {
                artistas = artistas.concat(this.artistas[i].toString() + System.lineSeparator());
            }
        }
        return artistas;
    }

    /**
     * Metodo para importar artistas desde un fichero txt
     */
    public void importarArtistasPorFichero() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("artistas.txt"));
            String linea;
            String nombre;
            String nacionalidad;
            Date fecha;
            String id;
            String[] partes;
            while ((linea = br.readLine()) != null) {
                if (sePuedeGuardar()) {
                    partes = linea.split(";");
                    nombre = partes[0].trim();
                    nacionalidad = partes[1].trim();
                    fecha = Date.valueOf(partes[2].trim());
                    id = partes[3].trim();
                    guardar(new Artista(nombre, nacionalidad, fecha, id));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

    }
}
