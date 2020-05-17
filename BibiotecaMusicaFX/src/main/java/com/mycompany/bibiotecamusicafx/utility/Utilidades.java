package com.mycompany.bibiotecamusicafx.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Utilidades {
    public static Scanner sc = new Scanner(System.in);

    public static Map<Integer, String> getMenu() {
        Map<Integer, String> opciones = new HashMap();
        opciones.put(1, "Añadir artista");
        opciones.put(2, "Mostrar artistas ordenados por nombre");
        opciones.put(3, "Mostrar artistas ordenados por edad");
        opciones.put(4, "Ver info completa de artista");
        opciones.put(5, "Eliminar artista");
        opciones.put(6, "Añadir album");
        opciones.put(7, "Eliminar album");
        opciones.put(8, "Salir");
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
                + "Elige una opcion";

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
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(Constantes.PATRON_FECHA);
        return LocalDate.parse(fecha, formateador);
    }
    
    public static String pedirCadena(String cadena) {
        mostrarCadena(cadena.concat(Constantes.DOS_PUNTOS_ESPACIO));
        return sc.nextLine();
    }
    
    public static void mostrarCadena(String cadena) {
        System.out.println(cadena);
    }
}
