package com.mycompany.bibiotecamusicafx;

import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicioImplCLI;
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
    
    public static ArtistaServicioImplCLI artistaServicio = ArtistaServicioImplCLI.getInstancia();

    public static void main(String[] args) {
        boolean seguir = true;
        while (seguir) {
            int opcion = Integer.parseInt(Utilidades.pedirCadena(Utilidades.menuEstilizadoCLI()));
            switch (opcion) {
                case 1:
                    anhadirArtista();
                    break;
                case 2:
                    mostrarArtistas(); 
                    break;
                case 3:
                    verArtista();
                    break;
                case 4:
                    eliminarArtista();
                    break;
                case 8:
                    seguir = false;
                    Utilidades.mostrarCadena(Constantes.MSG_SALIR);
                    break;
                default:
                    Utilidades.mostrarCadena(Constantes.MSG_ERROR);
            }
        }
    }

    private static void anhadirArtista() {
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
        artistaServicio.guardar(artista);
    }

    private static void mostrarArtistas() {
        Utilidades.mostrarCadena(
                Constantes.ETIQUETA_NOMBRE.concat(" ")
                .concat(Constantes.ETIQUETA_NACIONALIDAD.concat(" "))
                .concat(Constantes.ETIQUETA_EDAD)
        );
        Artista[] artistas = artistaServicio.obtenerTodos();
        if (artistaServicio.contarArtistas() > 0) {
            for (int i = 0; i < ArtistaServicioImplCLI.getTamReal(); i++) {
                System.out.println(artistas[i]);
            }
        } else {
            Utilidades.mostrarCadena(Constantes.MSG_NO_HAY_ARTISTAS);
        }
    }

    private static void verArtista() {
        String nombre = Utilidades.pedirCadena(Constantes.ETIQUETA_NOMBRE).trim();
        Artista artista = artistaServicio.getArtistaPorNombre(nombre);
        if (artista == null) {
            Utilidades.mostrarCadena(Constantes.MSG_ARTISTA_NO_ENCONTRADO);
        } else {
            Utilidades.mostrarCadena(artista.infoCompleta());
        }
    }

    private static void eliminarArtista() {
        String nombre = Utilidades.pedirCadena(Constantes.ETIQUETA_NOMBRE).trim();
        if (artistaServicio.eliminar(nombre)) {
            Utilidades.mostrarCadena(Constantes.MSG_ARTISTA_ELIMINADO);
        } else {
            Utilidades.mostrarCadena(Constantes.MSG_ARTISTA_NO_ENCONTRADO);
        }
    }
}
