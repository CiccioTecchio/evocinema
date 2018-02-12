/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import model.FilmConValutazioneMedia;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe che testa tutti i metodi della classe FilmValutazioneDAO
 * @author Antonio
 */
public class FilmValutazioneDAOTest {
    
    private static java.sql.Connection connection;
    private static FilmValutazioneDAO filmValutazioneDAO;
    
    /**
     * Costruttore
     */
    public FilmValutazioneDAOTest() {
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
     * Metodo che inizializza la connessione, imposta l'autocommit a false per non sporcare il database.
     * @throws SQLException
     * @throws NamingException
     */
    @BeforeClass
    public static void setUpClass() throws SQLException, NamingException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        filmValutazioneDAO = new FilmValutazioneDAO((Connection) connection);
    }
    
    /**
     * Metodo che chiude la connessione.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    @AfterClass
    public static void tearDownClass() throws SQLException {
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
     * Test del metodo getAllFilmValutazioni, della classe FilmValutazioneDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllFilmValutazioni() throws Exception {
        System.out.println("getAllFilmValutazioni");
        boolean expResult = false;
        List<FilmConValutazioneMedia> result = filmValutazioneDAO.getAllFilmValutazioni();
        for(FilmConValutazioneMedia f: result){
            if(f instanceof FilmConValutazioneMedia){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }
    
}
