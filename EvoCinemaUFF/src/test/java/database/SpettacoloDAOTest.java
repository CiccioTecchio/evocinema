/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
 *
 * @author Antonio
 */
public class SpettacoloDAOTest {
    
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
    
    public SpettacoloDAOTest() {
        DATAINIZIO = Calendar.getInstance();
        DATAINIZIO.set(2018, 02, 04);
        DATAFINE = Calendar.getInstance();
        DATAFINE.set(2018, 10, 05);
        ORAINIZIO = Calendar.getInstance();
        ORAINIZIO.set(0, 0, 0, 17, 30);
        ORAFINE = Calendar.getInstance();
        ORAFINE.set(0, 0, 0, 19, 16);
    }
    
    private static java.sql.Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco");
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException{
        connection = getTestConnection();
        connection.setAutoCommit(false);
        spettacoloDAO = new SpettacoloDAO((com.mysql.jdbc.Connection) connection);
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
     * Test of getAllSpettacoli method, of class SpettacoloDAO.
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
     * Test of foundByID method, of class SpettacoloDAO.
     */
    @Test
    public void testFoundByID() throws Exception {
        System.out.println("foundByID");
        Spettacolo spett = new Spettacolo(IDSALA, IDFILM, TITOLO, DATAINIZIO, DATAFINE, PREZZO, ORAINIZIO, ORAFINE, MATRICEPOSTI);
        spettacoloDAO.createSpettacolo(spett);
        //cerco il film per ricavarne l'ID autogenerato
        List<Spettacolo> listaSpettacoli = spettacoloDAO.getAllSpettacoli();
        Spettacolo mySpett = null;
        for(Spettacolo s: listaSpettacoli){
            if(s.getTitolo().equals(TITOLO)){
                mySpett = s;
            }
        }
        int expResult = mySpett.getIdSpettacolo();
        int result = spettacoloDAO.foundByID(mySpett.getIdSpettacolo()).getIdSpettacolo();
        assertEquals(expResult, result);
        spettacoloDAO.deleteSpettacolo(mySpett.getIdSpettacolo());
    }

    /**
     * Test of foundBySala method, of class SpettacoloDAO.
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
     * Test of foundByDate method, of class SpettacoloDAO.
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
     * Test of foundByOpera method, of class SpettacoloDAO.
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
     * Test of createSpettacolo method, of class SpettacoloDAO.
     */
    @Test
    public void testCreateSpettacolo() throws Exception {
        System.out.println("createSpettacolo");
        Spettacolo spett = new Spettacolo(IDSALA, IDFILM, TITOLO, DATAINIZIO, DATAFINE, PREZZO, ORAINIZIO, ORAFINE, MATRICEPOSTI);
        boolean expResult = true;
        assertEquals(expResult, spettacoloDAO.createSpettacolo(spett));
        //cerco il film per ricavarne l'ID autogenerato
        List<Spettacolo> listaSpettacoli = spettacoloDAO.getAllSpettacoli();
        Spettacolo mySpett = null;
        for(Spettacolo s: listaSpettacoli){
            if(s.getTitolo().equals(TITOLO)){
                mySpett = s;
            }
        }
        spettacoloDAO.deleteSpettacolo(mySpett.getIdSpettacolo());
    }

    /**
     * Test of updateSpettacolo method, of class SpettacoloDAO.
     */
    @Test
    public void testUpdateSpettacolo() throws Exception {
        System.out.println("updateSpettacolo");
        Spettacolo spett = new Spettacolo(IDSALA, IDFILM, TITOLO, DATAINIZIO, DATAFINE, PREZZO, ORAINIZIO, ORAFINE, MATRICEPOSTI);
        spettacoloDAO.createSpettacolo(spett);
        int newIdSala = 7;
        boolean expResult = true;
        spett.setIdSala(newIdSala);
        boolean result = spettacoloDAO.updateSpettacolo(spett);
        assertEquals(expResult, result);
         //cerco il film per ricavarne l'ID autogenerato
        List<Spettacolo> listaSpettacoli = spettacoloDAO.getAllSpettacoli();
        Spettacolo mySpett = null;
        for(Spettacolo s: listaSpettacoli){
            if(s.getTitolo().equals(TITOLO)){
                mySpett = s;
            }
        }
        spettacoloDAO.deleteSpettacolo(mySpett.getIdSpettacolo());
    }

    /**
     * Test of deleteSpettacolo method, of class SpettacoloDAO.
     */
    @Test
    public void testDeleteSpettacolo() throws Exception {
        System.out.println("deleteSpettacolo");
        Spettacolo spett = new Spettacolo(IDSALA, IDFILM, TITOLO, DATAINIZIO, DATAFINE, PREZZO, ORAINIZIO, ORAFINE, MATRICEPOSTI);
        spettacoloDAO.createSpettacolo(spett);
        boolean expResult = true;
         //cerco il film per ricavarne l'ID autogenerato
        List<Spettacolo> listaSpettacoli = spettacoloDAO.getAllSpettacoli();
        Spettacolo mySpett = null;
        for(Spettacolo s: listaSpettacoli){
            if(s.getTitolo().equals(TITOLO)){
                mySpett = s;
            }
        }
        assertEquals(expResult, spettacoloDAO.deleteSpettacolo(mySpett.getIdSpettacolo()));
    }
    
}
