/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.utility.Utilidades;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author victor
 */
public class TestUtilidades {

    @Test
    public void testGetMenu() {
        String esperado = "1. Añadir artista" + System.lineSeparator()
                + "2. Mostrar artistas ordenados por nombre" + System.lineSeparator()
                + "3. Mostrar artistas ordenados por edad" + System.lineSeparator()
                + "4. Ver info completa de artista" + System.lineSeparator()
                + "5. Eliminar artista" +  System.lineSeparator()
                + "6. Añadir album" + System.lineSeparator()
                + "7. Eliminar album" + System.lineSeparator()
                + "8. Exportar a fichero los albumes" + System.lineSeparator()
                + "9. Importar artistas de fichero" + System.lineSeparator()
                + "10. Salir" + System.lineSeparator();
        assertEquals(esperado, Utilidades.getMenu());
    }
    
    @Test
    public void testMenuEstilizado() {
        String esperado = Constantes.SEPARADOR + System.lineSeparator()
                + "   Orange Music  " + System.lineSeparator()
                + Constantes.SEPARADOR + System.lineSeparator()
                + Utilidades.getMenu()
                + Constantes.SEPARADOR + System.lineSeparator()
                + "Elige una opcion";
        assertEquals(esperado, Utilidades.menuEstilizadoCLI());
    }
    
    @Test
    public void testFormatearEspacios() {
        assertEquals("paco%20perez", Utilidades.formatearEspacios(" Paco perez "));
        assertNotEquals(" Paco perez ", Utilidades.formatearEspacios(" Paco perez "));
    }

    @Test
    public void testEsFechaValida() {
        assertTrue(Utilidades.esFechaValida("2000-09-12"));
        assertFalse(Utilidades.esFechaValida("2000-09-1"));
        assertFalse(Utilidades.esFechaValida("200-09-01"));
        assertFalse(Utilidades.esFechaValida("01/9/2000-9-01"));
        assertFalse(Utilidades.esFechaValida("092000-01"));
    }
}
