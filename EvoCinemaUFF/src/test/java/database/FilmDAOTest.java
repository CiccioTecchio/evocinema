/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import javax.naming.NamingException;
import model.Film;
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
public class FilmDAOTest {
    
    private static final String TITOLO = "V per Vendetta";
    private static final String LOCANDINA = "C:\\Users\\Antonio\\Desktop\\VForV.jpg";
    private static final String REGIA = "James McTeigue";
    private static final String CAST = "Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt, Stephen Fry ";
    private static final String GENERE = "azione, drammatico, thriller";
    private static final Time DURATA = new Time(0, 132, 0);
    private static final String DISTRIBUZIONE = "Warner Bros. Pictures";
    private static final String PRODUZIONE = "Regno Unito, Germania";
    private static final String TRAMA = "In un futuro alternativo la Germania, vincitrice della seconda guerra mondiale,"
                                        + " ha trasformato la Gran Bretagna in un paese nazista, governato con il pugno di ferro "
                                        + "da una tirannia mediatica degna di un romanzo orwelliano. Contro questo regime totalitario "
                                        + "si scaglia un misterioso uomo mascherato: carismatico e spietato, straordinariamente esperto "
                                        + "dellarte del combattimento e dell'inganno, \"V\" provoca una serie di atti terroristici cercando "
                                        + "di esortare i suoi concittadini a ribellarsi alla tirannia e all'oppressione. In questa sua lotta solitaria "
                                        + "il giovane troverà una inaspettata alleata, Evey Hammond, una ragazza salvata dalle grinfie della polizia segreta.";    
    private static final String TRAILER = "https://www.youtube.com/watch?v=8c3HGPz6BI4";
    private static java.sql.Connection connection;
    private static FilmDAO filmDAO;
    
    public FilmDAOTest() {
        
    }
    
