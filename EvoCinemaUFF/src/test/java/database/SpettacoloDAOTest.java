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
import model.Spettacolo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe che testa tutti i metodi della classe SpettacoloDAO
 * @author Antonio
 */
public class SpettacoloDAOTest {
    
    /**
     * Variabili che utilizziamo per istanziare l'oggetto Spettacolo per i Test.
     */
    private static final int      IDSALA = 8;
    private static final Integer  IDFILM = 11;
    private static final String   TITOLO = "Tre uomini e una gamba";
    private static       Calendar DATAINIZIO;
    private static       Calendar DATAFINE;
    private static final float    PREZZO = 6.0f;
    private static       Calendar ORAINIZIO;
    private static       Calendar ORAFINE;
    private static final String   MATRICEPOSTI = "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd";
    
    private static java.sql.Connection connection;
    private static SpettacoloDAO spettacoloDAO;
    private static Spettacolo spettacolo, mySpett;
    private static int idSpettacolo;
    
    /**
     * Costruttore.
     */
    public SpettacoloDAOTest() {
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
     * Metodo che inizializza la connessione, imposta l'autocommit a false per non sporcare il database,
     * inizializza l'oggetto Spettacolo che utilizzeremo per i test e ne ricava l'id autogenerato dal DB.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException
     */
    @BeforeClass
    public static void setUpClass() throws SQLException, NamingException, ParseException{
        connection = getTestConnection();
        connection.setAutoCommit(false);
        setMySpettacolo();
        setIdReale();
    }
    
    /**
     * Metodo che elimina dal DB il nostro Spettacolo e chiude la connessione.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    @AfterClass
    public static void tearDownClass() throws SQLException, ParseException, NamingException {
        spettacoloDAO.deleteSpettacolo(idSpettacolo);
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
     * Metodo che istanzia l'oggetto Spettacolo che utilizzeremo per effettuare i test.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setMySpettacolo() throws SQLException, ParseException, NamingException{
        spettacoloDAO = new SpettacoloDAO((com.mysql.jdbc.Connection) connection);
        DATAINIZIO = Calendar.getInstance();
        DATAINIZIO.set(2018, 02, 04);
        DATAFINE = Calendar.getInstance();
        DATAFINE.set(2018, 10, 05);
        ORAINIZIO = Calendar.getInstance();
        ORAINIZIO.set(0, 0, 0, 17, 30);
        ORAFINE = Calendar.getInstance();
        ORAFINE.set(0, 0, 0, 19, 16);
        spettacolo = new Spettacolo(IDSALA, IDFILM, TITOLO, DATAINIZIO, DATAFINE, PREZZO, ORAINIZIO, ORAFINE, MATRICEPOSTI);
        spettacoloDAO.createSpettacolo(spettacolo);
    }
    
    /**
     * Metodo che ricava l'id autogenerato dal DB quando inseriamo l'oggetto Spettacolo.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setIdReale() throws SQLException, ParseException, NamingException{
        //cerco lo spettacolo per ricavarne l'ID autogenerato
        List<Spettacolo> listaSpettacoli = spettacoloDAO.getAllSpettacoli();
        mySpett = null;
        for(Spettacolo s: listaSpettacoli){
            if(s.getTitolo().equals(TITOLO)){
                mySpett = s;
            }
        }
        idSpettacolo = mySpett.getIdSpettacolo();
    }

    /**
     * Test del metodo getAllSpettacoli, della classe SpettacoloDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllSpettacoli() throws Exception {
        System.out.println("getAllSpettacoli");
        List<Spettacolo> result = spettacoloDAO.getAllSpettacoli();
        boolean expResult = false;
        for(Spettacolo s: result){
            if(s instanceof Spettacolo){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test del metodo foundByID, della classe SpettacoloDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundByID() throws Exception {
        System.out.println("foundByID");
        int expResult = idSpettacolo;
        int result = spettacoloDAO.foundByID(idSpettacolo).getIdSpettacolo();
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo foundBySala, della classe SpettacoloDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundBySala() throws Exception {
        System.out.println("foundBySala");
        List<Spettacolo> result = spettacoloDAO.foundBySala(IDSALA);
        boolean expResult = false;
        for(Spettacolo s: result){
            if(s instanceof Spettacolo){
                expResult = true;
            }
            assertEquals(expResult, true);
        }        
    }
    

    /**
     * Test del metodo foundByDate, della classe SpettacoloDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundByDate() throws Exception {
        System.out.println("foundByDate");
        List<Spettacolo> result = spettacoloDAO.foundByDate(DATAINIZIO);
        boolean expResult = false;
        for(Spettacolo s: result){
            if(s instanceof Spettacolo){
                expResult = true;
            }
            assertEquals(expResult, true);
        }        
    }

    /**
     * Test del metodo foundByOpera, della classe SpettacoloDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundByOpera() throws Exception {
        System.out.println("foundByOpera");
        List<Spettacolo> result = spettacoloDAO.foundByOpera(IDFILM);
        boolean expResult = false;
        for(Spettacolo s: result){
            if(s instanceof Spettacolo){
                expResult = true;
            }
            assertEquals(expResult, true);
        }        
    }

    /**
     * Test del metodo createSpettacolo, della classe SpettacoloDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateSpettacolo() throws Exception {
        System.out.println("createSpettacolo");
        spettacoloDAO.deleteSpettacolo(idSpettacolo);
        boolean expResult = true;
        assertEquals(expResult, spettacoloDAO.createSpettacolo(spettacolo));
        setIdReale();
    }

    /**
     * Test del metodo updateSpettacolo, della classe SpettacoloDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateSpettacolo() throws Exception {
        System.out.println("updateSpettacolo");
        int newIdSala = 7;
        boolean expResult = true;
        spettacolo.setIdSala(newIdSala);
        boolean result = spettacoloDAO.updateSpettacolo(spettacolo);
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo deleteSpettacolo, della classe SpettacoloDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteSpettacolo() throws Exception {
        System.out.println("deleteSpettacolo");
        boolean expResult = true;
        assertEquals(expResult, spettacoloDAO.deleteSpettacolo(idSpettacolo));
        setMySpettacolo();
        setIdReale();
    }
    
}
