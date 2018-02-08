/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import javax.naming.NamingException;
import model.Acquisto;
import model.Operazione;
import model.Prenotazione;
import model.Sala;
import model.Sconto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe che testa tutti i metodi della classe OperazioneDAO
 * @author Antonio
 */
public class OperazioneDAOTest {
    
    /**
     * Variabili che utilizziamo per istanziare l'oggetto Acquisto per i Test.
     */
    private static final String EMAIL = "ABergamaschi@gmail.com ";
    private static final int IDSPETTACOLO = 54;
    private static final int POSTO = 16;
    private static final int OFFSET = 3;
    private static  Sala SALA;
    private static final float PREZZOFINALE = 5.0f;
    private static  Sconto SCONTO; 
    
    
    private static java.sql.Connection connection;
    private static OperazioneDAO operazioneDAO;
    private static ScontoDAO scontoDAO;
    private static SalaDAO salaDAO;

    public static Operazione o;
    public static Acquisto mioAcquisto;
    
    /**
     * Costruttore.
     */
    public OperazioneDAOTest() {
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
     * inizializza l'oggetto Acquisto che utilizzeremo per i test e ne ricava l'id autogenerato dal DB.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException
     */
    @BeforeClass
    public static void setUpClass() throws NamingException, SQLException, ParseException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        setMyOperazione();
        setIdReale();
    }
    
