/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import com.mycompany.bibiotecamusicafx.utility.Constantes;
import com.mycompany.bibiotecamusicafx.utility.Utilidades;
import static com.mycompany.bibiotecamusicafx.utility.Utilidades.getMenu;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class TestUtilidades {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetMenu() {
        String esperado = "1. Añadir artista" + System.lineSeparator()
                + "2. Mostrar artistas ordenados por nombre" + System.lineSeparator()
                + "3. Mostrar artistas ordenados por edad" + System.lineSeparator()
                + "4. Ver info completa de artista" + System.lineSeparator()
                + "5. Eliminar artista" + System.lineSeparator()
                + "6. Añadir album" + System.lineSeparator()
                + "7. Eliminar album" + System.lineSeparator()
                + "8. Importar albums de artista con fichero" + System.lineSeparator()
                + "9. Exportar a fichero los artistas" + System.lineSeparator()
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
    public void testEsFechaValida() {
        assertTrue(Utilidades.esFechaValida("12/09/2000"));
        assertFalse(Utilidades.esFechaValida("1/09/2000"));
        assertFalse(Utilidades.esFechaValida("01/09/200"));
        assertFalse(Utilidades.esFechaValida("01/9/200"));
        assertFalse(Utilidades.esFechaValida("01/092000"));
    }
    
    @Test
    public void testConversorStringToLocaldate() {
        LocalDate fechaEsperada = LocalDate.parse("1996-10-18");
        assertEquals(fechaEsperada, Utilidades.conversorStringToLocalDate("18/10/1996"));
        assertNotEquals(fechaEsperada, Utilidades.conversorStringToLocalDate("18/10/2000"));
    }
}
