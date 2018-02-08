/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import javax.naming.NamingException;
import model.UtenteBase;
import model.UtenteRegistrato;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe che testa tutti i metodi della classe UtenteRegistratoDAO
 * @author Antonio
 */
public class UtenteRegistratoDAOTest {
    
    /**
     * Variabili che utilizziamo per istanziare l'oggetto UtenteBase per i Test.
     */
    private static final float    SALDO = 0f;
    private static final String   EMAIL = "emai222@email.it";
    private static final String   NOMEUTENTE = "nomeUtente23";
    private static       Calendar DATANASCITA;
    private static final String   PASSWORD = "password";
    private static final String   NOME = "nome";
    private static final String   COGNOME = "cognome";
    private static final String   CELLULARE = "1234567890";
    private static final String   CITTA = "citta";
    private static final String   INDIRIZZO = "via Roma 7";
    private static Connection connection;
    private static UtenteRegistratoDAO utenteRegistratoDAO;
    private static UtenteBase utente;
    
    /**
     * Connessione temporanea al DB.
     * @return La connessione al DB
     * @throws SQLException 
     */
    private static Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco");
    }
    
    /**
     * Costruttore
     */
    public UtenteRegistratoDAOTest() {
    }
    
    /**
     * Metodo che inizializza la connessione, imposta l'autocommit a false per non sporcare il database e 
     * inizializza l'oggetto UtenteRegistrato che utilizzeremo per i test.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException
     */
    @BeforeClass
    public static void setUpClass() throws NamingException, SQLException, ParseException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        setMyUtente();
    }
    
    /**
     * Metodo che elimina dal DB il nostro UtenteRegistrato e chiude la connessione.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    @AfterClass
    public static void tearDownClass() throws SQLException, ParseException, NamingException {
        utenteRegistratoDAO.deleteUtenteRegistrato(utente.getEmail());
        connection.rollback();
        connection.close();
    }
    
    /**
     * Metodo che istanzia l'oggetto UtenteBase che utilizzeremo per effettuare i test.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setMyUtente() throws NamingException, SQLException, ParseException{
        utenteRegistratoDAO = new UtenteRegistratoDAO(connection);
        DATANASCITA = Calendar.getInstance();
        DATANASCITA.set(1996, 01, 29);
        utente = new UtenteBase(SALDO, EMAIL, NOMEUTENTE, PASSWORD, UtenteRegistrato.ruolo.UTENTE, NOME, COGNOME, DATANASCITA, UtenteRegistrato.sesso.M, CELLULARE, CITTA, INDIRIZZO);
        utenteRegistratoDAO.createUtenteRegistrato(utente);
    }
    
    /**
     * Metodo che viene eseguito prima di ogni metodo Test
     */
    @Before
    public void setUp() {
    }
    
    /**
     * Metodo che viene eseguito dopo ogni metodo Test
     */
    @After
    public void tearDown() {
    }

    /**
     * Test del metodo getUtenteByRuolo, della classe UtenteRegistratoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUtentiByRuolo() throws Exception {
        System.out.println("getUtentiByRuolo");
        List<UtenteRegistrato> result = utenteRegistratoDAO.getUtentiByRuolo(UtenteRegistrato.ruolo.UTENTE);
        boolean expResult1 = false;
        for(UtenteRegistrato u: result){
            if(u instanceof UtenteRegistrato){
                if(u.getRuolo().equals(UtenteRegistrato.ruolo.UTENTE)){
                    expResult1 = true;
                }
            }
            assertEquals(expResult1, true);
        }
        result = utenteRegistratoDAO.getUtentiByRuolo(UtenteRegistrato.ruolo.GESTORE);
        boolean expResult2 = false;
        for(UtenteRegistrato u: result){
            if(u instanceof UtenteRegistrato){
                if(u.getRuolo().equals(UtenteRegistrato.ruolo.GESTORE)){
                    expResult2 = true;
                }
            }
            assertEquals(expResult2, true);
        }
        result = utenteRegistratoDAO.getUtentiByRuolo(UtenteRegistrato.ruolo.OPERATORE);
        boolean expResult3 = false;
        for(UtenteRegistrato u: result){
            if(u instanceof UtenteRegistrato){
                if(u.getRuolo().equals(UtenteRegistrato.ruolo.OPERATORE)){
                    expResult3 = true;
                }
            }
            assertEquals(expResult3, true);
        }
    }

    /**
     * Test del metodo foundUtenteByEmail, della classe UtenteRegistratoDAO.
     * @throws Exception
     */
    @Test
    public void testFoundUtenteBaseByEmail() throws Exception {
        System.out.println("foundUtenteBaseByEmail");
        String expResult = EMAIL;
        String result = utenteRegistratoDAO.foundUtenteBaseByEmail(utente.getEmail()).getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo updateUtenteRegistrato, della classe UtenteRegistratoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateUtenteRegistrato() throws Exception {
        System.out.println("updateUtenteRegistrato");
        String newNomeUtente = "BNose";
        boolean expResult = true;
        utente.setNomeUtente(newNomeUtente);
        boolean result = utenteRegistratoDAO.updateUtenteRegistrato(utente);
        assertEquals(expResult, result);      
    }

    /**
     * Test del metodo updateUtenteBase, della classe UtenteRegistratoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateUtenteBase() throws Exception {
        System.out.println("updateUtenteBase");
        String newNomeUtente = "BNose";
        boolean expResult = true;
        utente.setNomeUtente(newNomeUtente);
        boolean result = utenteRegistratoDAO.updateUtenteBase(utente);
        assertEquals(expResult, result);      
    }

    /**
     * Test del metodo createUtenteRegistrato, della classe UtenteRegistratoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateUtenteRegistrato() throws Exception {
        System.out.println("createUtenteRegistrato");
        utenteRegistratoDAO.deleteUtenteRegistrato(utenteRegistratoDAO.foundUtenteBaseByEmail(EMAIL).getEmail());    
        boolean expResult = true;
        assertEquals(expResult, utenteRegistratoDAO.createUtenteRegistrato(utente));
    }

    /**
     * Test del metodo deleteUtenteRegistrato, della classe UtenteRegistratoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteUtenteRegistrato() throws Exception {
        System.out.println("deleteUtenteRegistrato");
        boolean expResult = true;
        assertEquals(expResult, utenteRegistratoDAO.deleteUtenteRegistrato(utente.getEmail()));
        setMyUtente();
    }
}
