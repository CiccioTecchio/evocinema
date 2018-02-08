/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.naming.NamingException;
import model.Sala;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe che testa tutti i metodi della classe SalaDAO
 * @author Antonio
 */
public class SalaDAOTest {
    
    /**
     * Variabili che utilizziamo per istanziare l'oggetto Sala per i Test.
     */
    private static final int NUMEROPOSTI = 82;
    private static final String CONFIGPOSTI = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111100000000000000001111111111111100000000000000001111111111111100000000000000000000000000000000000000000011101111111111111101110000000011101111111111111101110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    
    private static java.sql.Connection connection;
    private static SalaDAO salaDAO;
    private static Sala sala;
    private static int idReale;
    
    /**
     * Costruttore.
     */
    public SalaDAOTest() {
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
     * inizializza l'oggetto Sala che utilizzeremo per i test e ne ricava l'id autogenerato dal DB.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException
     */
    @BeforeClass
    public static void setUpClass() throws SQLException, NamingException, ParseException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        setMySala();
        setIdReale();
    }
    
    /**
     * Metodo che istanzia l'oggetto Sala che utilizzeremo per effettuare i test.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setMySala() throws SQLException, NamingException, ParseException{
        salaDAO = new SalaDAO((com.mysql.jdbc.Connection) connection);
        sala = new Sala();
        sala.setConfigPosti(CONFIGPOSTI);
        sala.setNumeroPosti(NUMEROPOSTI);
        salaDAO.createSala(sala);
    }
    
     /**
     * Metodo che ricava l'id autogenerato dal DB quando inseriamo l'oggetto Sala.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setIdReale() throws SQLException, ParseException, NamingException{
        List<Sala> listaSale = salaDAO.getAllSale();
        idReale = 0;
        int curr = 0;
        for(Sala s: listaSale){
            curr = s.getIdSala();
            if( curr > idReale){
                idReale = curr;
            }
        }
    }
    
   /**
     * Metodo che elimina dal DB la nostra Sala e chiude la connessione.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    @AfterClass
    public static void tearDownClass() throws SQLException, ParseException, NamingException {
        salaDAO.deleteSale(idReale);   
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
     * Test del metodo getAllSale, della classe SalaDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllSale() throws Exception {
        System.out.println("getAllSale");
        List<Sala> result = salaDAO.getAllSale();
        boolean expResult = false;
        for(Sala s: result){
            if(s instanceof Sala){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test del metodo foundByID, della classe SalaDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundByID() throws Exception {
        System.out.println("foundByID"); 
        //devo ricavare l'id reale
        int expResult = idReale;
        int result = salaDAO.foundByID(idReale).getIdSala();
        assertEquals(expResult, result);             
    }

    /**
     * Test del metodo createSala, della classe SalaDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateSala() throws Exception {
        System.out.println("createSala");
        salaDAO.deleteSale(idReale);
        boolean expResult = true;
        assertEquals(expResult, salaDAO.createSala(sala));
        setIdReale();
    }

    /**
     * Test del metodo updateSala, della classe SalaDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateSala() throws Exception {
        System.out.println("updateSala");
        int newPosti = 80;
        boolean expResult = true;
        sala.setNumeroPosti(newPosti);
        boolean result = salaDAO.updateSala(sala);
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo deleteSale, della classe SalaDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteSale() throws Exception {
        System.out.println("deleteSale");
        boolean expResult = true;
        boolean result = salaDAO.deleteSale(idReale);
        assertEquals(expResult, result);
        setMySala();
        setIdReale();        
    }    
}