   /**
     * Metodo che elimina dal DB il nostro Acquisto e chiude la connessione.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    @AfterClass
    public static void tearDownClass() throws SQLException, ParseException, NamingException {
        operazioneDAO.deleteOperazione(mioAcquisto.getIdOperazione());
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
     * Metodo che istanzia l'oggetto Acquisto che utilizzeremo per effettuare i test.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setMyOperazione() throws SQLException, NamingException, ParseException{
        operazioneDAO = new OperazioneDAO((Connection) connection);
        salaDAO = new SalaDAO((Connection) connection);
        SALA = salaDAO.foundByID(6);
        scontoDAO = new ScontoDAO((Connection) connection);
        SCONTO = scontoDAO.foundByID(41);
        Calendar data = Calendar.getInstance();
        data.set(2018, 02, 05);
        o = new Acquisto(EMAIL, IDSPETTACOLO, POSTO, OFFSET, SALA, Operazione.prenotato.FALSE, Operazione.acquistato.TRUE, PREZZOFINALE, data, SCONTO);
        operazioneDAO.createOperazione(o);  
    }
    
    /**
     * Metodo che ricava l'id autogenerato dal DB quando inseriamo l'oggetto Acquisto.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setIdReale() throws SQLException, ParseException, NamingException{
        //Per ricavare l'id reale dell'acquisto creato
        List<Acquisto> listaAcquisti = operazioneDAO.getAcquistiUtente(EMAIL);
        mioAcquisto = null;
        for(Acquisto a: listaAcquisti){
            if(a.getIdSpettacolo() == IDSPETTACOLO && 
               a.getSala().getIdSala() == SALA.getIdSala() &&
               a.getPosto() == POSTO){
                mioAcquisto = a;
            }
        }
    }

    /**
     * Test del metodo getAllOperazioni, della classe OperazioneDAO.
     */
    @Test
    public void testGetAllOperazioni() throws Exception {
        System.out.println("getAllOperazioni");
        List<Operazione> result = operazioneDAO.getAllOperazioni();
        boolean expResult = false;
        for(Operazione o: result){
            if(o instanceof Operazione){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test del metodo getPrenotazioni, della classe OperazioneDAO.
     */
    @Test
    public void testGetPrenotazioni() throws Exception {
        System.out.println("getPrenotazioni");
        List<Prenotazione> result = operazioneDAO.getPrenotazioni();
        boolean expResult = false;
        for(Prenotazione p: result){
            if(p instanceof Prenotazione){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
        
    }

    /**
     * Test del metodo getPrenotazioniUtente, della classe OperazioneDAO.
     */
    @Test
    public void testGetPrenotazioniUtente() throws Exception {
        System.out.println("getPrenotazioniUtente");
        String email = "ABergamaschi@gmail.com";
        List<Prenotazione> result = operazioneDAO.getPrenotazioniUtente(email);
        boolean expResult = false;
        for(Prenotazione p: result){
            if(p instanceof Prenotazione){
                expResult = true;
            }
        }
    }

    /**
     * Test del metodo getAcquisti, della classe OperazioneDAO.
     */
    @Test
    public void testGetAcquisti() throws Exception {
        System.out.println("getAcquisti");
        List<Acquisto> result = operazioneDAO.getAcquisti();
        boolean expResult = false;
        for(Acquisto a: result){
            if(a instanceof Acquisto){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test del metodo getAcquistiUtente, della classe OperazioneDAO.
     */
    @Test
    public void testGetAcquistiUtente() throws Exception {
        System.out.println("getAcquistiUtente");
        String email = "ABergamaschi@gmail.com";
        List<Acquisto> result = operazioneDAO.getAcquistiUtente(email);
        boolean expResult = false;
        for(Acquisto a: result){
            if(a instanceof Acquisto){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test del metodo foundByID, della classe OperazioneDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundByID() throws Exception {
        System.out.println("foundByID");      
        int expResult = mioAcquisto.getIdOperazione();
        int result = operazioneDAO.foundByID(expResult).getIdOperazione();
        assertEquals(expResult, result);        
    }

    /**
     * Test del metodo createOperazione, della classe OperazioneDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateOperazione() throws Exception {
        System.out.println("createOperazione");
        operazioneDAO.deleteOperazione(mioAcquisto.getIdOperazione());
        boolean expResult = true;
        assertEquals(expResult, operazioneDAO.createOperazione(o));
        setIdReale();
    }

    /**
     * Test del metodo updateOperazione, della classe OperazioneDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateOperazione() throws Exception {
        System.out.println("updateOperazione");
        int newPosto = 17;
        boolean expResult = true;
        o.setPosto(newPosto);
        boolean result = operazioneDAO.updateOperazione(o);
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo deleteOperazione, della classe OperazioneDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteOperazione() throws Exception {
        System.out.println("deleteOperazione");
        boolean expResult = true;
        assertEquals(expResult, operazioneDAO.deleteOperazione(mioAcquisto.getIdOperazione()));
        setMyOperazione();
        setIdReale();
    }

    /**
     * Test del metodo analyticsGetDatiOperazioni, della classe OperazioneDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testAnalyticsGetDatiOperazioni() throws Exception {
        System.out.println("analyticsGetDatiOperazioni");
        String scelta = "Operazioni";
        boolean expResult = true;
        String s = operazioneDAO.analyticsGetDatiOperazioni(scelta);
        boolean result = false;
        if(s instanceof String){
            result = true;
        }
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo analyticsGetDatiAffluenzeSpettacolo, della classe OperazioneDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testAnalyticsGetDatiAffluenzeSpettacolo() throws Exception {
        System.out.println("analyticsGetDatiAffluenzeSpettacolo");
        String minAffluenza = "1";
        boolean expResult = true;
        String s = operazioneDAO.analyticsGetDatiAffluenzeSpettacolo(minAffluenza);
        boolean result = false;
        if(s instanceof String){
            result = true;
        }
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo analyticsGetDatiIncassi, della classe OperazioneDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testAnalyticsGetDatiIncassi() throws Exception {
        System.out.println("analyticsGetDatiIncassi");
        String minIncasso = "5.0";
        boolean expResult = true;
        String s = operazioneDAO.analyticsGetDatiIncassi(minIncasso);
        boolean result = false;
        if(s instanceof String){
            result = true;
        }
        assertEquals(expResult, result);
    }
    
}
