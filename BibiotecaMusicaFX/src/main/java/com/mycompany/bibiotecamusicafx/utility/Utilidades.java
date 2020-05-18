package com.mycompany.bibiotecamusicafx.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Utilidades {
    public static Scanner sc = new Scanner(System.in);

    public static String getMenu() {
        return "1. Añadir artista" + System.lineSeparator()
                + "2. Mostrar artistas ordenados por nombre" + System.lineSeparator()
                + "3. Mostrar artistas ordenados por edad" + System.lineSeparator()
                + "4. Ver info completa de artista" + System.lineSeparator()
                + "5. Eliminar artista" +  System.lineSeparator()
                + "6. Añadir album" + System.lineSeparator()
                + "7. Eliminar album" + System.lineSeparator()
                + "8. Importar albums de artista con fichero" + System.lineSeparator()
                + "9. Salir" + System.lineSeparator();
    }

    public static String menuEstilizadoCLI() {
        return "__________________________" + System.lineSeparator()
                + "   Orange Music  " + System.lineSeparator()
                + "__________________________" + System.lineSeparator()
                + getMenu()
                + "__________________________" + System.lineSeparator()
                + "Elige una opcion";
    }

    public static String formatearEspacios(String valor) {
        return valor.toLowerCase().trim().replace(" ", "%20");
    }

    public static boolean esFechaValida(String fecha) {
        return Pattern.matches(Constantes.EXPRESION_REGULAR, fecha);
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
