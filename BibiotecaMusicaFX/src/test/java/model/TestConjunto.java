package model;

import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.model.Conjunto;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestConjunto {

    static Conjunto conjunto = new Conjunto();

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
    public void testGuardarArtistaYGetArtista() {
        String nombre = "eminem";
        String nacionalidad = "eeuu";
        LocalDate fecha = LocalDate.parse("1972-10-17");
        Artista eminem = new Artista(nombre, nacionalidad, fecha);
        conjunto.guardar(eminem);
        Artista artista = conjunto.getArtistaPorNombre(nombre);
        assertEquals(nombre, artista.getNombre());
        assertEquals(nacionalidad, artista.getNacionalidad());
        assertEquals(fecha, artista.getFechaNacimiento());
        assertEquals(1, Conjunto.getTamReal());
        assertNotEquals(2, Conjunto.getTamReal());
    }

    @Test
    public void testEliminarArtista() {
        String nombre = "paco";
        String nacionalidad = "eeuu";
        LocalDate fecha = LocalDate.parse("1980-08-17");
        Artista paco = new Artista(nombre, nacionalidad, fecha);
        conjunto.guardar(paco);
        assertTrue(conjunto.eliminar(nombre));
        assertFalse(conjunto.eliminar("sara"));
        assertEquals(1, Conjunto.getTamReal());
    }

}
