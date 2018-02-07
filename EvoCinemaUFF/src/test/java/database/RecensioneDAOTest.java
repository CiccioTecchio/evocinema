/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import javax.naming.NamingException;
import model.Film;
import model.Recensione;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antonio
 */
public class RecensioneDAOTest {
    
    private static final String EMAIL = "antonio.giulio96@gmail.com";
    private static Film FILM;
    private static final float VALUTAZIONE = 3.5f;
    private static final String TESTO = "Bellissimo film";
    
    
    private static java.sql.Connection connection;
    private static RecensioneDAO recensioneDAO;
    private static FilmDAO filmDAO;
    private static Recensione recensione;
    
    public RecensioneDAOTest() {
    }
    
    private static java.sql.Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco");
    }
    
    @BeforeClass
    public static void setUpClass() throws NamingException, SQLException, ParseException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        recensioneDAO = new RecensioneDAO((com.mysql.jdbc.Connection) connection);  
        filmDAO = new FilmDAO((com.mysql.jdbc.Connection) connection);
        FILM = filmDAO.foundByID(1);
        Calendar dataRec = Calendar.getInstance();
        dataRec.set(2018, 02, 06);
        recensione = new Recensione(EMAIL, FILM, VALUTAZIONE, TESTO, dataRec);
        recensioneDAO.createRecensione(recensione);
        System.out.println("Ho istanziato e creato sia film che recensione");
        
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException, ParseException, NamingException {
        recensioneDAO.deleteRecensione(recensione);
        connection.rollback();
        connection.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of getAllRecensioni method, of class RecensioneDAO.
     */
    @Test
    public void testGetAllRecensioni() throws Exception {
        System.out.println("getAllRecensioni");
        List<Recensione> result = recensioneDAO.getAllRecensioni();
        boolean expResult = false;
        for(Recensione r: result){
            if(r instanceof Recensione){
                expResult = true;              
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test of foundByEmail method, of class RecensioneDAO.
     */
    @Test
    public void testFoundByEmail() throws Exception {
        System.out.println("foundByEmail");
        List<Recensione> result = recensioneDAO.foundByEmail(EMAIL);
        boolean expResult = false;
        for(Recensione r: result){
            if(r instanceof Recensione){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test of foundByFilm method, of class RecensioneDAO.
     */
    @Test
    public void testFoundByFilm() throws Exception {
        System.out.println("foundByFilm");
        List<Recensione> result = recensioneDAO.foundByFilm(3);
        boolean expResult = false;
        for(Recensione r: result){
            if(r instanceof Recensione){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test of createRecensione method, of class RecensioneDAO.
     */
    @Test
    public void testCreateRecensione() throws Exception {
        System.out.println("createRecensione");
        recensioneDAO.deleteRecensione(recensione);
        boolean expResult = true;
        assertEquals(expResult, recensioneDAO.createRecensione(recensione));
    }

    /**
     * Test of updateRecensione method, of class RecensioneDAO.
     */
    @Test
    public void testUpdateRecensione() throws Exception {
        System.out.println("updateRecensione");
        String newTesto = "Film bruttissimo";
        boolean expResult = true;
        recensione.setTesto(TESTO);
        assertEquals(expResult, recensioneDAO.updateRecensione(recensione));
    }

    /**
     * Test of deleteRecensione method, of class RecensioneDAO.
     */
    @Test
    public void testDeleteRecensione() throws Exception {
        System.out.println("deleteRecensione");
        boolean expResult = true;
        assertEquals(expResult, recensioneDAO.deleteRecensione(recensione));
        recensioneDAO.createRecensione(recensione);
    }
    
}
