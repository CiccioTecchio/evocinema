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
    
    private static final int NUMEROPOSTI = 82;
    private static final String CONFIGPOSTI = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111100000000000000001111111111111100000000000000001111111111111100000000000000000000000000000000000000000011101111111111111101110000000011101111111111111101110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    
    private static java.sql.Connection connection;
    private static SalaDAO salaDAO;
    private static Sala sala;
    private static int idReale;
    
    public SalaDAOTest() {
    }
    
    private static java.sql.Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco");
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException, NamingException, ParseException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        salaDAO = new SalaDAO((com.mysql.jdbc.Connection) connection);
        sala = new Sala();
        sala.setConfigPosti(CONFIGPOSTI);
        sala.setNumeroPosti(NUMEROPOSTI);
        salaDAO.createSala(sala);
         //devo ricavare l'id reale
        List<Sala> listaSale = salaDAO.getAllSale();
        idReale = 0;
        int curr = 0;
        for(Sala sala: listaSale){
            curr = sala.getIdSala();
            if( curr > idReale){
                idReale = curr;
            }
        }
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException, ParseException, NamingException {
        salaDAO.deleteSale(idReale);   
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
        //devo ricavare l'id reale
        int expResult = idReale;
        int result = salaDAO.foundByID(idReale).getIdSala();
        assertEquals(expResult, result);             
    }

    /**
     * Test of createSala method, of class SalaDAO.
     */
    @Test
    public void testCreateSala() throws Exception {
        System.out.println("createSala");
        salaDAO.deleteSale(idReale);
        boolean expResult = true;
        assertEquals(expResult, salaDAO.createSala(sala));
         //devo ricavare l'id reale
        List<Sala> listaSale = salaDAO.getAllSale();
        idReale = 0;
        int curr = 0;
        for(Sala sala: listaSale){
            curr = sala.getIdSala();
            if( curr > idReale){
                idReale = curr;
            }
        }       
    }

    /**
     * Test of updateSala method, of class SalaDAO.
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
     * Test of deleteSale method, of class SalaDAO.
     */
    @Test
    public void testDeleteSale() throws Exception {
        System.out.println("deleteSale");
        boolean expResult = true;
        boolean result = salaDAO.deleteSale(idReale);
        assertEquals(expResult, result);
        salaDAO.createSala(sala);
        //devo ricavare l'id reale
        List<Sala> listaSale = salaDAO.getAllSale();
        idReale = 0;
        int curr = 0;
        for(Sala sala: listaSale){
            curr = sala.getIdSala();
            if( curr > idReale){
                idReale = curr;
            }
        }       
    }
    
}
