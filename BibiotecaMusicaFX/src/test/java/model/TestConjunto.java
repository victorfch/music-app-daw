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
    public void testGuardarArtista() {
        LocalDate fecha1 = LocalDate.parse("1972-10-17");
        Artista eminem = new Artista("eminem", "eeuu", fecha1);
        conjunto.guardar(eminem);
        assertEquals(1, Conjunto.getTamReal());
        assertNotEquals(2, Conjunto.getTamReal());
    }
}
