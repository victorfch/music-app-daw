package com.mycompany.bibiotecamusicafx.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Utilidades {

    public static Map<Integer, String> getMenu() {
        HashMap<Integer, String> opciones = new HashMap();
        opciones.put(1, "Añadir artista");
        opciones.put(2, "Editar artista");
        opciones.put(3, "Mostrar artistas");
        opciones.put(4, "Buscar artista");
        opciones.put(5, "Eliminar artista");
        opciones.put(6, "Añadir album");
        opciones.put(7, "Editar album");
        opciones.put(8, "Mostrar albumes");
        opciones.put(9, "Buscar album");
        opciones.put(10, "Eliminar album");
        opciones.put(11, "Añadir cancion");
        opciones.put(11, "Buscar cancion");
        opciones.put(12, "Editar cancion");
        opciones.put(13, "Mostrar canciones");
        opciones.put(15, "Eliminar cancion");
        opciones.put(16, "Salir");
        return opciones;
    }

    public static String menuEstilizadoCLI() {
        String opciones = "__________________________" + System.lineSeparator()
                + "   Orange Music  " + System.lineSeparator()
                + "__________________________" + System.lineSeparator();

        for (Map.Entry<Integer, String> opcion : getMenu().entrySet()) {
            opciones += opcion.getKey() + ". " + opcion.getValue() + System.lineSeparator();
        }
        opciones += "__________________________" + System.lineSeparator()
                + "Elige una opcion: " + System.lineSeparator();

        return opciones;
    }

    public static String formatearEspacios(String valor) {
        valor = valor.toLowerCase().trim().replace(" ", "%20");
        return valor;
    }

    public static boolean esFechaValida(String fecha) {
        String expReg = "\\d{2}/\\d{2}/\\d{4}";
        return Pattern.matches(expReg, fecha);
    }

    public static LocalDate conversorStringToLocalDate(String fecha) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha, formateador);
    }

}
