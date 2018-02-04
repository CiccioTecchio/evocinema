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
 * @author Giuseppe
 */

public class UtenteRegistratoDAOTest {
    
    private static final String EMAIL = "emai222@email.it";
    private static final String NOMEUTENTE = "nomeUtente23";
    private static final String PASSWORD = "password";
    private static final String NOME = "nome";
    private static final String COGNOME = "cognome";
    private static final String CELLULARE = "1234567890";
    private static final String CITTA = "citta";
    private static final String INDIRIZZO = "via Roma 7";
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
    }
    
    /**
     * Deve sempre contenere le prime due istruzioni: getTestConnection per risolvere i problemi di contesto e l'autocommit a false per non sporcare il db.
     * @throws SQLException
     * @throws NamingException 
     */

    @BeforeClass
    public static void setUpClass() throws SQLException, NamingException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        utenteRegistratoDAO = new UtenteRegistratoDAO(connection);
    }
    
    @Test
    public void testConnectionUp() throws SQLException{
        Connection notnullpls = getTestConnection();
        assertNotNull(notnullpls);
    }
    
    @Test
    public void createTest() throws NamingException, SQLException, ParseException{
        
        Calendar dataNascita = Calendar.getInstance();
        dataNascita.set(2000, 1, 1);
        
        UtenteBase a = new UtenteBase(0f, EMAIL, NOMEUTENTE, PASSWORD, UtenteRegistrato.ruolo.UTENTE, NOME, COGNOME, dataNascita, UtenteRegistrato.sesso.M, CELLULARE, CITTA, INDIRIZZO);
        utenteRegistratoDAO.createUtenteRegistrato(a);
        UtenteBase b = utenteRegistratoDAO.foundUtenteBaseByEmail(EMAIL);
        assertNotNull(b);
        assertEquals(a.getEmail(), b.getEmail());
        utenteRegistratoDAO.deleteUtenteRegistrato(EMAIL);
    }
    
    @Test
    public void createTrueTest() throws NamingException, SQLException, ParseException{
        
        Calendar dataNascita = Calendar.getInstance();
        dataNascita.set(2000, 1, 1);
        
        UtenteBase a = new UtenteBase(14.0F, EMAIL, NOMEUTENTE, PASSWORD, UtenteRegistrato.ruolo.UTENTE, NOME, COGNOME, dataNascita, UtenteRegistrato.sesso.M, CELLULARE, CITTA, INDIRIZZO);
        utenteRegistratoDAO.createUtenteRegistrato(a);
        UtenteBase b = utenteRegistratoDAO.foundUtenteBaseByEmail(EMAIL);
        assertNotNull(b);
        assertEquals(a.getEmail(), b.getEmail());
        utenteRegistratoDAO.deleteUtenteRegistrato(EMAIL);
    }
    
    
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
         connection.rollback();
        connection.close();
    }
    
    @Before
    public void setUp() {
    }
    
    /**
     * E' buona norma rollbackare sempre le istruzioni non committate (appositamente) prima di chiduere la connessione.
     * @throws SQLException 
     */

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
