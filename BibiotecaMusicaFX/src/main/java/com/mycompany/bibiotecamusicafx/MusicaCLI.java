package com.mycompany.bibiotecamusicafx;

import com.mycompany.bibiotecamusicafx.model.Album;
import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.model.Conjunto;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.utility.Utilidades;
import java.sql.Date;

public class MusicaCLI {

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
                case 6:
                    anhadirAlbum();
                    break;
                case 7:
                    eliminarAlbum();
                    break;
                case 8:
                    exportarAlbumsAFichero();
                    break;
                case 9:
                    conjunto.importarArtistasPorFichero();
                    break;
                case 10:
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
        String nacionalidad = null;
        if (conjunto.sePuedeGuardar()) {
            boolean esFechaNacimientoValida = false;
            boolean esNacionalidadValida = false;
            String nombre = Utilidades.pedirCadena(Constantes.ETIQUETA_NOMBRE).trim().toLowerCase();
            while(!esNacionalidadValida) {
                nacionalidad = Utilidades.pedirCadena(Constantes.ETIQUETA_NACIONALIDAD).trim();
                if (nacionalidad.matches("^[a-zA-Z]{3}$")) {
                    esNacionalidadValida = true;
                }
                
            }
            Date fechaDate = null;
            while (!esFechaNacimientoValida) {
                String fecha = Utilidades.pedirCadena(Constantes.ETIQUETA_FECHA_NACIMIENTO).trim();
                if (Utilidades.esFechaValida(fecha)) {
                    esFechaNacimientoValida = true;
                    fechaDate = Date.valueOf(fecha);
                }
            }
            Artista artista = new Artista(nombre, nacionalidad, fechaDate, null);
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
            Utilidades.mostrarCadena(artista.getAlbumesString());
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

    private static void anhadirAlbum() {
        boolean esFechaNacimientoValida = false;
        String nombreArtista = Utilidades.pedirCadena(Constantes.NOMBRE_ARTISTA).trim();
        String msg = Constantes.MSG_ARTISTA_NO_ENCONTRADO;
        Artista artista = conjunto.getArtistaPorNombre(nombreArtista);
        if (artista != null) {
            if (artista.sePuedenAnhadirMasAlbumes()) {
                String titulo = Utilidades.pedirCadena("Titulo").trim();
                String genero = Utilidades.pedirCadena("Genero").trim();
                Date fechaLanzamiento = null;
                while (!esFechaNacimientoValida) {
                    String fecha = Utilidades.pedirCadena("Fecha de lanzamiento(yyyy-mm-dd)").trim();
                    if (Utilidades.esFechaValida(fecha)) {
                        esFechaNacimientoValida = true;
                        fechaLanzamiento = Date.valueOf(fecha);
                    }
                }
                Album album = new Album(artista.getId(), titulo, genero, fechaLanzamiento, null);
                artista.anhadirAlbum(album);
                msg = "Album añadido correctamente";
            } else {
                msg = "No se pueden añadir mas albumes a ese artista";
            }
        }
        Utilidades.mostrarCadena(msg);
    }

    private static void eliminarAlbum() {
        String nombre = Utilidades.pedirCadena(Constantes.NOMBRE_ARTISTA).trim();
        String mensaje = Constantes.MSG_ARTISTA_NO_ENCONTRADO;
        Artista artista = conjunto.getArtistaPorNombre(nombre);
        if (artista != null) {
            String titulo = Utilidades.pedirCadena("Titulo del album");
            mensaje = "Album no encontrado";
            if (artista.eliminarAlbum(titulo)) {
                mensaje = "Album eliminado con exito";
            }
        }
        Utilidades.mostrarCadena(mensaje);
    }

    private static void exportarAlbumsAFichero() {
        String nombre = Utilidades.pedirCadena(Constantes.NOMBRE_ARTISTA).trim();
        String mensaje = Constantes.MSG_ARTISTA_NO_ENCONTRADO;
        Artista artista = conjunto.getArtistaPorNombre(nombre);
        if (artista != null) {
            artista.exportarAlbums();
            mensaje = "Exportacion realizada con exito";
        }
        Utilidades.mostrarCadena(mensaje);
    }
}
