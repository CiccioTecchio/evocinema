/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.DriverManager;
import java.sql.SQLException;
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
 * Classe che testa tutti i metodi della classe RecensioneDAO
 * @author Antonio
 */
public class RecensioneDAOTest {
    
    /**
     * Variabili che utilizziamo per istanziare l'oggetto Recensione per i Test.
     */
    private static final String EMAIL = "antonio.giulio96@gmail.com";
    private static Film FILM;
    private static final float VALUTAZIONE = 3.5f;
    private static final String TESTO = "Bellissimo film";
    
    
    private static java.sql.Connection connection;
    private static RecensioneDAO recensioneDAO;
    private static FilmDAO filmDAO;
    private static Recensione recensione;
    
    /**
     * Costruttore.
     */
    public RecensioneDAOTest() {
    }
    
    /**
     * Connessione temporanea al DB.
     * @return La connessione al DB
     * @throws SQLException 
     */
    private static java.sql.Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco");
    }
    
    /**
     * Metodo che inizializza la connessione, imposta l'autocommit a false per non sporcare il database e
     * inizializza l'oggetto Recensione che utilizzeremo per i test.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException
     */
    @BeforeClass
    public static void setUpClass() throws NamingException, SQLException, ParseException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        setMyRecensione();
    }
    
   /**
     * Metodo che elimina dal DB la nostra Recensione e chiude la connessione.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    @AfterClass
    public static void tearDownClass() throws SQLException, ParseException, NamingException {
        recensioneDAO.deleteRecensione(recensione);
        connection.rollback();
        connection.close();
    }
    
    /**
     * Metodo che viene eseguito prima di ogni metodo Test.
     */
    @Before
    public void setUp() {
    }
    
    /**
     * Metodo che viene eseguito dopo di ogni metodo Test.
     */
    @After
    public void tearDown() {
    }
    
    /**
     * Metodo che istanzia l'oggetto recensione che utilizzeremo per effettuare i test.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setMyRecensione() throws NamingException, SQLException, ParseException{
        recensioneDAO = new RecensioneDAO((com.mysql.jdbc.Connection) connection);  
        filmDAO = new FilmDAO((com.mysql.jdbc.Connection) connection);
        FILM = filmDAO.foundByID(1);
        Calendar dataRec = Calendar.getInstance();
        dataRec.set(2018, 02, 06);
        recensione = new Recensione(EMAIL, FILM, VALUTAZIONE, TESTO, dataRec);
        recensioneDAO.createRecensione(recensione);   
    }


    /**
     * Test del metodo getAllRecensioni, della classe RecensioneDAO.
     * @throws java.lang.Exception
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
     * Test del metodo foundByEmail, della classe RecensioneDAO.
     * @throws java.lang.Exception
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
     * Test del metodo foundByFilm, della classe RecensioneDAO.
     * @throws java.lang.Exception
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
     * Test del metodo createRecensione, della classe RecensioneDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateRecensione() throws Exception {
        System.out.println("createRecensione");
        recensioneDAO.deleteRecensione(recensione);
        boolean expResult = true;
        assertEquals(expResult, recensioneDAO.createRecensione(recensione));
    }

    /**
     * Test del metodo updateRecensione, della classe RecensioneDAO.
     * @throws java.lang.Exception
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
     * Test del metodo eleteRecensione, della classe RecensioneDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteRecensione() throws Exception {
        System.out.println("deleteRecensione");
        boolean expResult = true;
        assertEquals(expResult, recensioneDAO.deleteRecensione(recensione));
        setMyRecensione();
    }
    
}
