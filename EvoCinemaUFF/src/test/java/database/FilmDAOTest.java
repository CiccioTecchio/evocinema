/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package database;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import javax.naming.NamingException;
import model.Film;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giuseppe
 */
/*
public class FilmDAOTest {
    
    public FilmDAOTest() {
    }

    private static final String TITOLO = "titolo";
    private static final String LOCANDINA = "locandina";
    private static final String REGIA = "regista";
    private static final String CAST = "nome, nome, nome";
    private static final String GENERE = "prova";
    private static final String PRODUZIONE = "produzione";
    private static final String TRAMA = "trama di un film";
    private static final String TRAILER = "URL di YouTube";
    
    
    @Test
    public void createTest() throws SQLException, ParseException, NamingException{
        FilmDAO x = new FilmDAO();
        
        Time durata = new Time(10);
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2000, 1, 1);
        Film a = new Film(0, Film.tipo.ALTRO, TITOLO, LOCANDINA, REGIA, CAST, GENERE, durata, dataUscita, Film.vistoCensura.T, PRODUZIONE, PRODUZIONE, TRAMA, TRAILER);
        x.createFilm(a);
        Film b = x.foundByID(0);
        assertNotNull(b);
        assertEquals(a.getTitolo(), b.getTitolo());
    }
    
    
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
*/