    private static java.sql.Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco");
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException, NamingException{
        connection = getTestConnection();
        connection.setAutoCommit(false);
        filmDAO = new FilmDAO((Connection) connection);
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
     * Test of getAllOpere method, of class FilmDAO.
     */
    @Test
    public void testGetAllOpere() throws Exception {
        System.out.println("getAllOpere");
        List<Film> result = filmDAO.getAllOpere();
        boolean expResult = false;
        for(Film f: result){
            if(f instanceof Film){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test of getAllFilm method, of class FilmDAO.
     */
    @Test
    public void testGetAllFilm() throws Exception {
        System.out.println("getAllFilm");
        List<Film> result = filmDAO.getAllFilm();
        boolean expResult = false;
        for(Film f: result){
            if(f instanceof Film){
                if(f.getTipo().equals(Film.tipo.FILM)){
                    expResult = true;
                }
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test of getFilmNameAndDate method, of class FilmDAO.
     */
    @Test
    public void testGetFilmNameAndDate() throws Exception {
        System.out.println("getFilmNameAndDate");
        List<Film> result = filmDAO.getFilmNameAndDate();
        boolean expResult = false;
        for(Film f: result){
            if(f instanceof Film){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test of getAllTeatro method, of class FilmDAO.
     */
    @Test
    public void testGetAllTeatro() throws Exception {
        System.out.println("getAllTeatro");
        List<Film> result = filmDAO.getAllTeatro();
        boolean expResult = false;
        for(Film f: result){
            if(f instanceof Film){
                if(f.getTipo().equals(Film.tipo.TEATRO)){
                    expResult = true;
                }
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test of foundByID method, of class FilmDAO.
     */
    @Test
    public void testFoundByID() throws Exception {
        System.out.println("foundByID");
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2006, 3, 17);
        Film v = new Film(Film.tipo.FILM, TITOLO, LOCANDINA, REGIA, CAST, GENERE, DURATA, dataUscita, Film.vistoCensura.VM14, DISTRIBUZIONE, PRODUZIONE, TRAMA, TRAILER);        
        filmDAO.createFilm(v);
        //cerco il film per ricavarne l'ID autogenerato
        List<Film> listaFilm = filmDAO.getAllFilm();
        Film myFilm = null;
        for(Film f: listaFilm){
            if(f.getTitolo().equals(TITOLO)){
                myFilm = f;
            }
        }
        int expResult = myFilm.getIdFilm();
        int result = filmDAO.foundByID(myFilm.getIdFilm()).getIdFilm();
        assertEquals(expResult, result);
        filmDAO.deleteFilm(myFilm.getIdFilm());
    }

    /**
     * Test of isExistByTitolo method, of class FilmDAO.
     */
    @Test
    public void testIsExistByTitolo() throws Exception {
        System.out.println("isExistByTitolo");
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2006, 3, 17);
        Film v = new Film(Film.tipo.FILM, TITOLO, LOCANDINA, REGIA, CAST, GENERE, DURATA, dataUscita, Film.vistoCensura.VM14, DISTRIBUZIONE, PRODUZIONE, TRAMA, TRAILER);        
        filmDAO.createFilm(v);
        boolean expResult = true;
        assertEquals(expResult, filmDAO.isExistByTitolo(v.getTitolo()));
        //cerco il film per ricavarne l'ID autogenerato
        List<Film> listaFilm = filmDAO.getAllFilm();
        Film myFilm = null;
        for(Film f: listaFilm){
            if(f.getTitolo().equals(TITOLO)){
                myFilm = f;
            }
        }
        filmDAO.deleteFilm(myFilm.getIdFilm());
    }

    /**
     * Test of createFilm method, of class FilmDAO.
     */
    @Test
    public void testCreateFilm() throws Exception {
        System.out.println("createFilm");
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2006, 3, 17);
        Film v = new Film(Film.tipo.FILM, TITOLO, LOCANDINA, REGIA, CAST, GENERE, DURATA, dataUscita, Film.vistoCensura.VM14, DISTRIBUZIONE, PRODUZIONE, TRAMA, TRAILER);        
        boolean expResult = true;
        assertEquals(expResult, filmDAO.createFilm(v));
        //cerco il film per ricavarne l'ID autogenerato
        List<Film> listaFilm = filmDAO.getAllFilm();
        Film myFilm = null;
        for(Film f: listaFilm){
            if(f.getTitolo().equals(TITOLO)){
                myFilm = f;
            }
        }
        filmDAO.deleteFilm(myFilm.getIdFilm());            
    }

    /**
     * Test of updateFilm method, of class FilmDAO.
     */
    @Test
    public void testUpdateFilm() throws Exception {
        System.out.println("updateFilm");
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2006, 3, 17);
        Film v = new Film(Film.tipo.FILM, TITOLO, LOCANDINA, REGIA, CAST, GENERE, DURATA, dataUscita, Film.vistoCensura.VM14, DISTRIBUZIONE, PRODUZIONE, TRAMA, TRAILER);        
        filmDAO.createFilm(v);
        String newRegia = "Wakowsky brothers";
        boolean expResult = true;
        v.setRegia(newRegia);
        boolean result = filmDAO.updateFilm(v);
        assertEquals(expResult, result);
        //cerco il film per ricavarne l'ID autogenerato
        List<Film> listaFilm = filmDAO.getAllFilm();
        Film myFilm = null;
        for(Film f: listaFilm){
            if(f.getTitolo().equals(TITOLO)){
                myFilm = f;
            }
        }
        filmDAO.deleteFilm(myFilm.getIdFilm());
    }

    /**
     * Test of deleteFilm method, of class FilmDAO.
     */
    @Test
    public void testDeleteFilm() throws Exception {
        System.out.println("deleteFilm");
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2006, 3, 17);
        Film v = new Film(Film.tipo.FILM, TITOLO, LOCANDINA, REGIA, CAST, GENERE, DURATA, dataUscita, Film.vistoCensura.VM14, DISTRIBUZIONE, PRODUZIONE, TRAMA, TRAILER);        
        filmDAO.createFilm(v);
        boolean expResult = true;
        List<Film> listaFilm = filmDAO.getAllFilm();
        Film myFilm = null;
        for(Film f: listaFilm){
            if(f.getTitolo().equals(TITOLO)){
                myFilm = f;
            }
        }
        assertEquals(expResult, filmDAO.deleteFilm(myFilm.getIdFilm()));
       
    }
    
}
