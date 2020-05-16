package com.mycompany.bibiotecamusicafx;

import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.model.Conjunto;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.utility.Utilidades;
import java.time.LocalDate;

public class MusicaCLI {

    /*
    IMPORTANTE!!! 
    Aclaracion para el profe, en la clase Artista tengo el atributo id. 
    Esto es porque ya habia avanzado en la version con el javafx y el identificador era ese,
    A la hora de buscar o eliminar en esta version esta siendo con el campo nombre, que lo tomaremos como unico.
    Podria cambiar el codigo y dejartelo como tengo en la version mas avanzada pero creo que es un poco
    mas engorroso para ti buscar por un UUID que por un nombre, por eso para esta version pensemos que
    el campo nombre es unico
     */

    public static Conjunto conjunto = new Conjunto();

    public static void main(String[] args) {
        boolean seguir = true;
        while (seguir) {
            int opcion = Integer.parseInt(Utilidades.pedirCadena(Utilidades.menuEstilizadoCLI()));
            switch (opcion) {
                case 1:
                    anhadirArtista();
                    break;
                case 2:
                    Utilidades.mostrarCadena(conjunto.ordenarArtistasPorOpcion(Constantes.ORDENAR_POR_NOMBRE));
                    break;
                case 3:
                    Utilidades.mostrarCadena(conjunto.ordenarArtistasPorOpcion(Constantes.ORDENAR_POR_EDAD));
                    break;
                case 4:
                    verArtista();
                    break;
                case 5:
                    eliminarArtista();
                    break;
                case 9:
                    seguir = false;
                    Utilidades.mostrarCadena(Constantes.MSG_SALIR);
                    break;
                default:
                    Utilidades.mostrarCadena(Constantes.MSG_ERROR);
            }
        }
    }

    private static void anhadirArtista() {
        String mensaje = "No se pueden añadir mas artistas";
        if (conjunto.sePuedeGuardar()) {
            boolean esFechaNacimientoValida = false;
            String nombre = Utilidades.pedirCadena(Constantes.ETIQUETA_NOMBRE).trim().toLowerCase();
            String nacionalidad = Utilidades.pedirCadena(Constantes.ETIQUETA_NACIONALIDAD).trim();
            LocalDate fechaDate = null;
            while (!esFechaNacimientoValida) {
                String fecha = Utilidades.pedirCadena(Constantes.ETIQUETA_FECHA_NACIMIENTO).trim();
                if (Utilidades.esFechaValida(fecha)) {
                    esFechaNacimientoValida = true;
                    fechaDate = Utilidades.conversorStringToLocalDate(fecha);
                }
            }
            Artista artista = new Artista(nombre, nacionalidad, fechaDate);
            conjunto.guardar(artista);
            mensaje = "Artista guardado correctamente";
        }
        Utilidades.mostrarCadena(mensaje);
    }

    private static void verArtista() {
        String nombre = Utilidades.pedirCadena(Constantes.ETIQUETA_NOMBRE).trim();
        Artista artista = conjunto.getArtistaPorNombre(nombre);
        if (artista == null) {
            Utilidades.mostrarCadena(Constantes.MSG_ARTISTA_NO_ENCONTRADO);
        } else {
            Utilidades.mostrarCadena("ID: " + artista.getId());
            Utilidades.mostrarCadena(Constantes.ETIQUETA_NOMBRE 
                    + Constantes.DOS_PUNTOS_ESPACIO + artista.getNombre());
            Utilidades.mostrarCadena(Constantes.ETIQUETA_NACIONALIDAD 
                    + Constantes.DOS_PUNTOS_ESPACIO + artista.getNacionalidad());
            Utilidades.mostrarCadena("Fecha nacimiento(yyyy/mm/dd): " 
                    + artista.getFechaNacimiento().toString());
            Utilidades.mostrarCadena(Constantes.ETIQUETA_EDAD + Constantes.DOS_PUNTOS_ESPACIO
                    + artista.getEdad());
            Utilidades.mostrarCadena("Albumes: ");
            if (artista.getTamRealAlbums() == 0) {
                Utilidades.mostrarCadena("No tiene ningun album relacionado");
            }
        }
    }

    private static void eliminarArtista() {
        String nombre = Utilidades.pedirCadena(Constantes.ETIQUETA_NOMBRE).trim();
        String mensaje = Constantes.MSG_ARTISTA_NO_ENCONTRADO;
        if (conjunto.eliminar(nombre)) {
            mensaje = Constantes.MSG_ARTISTA_ELIMINADO;
        }
        Utilidades.mostrarCadena(mensaje);
    }
}
