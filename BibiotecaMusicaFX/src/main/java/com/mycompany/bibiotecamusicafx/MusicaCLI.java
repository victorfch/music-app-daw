package com.mycompany.bibiotecamusicafx;

import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.servicio.ArtistaServicioImplCLI;
import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.utility.Utilidades;
import java.time.LocalDate;
import java.util.Scanner;

public class MusicaCLI {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArtistaServicioImplCLI artistaServicio = ArtistaServicioImplCLI.getInstancia();
        boolean esFechaNacimientoValida = false;
        boolean seguir = true;
        while (seguir) {
            System.out.print(Utilidades.menuEstilizadoCLI());
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println(Constantes.ETIQUETA_NOMBRE.concat(Constantes.DOS_PUNTOS_ESPACIO));
                    String nombre = sc.nextLine();
                    System.out.println(Constantes.ETIQUETA_NACIONALIDAD.concat(Constantes.DOS_PUNTOS_ESPACIO));
                    String nacionalidad = sc.nextLine();
                    LocalDate fechaDate = null;
                    while (!esFechaNacimientoValida) {
                        System.out.println(Constantes.ETIQUETA_FECHA_NACIMIENTO);
                        String fecha = sc.nextLine();
                        fecha = fecha.trim();
                        if (Utilidades.esFechaValida(fecha)) {
                            esFechaNacimientoValida = true;
                            fechaDate = Utilidades.conversorStringToLocalDate(fecha);
                        }
                    }
                    esFechaNacimientoValida = !esFechaNacimientoValida;
                    Artista artista = new Artista(nombre, nacionalidad, fechaDate);
                    artistaServicio.guardar(artista);
                    break;
                case 3:
                    System.out.println("ID "
                            .concat(Constantes.ETIQUETA_NOMBRE.concat(" "))
                            .concat(Constantes.ETIQUETA_NACIONALIDAD.concat(" ")
                            .concat(Constantes.ETIQUETA_EDAD)));
                    for (int i = 0; i < ArtistaServicioImplCLI.getTamReal(); i++) {
                        System.out.println(artistaServicio.obtenerTodos()[i].toStringConID());
                    }
                    break;
                case 4:
                    System.out.println("ID: ");
                    String id = sc.nextLine();
                    Artista compositor = artistaServicio.getArtista(id);
                    if (compositor == null) {
                        System.out.println("No encontrado");
                    } else {
                        System.out.println(compositor);
                    }
                    break;

                case 16:
                    seguir = false;
                    System.out.println("Saliendo");
                    break;

                default:
                    System.out.println("Error");
            }
        }

    }
}
