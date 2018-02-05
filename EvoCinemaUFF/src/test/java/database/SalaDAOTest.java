/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Antonio
 */
public class SalaDAOTest {
    
    private static final int IDSALA = 100;
    private static final int NUMEROPOSTI = 82;
    private static final String CONFIGPOSTI = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111100000000000000001111111111111100000000000000001111111111111100000000000000000000000000000000000000000011101111111111111101110000000011101111111111111101110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    
    private static java.sql.Connection connection;
    private static SalaDAO salaDAO;
    
    public SalaDAOTest() {
    }
    
    private static java.sql.Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco");
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException, NamingException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        salaDAO = new SalaDAO((com.mysql.jdbc.Connection) connection);
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
     * Test of getAllSale method, of class SalaDAO.
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
     * Test of foundByID method, of class SalaDAO.
     */
    @Test
    public void testFoundByID() throws Exception {
        System.out.println("foundByID");
        Sala s = new Sala(IDSALA, NUMEROPOSTI, CONFIGPOSTI);
        salaDAO.createSala(s);        
        int expResult = IDSALA;
        Sala mySala = salaDAO.foundByID(IDSALA);
        if(mySala instanceof Sala){
            int result = mySala.getIdSala();
            assertEquals(expResult, result);
        }        
        salaDAO.deleteSale(IDSALA);
        
        
    }

    /**
     * Test of createSala method, of class SalaDAO.
     */
    @Test
    public void testCreateSala() throws Exception {
        System.out.println("createSala");
        Sala s = new Sala(IDSALA, NUMEROPOSTI, CONFIGPOSTI);
        boolean expResult = true;
        assertEquals(expResult, salaDAO.createSala(s));
        salaDAO.deleteSale(IDSALA);
    }

    /**
     * Test of updateSala method, of class SalaDAO.
     */
    @Test
    public void testUpdateSala() throws Exception {
        System.out.println("updateSala");
        Sala s = new Sala(IDSALA, NUMEROPOSTI, CONFIGPOSTI);
        salaDAO.createSala(s);
        int newPosti = 80;
        boolean expResult = true;
        s.setNumeroPosti(newPosti);
        boolean result = salaDAO.updateSala(s);
        assertEquals(expResult, result);
        salaDAO.deleteSale(IDSALA);
    }

    /**
     * Test of deleteSale method, of class SalaDAO.
     */
    @Test
    public void testDeleteSale() throws Exception {
        System.out.println("deleteSale");
        Sala s = new Sala(IDSALA, NUMEROPOSTI, CONFIGPOSTI);
        salaDAO.createSala(s);
        boolean expResult = true;
        boolean result = salaDAO.deleteSale(IDSALA);
        assertEquals(expResult, result);
    }
    
}
