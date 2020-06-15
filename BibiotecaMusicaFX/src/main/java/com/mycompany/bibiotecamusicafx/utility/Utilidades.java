package com.mycompany.bibiotecamusicafx.utility;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Utilidades {
    public static Scanner sc = new Scanner(System.in);

    private Utilidades() {
    }

    public static String getMenu() {
        return "1. Añadir artista" + System.lineSeparator()
                + "2. Mostrar artistas ordenados por nombre" + System.lineSeparator()
                + "3. Mostrar artistas ordenados por edad" + System.lineSeparator()
                + "4. Ver info completa de artista" + System.lineSeparator()
                + "5. Eliminar artista" +  System.lineSeparator()
                + "6. Añadir album" + System.lineSeparator()
                + "7. Eliminar album" + System.lineSeparator()
                + "8. Exportar a fichero los albumes" + System.lineSeparator()
                + "9. Importar artistas de fichero" + System.lineSeparator()
                + "10. Salir" + System.lineSeparator();
    }

    public static String menuEstilizadoCLI() {
        return Constantes.SEPARADOR + System.lineSeparator()
                + "   Orange Music  " + System.lineSeparator()
                + Constantes.SEPARADOR + System.lineSeparator()
                + getMenu()
                + Constantes.SEPARADOR + System.lineSeparator()
                + "Elige una opcion";
    }

    public static String formatearEspacios(String valor) {
        return valor.toLowerCase().trim().replace(" ", "%20");
    }

    public static boolean esFechaValida(String fecha) {
        return Pattern.matches(Constantes.EXPRESION_REGULAR, fecha);
    }
    
    public static String pedirCadena(String cadena) {
        mostrarCadena(cadena.concat(Constantes.DOS_PUNTOS_ESPACIO));
        return sc.nextLine();
    }
    
    public static void mostrarCadena(String cadena) {
        System.out.println(cadena);
    }
}
