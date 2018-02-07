package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
 * Classe di test della classe SalaDAO.
 *
 * @author Antonio
 */
public class SalaDAOTest {

    /*
    * Variabili utilizzate per creare gli oggetti necessari al test
     */
    private static final int IDSALA = 100;
    private static final int NUMEROPOSTI = 82;
    private static final String CONFIGPOSTI = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111100000000000000001111111111111100000000000000001111111111111100000000000000000000000000000000000000000011101111111111111101110000000011101111111111111101110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

    private static java.sql.Connection connection;
    private static SalaDAO salaDAO;

    /**
     * Costruttore vuoto
     */
    public SalaDAOTest() {
    }

    private static java.sql.Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco");
    }

    /**
     * Metodo per la connessione al DB
     *
     * @throws SQLException
     * @throws NamingException
     */
    @BeforeClass
    public static void setUpClass() throws SQLException, NamingException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        salaDAO = new SalaDAO((com.mysql.jdbc.Connection) connection);
    }

    /**
     * Evita che i cambiamenti effettuati dal test vengano resi persistenti
     *
     * @throws SQLException
     */
    @AfterClass
    public static void tearDownClass() throws SQLException {
        connection.rollback();
        connection.close();
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test del metodo getAllSale della classe SalaDAO.
     */
    @Test
    public void testGetAllSale() throws Exception {
        System.out.println("getAllSale");
        List<Sala> result = salaDAO.getAllSale();
        boolean expResult = false;
        for (Sala s : result) {
            if (s instanceof Sala) {
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test del metodo foundByID della classe SalaDAO.
     */
    @Test
    public void testFoundByID() throws Exception {
        System.out.println("foundByID");
        Sala s = new Sala();
        s.setConfigPosti(CONFIGPOSTI);
        s.setNumeroPosti(NUMEROPOSTI);
        salaDAO.createSala(s);
        //devo ricavare l'id reale
        List<Sala> listaSale = salaDAO.getAllSale();
        int def = 0;
        int curr = 0;
        for (Sala sala : listaSale) {
            curr = sala.getIdSala();
            if (curr > def) {
                def = curr;
            }
        }
        int expResult = def;
        int result = salaDAO.foundByID(def).getIdSala();
        assertEquals(expResult, result);
        salaDAO.deleteSale(result);
    }

    /**
     * Test del metodo createSala della classe SalaDAO.
     */
    @Test
    public void testCreateSala() throws Exception {
        System.out.println("createSala");
        Sala s = new Sala();
        s.setConfigPosti(CONFIGPOSTI);
        s.setNumeroPosti(NUMEROPOSTI);
        boolean expResult = true;
        assertEquals(expResult, salaDAO.createSala(s));
        //devo ricavare l'id reale
        List<Sala> listaSale = salaDAO.getAllSale();
        int def = 0;
        int curr = 0;
        for (Sala sala : listaSale) {
            curr = sala.getIdSala();
            if (curr > def) {
                def = curr;
            }
        }
        salaDAO.deleteSale(def);

    }

    /**
     * Test del metodo updateSala della classe SalaDAO.
     */
    @Test
    public void testUpdateSala() throws Exception {
        System.out.println("updateSala");
        Sala s = new Sala();
        s.setConfigPosti(CONFIGPOSTI);
        s.setNumeroPosti(NUMEROPOSTI);
        salaDAO.createSala(s);
        int newPosti = 80;
        boolean expResult = true;
        s.setNumeroPosti(newPosti);
        boolean result = salaDAO.updateSala(s);
        assertEquals(expResult, result);
        //devo ricavare l'id reale
        List<Sala> listaSale = salaDAO.getAllSale();
        int def = 0;
        int curr = 0;
        for (Sala sala : listaSale) {
            curr = sala.getIdSala();
            if (curr > def) {
                def = curr;
            }
        }
        salaDAO.deleteSale(def);
    }

    /**
     * Test del metodo deleteSale della classe SalaDAO.
     */
    @Test
    public void testDeleteSale() throws Exception {
        System.out.println("deleteSale");
        Sala s = new Sala();
        s.setConfigPosti(CONFIGPOSTI);
        s.setNumeroPosti(NUMEROPOSTI);
        salaDAO.createSala(s);
        boolean expResult = true;
        //devo ricavare l'id reale
        List<Sala> listaSale = salaDAO.getAllSale();
        int def = 0;
        int curr = 0;
        for (Sala sala : listaSale) {
            curr = sala.getIdSala();
            if (curr > def) {
                def = curr;
            }
        }
        boolean result = salaDAO.deleteSale(def);
        assertEquals(expResult, result);
    }

}
