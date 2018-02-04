/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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
 *
 * @author Antonio
 */
public class UtenteRegistratoDAOTest {
    
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
    
    /**
     * Connessione temporanea al DB.
     * @return
     * @throws SQLException 
     */

    private static Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco");
    }
    
    public UtenteRegistratoDAOTest() {
        DATANASCITA = Calendar.getInstance();
        DATANASCITA.set(1996, 01, 29);
    }
    
    @BeforeClass
    public static void setUpClass() throws NamingException, SQLException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        utenteRegistratoDAO = new UtenteRegistratoDAO(connection);
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
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
     * Test of getUtentiByRuolo method, of class UtenteRegistratoDAO.
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

    @Test
    public void testFoundUtenteBaseByEmail() throws Exception {
        System.out.println("foundUtenteBaseByEmail");
        UtenteBase u = new UtenteBase(SALDO, EMAIL, NOMEUTENTE, PASSWORD, UtenteRegistrato.ruolo.UTENTE, NOME, COGNOME, DATANASCITA, UtenteRegistrato.sesso.M, CELLULARE, CITTA, INDIRIZZO);
        utenteRegistratoDAO.createUtenteRegistrato(u);
        String expResult = EMAIL;
        String result = utenteRegistratoDAO.foundUtenteBaseByEmail(EMAIL).getEmail();
        assertEquals(expResult, result);
        utenteRegistratoDAO.deleteUtenteRegistrato(utenteRegistratoDAO.foundUtenteBaseByEmail(EMAIL).getEmail());
    }

    /**
     * Test of updateUtenteRegistrato method, of class UtenteRegistratoDAO.
     */
    @Test
    public void testUpdateUtenteRegistrato() throws Exception {
        System.out.println("updateUtenteRegistrato");
        UtenteBase u = new UtenteBase(SALDO, EMAIL, NOMEUTENTE, PASSWORD, UtenteRegistrato.ruolo.UTENTE, NOME, COGNOME, DATANASCITA, UtenteRegistrato.sesso.M, CELLULARE, CITTA, INDIRIZZO);
        utenteRegistratoDAO.createUtenteRegistrato(u);
        String newNomeUtente = "BNose";
        boolean expResult = true;
        u.setNomeUtente(newNomeUtente);
        boolean result = utenteRegistratoDAO.updateUtenteRegistrato(u);
        assertEquals(expResult, result);
        utenteRegistratoDAO.deleteUtenteRegistrato(utenteRegistratoDAO.foundUtenteBaseByEmail(EMAIL).getEmail());        
    }

    /**
     * Test of updateUtenteBase method, of class UtenteRegistratoDAO.
     */
    @Test
    public void testUpdateUtenteBase() throws Exception {
        System.out.println("updateUtenteBase");
        UtenteBase u = new UtenteBase(SALDO, EMAIL, NOMEUTENTE, PASSWORD, UtenteRegistrato.ruolo.UTENTE, NOME, COGNOME, DATANASCITA, UtenteRegistrato.sesso.M, CELLULARE, CITTA, INDIRIZZO);
        utenteRegistratoDAO.createUtenteRegistrato(u);
        String newNomeUtente = "BNose";
        boolean expResult = true;
        u.setNomeUtente(newNomeUtente);
        boolean result = utenteRegistratoDAO.updateUtenteBase(u);
        assertEquals(expResult, result);
        utenteRegistratoDAO.deleteUtenteRegistrato(utenteRegistratoDAO.foundUtenteBaseByEmail(EMAIL).getEmail());      
    }

    /**
     * Test of createUtenteRegistrato method, of class UtenteRegistratoDAO.
     */
    @Test
    public void testCreateUtenteRegistrato() throws Exception {
        System.out.println("createUtenteRegistrato");
        UtenteBase u = new UtenteBase(SALDO, EMAIL, NOMEUTENTE, PASSWORD, UtenteRegistrato.ruolo.UTENTE, NOME, COGNOME, DATANASCITA, UtenteRegistrato.sesso.M, CELLULARE, CITTA, INDIRIZZO);
        boolean expResult = true;
        assertEquals(expResult, utenteRegistratoDAO.createUtenteRegistrato(u));
        utenteRegistratoDAO.deleteUtenteRegistrato(utenteRegistratoDAO.foundUtenteBaseByEmail(EMAIL).getEmail());      
    }

    /**
     * Test of deleteUtenteRegistrato method, of class UtenteRegistratoDAO.
     */
    @Test
    public void testDeleteUtenteRegistrato() throws Exception {
        System.out.println("deleteUtenteRegistrato");
        UtenteBase u = new UtenteBase(SALDO, EMAIL, NOMEUTENTE, PASSWORD, UtenteRegistrato.ruolo.UTENTE, NOME, COGNOME, DATANASCITA, UtenteRegistrato.sesso.M, CELLULARE, CITTA, INDIRIZZO);
        utenteRegistratoDAO.createUtenteRegistrato(u);
        boolean expResult = true;
        assertEquals(expResult, utenteRegistratoDAO.deleteUtenteRegistrato(EMAIL));
    }
}
