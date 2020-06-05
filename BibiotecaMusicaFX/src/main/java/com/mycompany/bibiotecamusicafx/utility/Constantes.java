package com.mycompany.bibiotecamusicafx.utility;

public class Constantes {
    private Constantes() {
    }

    public static final String VISTA_ARTISTAS = "/fxml/Artista.fxml";
    public static final String VISTA_INICIO = "/fxml/Inicio.fxml";
    public static final String VISTA_MENU = "/fxml/Menu.fxml";
    public static final String VISTA_CANCIONES = "/fxml/Cancion.fxml";
    public static final String VISTA_ARTISTA_EDIT = "/fxml/ArtistaEdit.fxml";
    public static final String VISTA_ALBUM_EDIT = "/fxml/AlbumEdit.fxml";
    public static final String VISTA_ALBUMES = "/fxml/Album.fxml";
    public static final String GUION = "-";
    public static final String COLOR_ROJO = "#f53434";
    public static final String ETIQUETA_NOMBRE = "Nombre";
    public static final String ETIQUETA_NACIONALIDAD = "Nacionalidad";
    public static final String ETIQUETA_FECHA_NACIMIENTO = "Fecha nacimiento(dd/mm/yyyy): ";
    public static final String ETIQUETA_EDAD = "Años";
    public static final String DOS_PUNTOS_ESPACIO = ": ";
    public static final String PATRON_FECHA = "dd/MM/yyyy";
    public static final String MSG_NO_HAY_ARTISTAS = "No hay artistas añadidos";
    public static final String MSG_NO_HAY_ALBUMES = "No hay álbumes añadidos";
    public static final String MSG_COMPLETAR_CAMPOS = "Por favor, complete todos los campos";
    public static final String MSG_ARTISTA_NO_ENCONTRADO = "Artista no encontrado";
    public static final String MSG_SALIR = "Saliendo";
    public static final String MSG_ERROR = "Error";
    public static final String MSG_ARTISTA_ELIMINADO = "Artista eliminado con exito";
    public static final String SEPARADOR = "__________________________";
    public static final String NOMBRE_ARTISTA = "Nombre del artista";
    public static final String EXPRESION_REGULAR = "\\d{2}/\\d{2}/\\d{4}";
    public static final int TAMANHO_MAX = 10;
    public static final int ORDENAR_POR_NOMBRE = 1;
    public static final int ORDENAR_POR_EDAD = 2;
}
