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
import java.text.ParseException;
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
    
    /**
     * Variabili che utilizziamo per istanziare l'oggetto Spettacolo per i Test.
     */
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
    private static Film film, myFilm;
    private static int idFilm;
    
    /**
     * Costruttore.
     */
    public FilmDAOTest() {
        
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
     * Metodo che inizializza la connessione, imposta l'autocommit a false per non sporcare il database,
     * inizializza l'oggetto Film che utilizzeremo per i test e ne ricava l'id autogenerato dal DB.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException
     */
    @BeforeClass
    public static void setUpClass() throws SQLException, NamingException, ParseException{
        connection = getTestConnection();
        connection.setAutoCommit(false);
        setMyFilm();
        setIdReale();
    }
    
    /**
     * Metodo che istanzia l'oggetto Film che utilizzeremo per effettuare i test.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setMyFilm() throws SQLException, NamingException, ParseException{
        filmDAO = new FilmDAO((Connection) connection);
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2006, 3, 17);
        film = new Film(Film.tipo.FILM, TITOLO, LOCANDINA, REGIA, CAST, GENERE, DURATA, dataUscita, Film.vistoCensura.VM14, DISTRIBUZIONE, PRODUZIONE, TRAMA, TRAILER);        
        filmDAO.createFilm(film);
    }
    
    /**
     * Metodo che ricava l'id autogenerato dal DB quando inseriamo l'oggetto Film.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setIdReale() throws SQLException, ParseException, NamingException{
        //cerco il film per ricavarne l'ID autogenerato
        List<Film> listaFilm = filmDAO.getAllFilm();
        myFilm = null;
        for(Film f: listaFilm){
            if(f.getTitolo().equals(TITOLO)){
                myFilm = f;
            }
        }
        idFilm = myFilm.getIdFilm();
    }
    
    /**
     * Metodo che elimina dal DB il nostro Film e chiude la connessione.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    @AfterClass
    public static void tearDownClass() throws SQLException, ParseException, NamingException {
        filmDAO.deleteFilm(idFilm);
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
     * Test del metodo getAllOpere, della classe FilmDAO.
     * @throws java.lang.Exception
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
     * Test del metodo getAllFilm, della classe FilmDAO.
     * @throws java.lang.Exception
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
     * Test del metodo getFilmNameAndDate, della classe FilmDAO.
     * @throws java.lang.Exception
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
     * Test del metodo getAllTeatro, della classe FilmDAO.
     * @throws java.lang.Exception
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
     * Test del metodo foundByID, della classe FilmDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundByID() throws Exception {
        System.out.println("foundByID");
        int expResult = idFilm;
        int result = filmDAO.foundByID(idFilm).getIdFilm();
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo isExistByTitolo, della classe FilmDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testIsExistByTitolo() throws Exception {
        System.out.println("isExistByTitolo");
        boolean expResult = true;
        assertEquals(expResult, filmDAO.isExistByTitolo(film.getTitolo()));
    }

    /**
     * Test del metodo createFilm, della classe FilmDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateFilm() throws Exception {
        System.out.println("createFilm");
        filmDAO.deleteFilm(idFilm);
        boolean expResult = true;
        assertEquals(expResult, filmDAO.createFilm(film));
        setIdReale();           
    }

    /**
     * Test del metodo updateFilm, della classe FilmDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateFilm() throws Exception {
        System.out.println("updateFilm");
        String newRegia = "Wakowsky brothers";
        boolean expResult = true;
        film.setRegia(newRegia);
        boolean result = filmDAO.updateFilm(film);
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo deleteFilm, della classe FilmDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteFilm() throws Exception {
        System.out.println("deleteFilm");
        boolean expResult = true;
        assertEquals(expResult, filmDAO.deleteFilm(myFilm.getIdFilm()));
        setMyFilm();
        setIdReale();
    }
    
}
