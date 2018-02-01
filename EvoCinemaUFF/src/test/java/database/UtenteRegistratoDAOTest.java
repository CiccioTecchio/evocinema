/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package database;

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
/*
public class UtenteRegistratoDAOTest {
    
    private static final String EMAIL = "email@email.it";
    private static final String NOMEUTENTE = "nomeUtente";
    private static final String PASSWORD = "password";
    private static final String NOME = "nome";
    private static final String COGNOME = "cognome";
    private static final String CELLULARE = "1234567890";
    private static final String CITTA = "citta";
    private static final String INDIRIZZO = "via Roma 7";
    
    public UtenteRegistratoDAOTest() {
    }
    
    @Test
    public void createTest() throws NamingException, SQLException, ParseException{
        UtenteRegistratoDAO x = new UtenteRegistratoDAO();
        
        Calendar dataNascita = Calendar.getInstance();
        dataNascita.set(2000, 1, 1);
        
        UtenteBase a = new UtenteBase(Float.NaN, EMAIL, NOMEUTENTE, PASSWORD, UtenteRegistrato.ruolo.UTENTE, NOME, COGNOME, dataNascita, UtenteRegistrato.sesso.M, CELLULARE, CITTA, INDIRIZZO);
        x.createUtenteRegistrato(a);
        UtenteBase b = x.foundUtenteBaseByEmail(EMAIL);
        assertNotNull(b);
        assertEquals(a.getEmail(), b.getEmail());
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