/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import javax.naming.NamingException;
import model.Film;
import model.Recensione;
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
public class RecensioneDAOTest {

    private static java.sql.Connection connection;
    private static RecensioneDAO recensioneDAO;

    private static final String emailUtente = "aRossi@gmail.com";
    private static final String emailUtente2 = "giuseppe.dambrosio14@gmail.com";
    private static final String emailUtente3 = "prova@prova.it";
    private static final String emailUtente4 = "LuisaBoni@gmail.com";
    private static final String nomeUtente = "nomeutente";
    private static final float valutazione = 2f;
    private static final String testo = "recensione";

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

    public RecensioneDAOTest() {
    }

    private static java.sql.Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco");
    }

    @BeforeClass
    public static void setUpClass() throws SQLException, NamingException, ParseException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        recensioneDAO = new RecensioneDAO((com.mysql.jdbc.Connection) connection);

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

    @Test
    public void testGetAllRecensioni() throws Exception {
        List<Recensione> result = recensioneDAO.getAllRecensioni();
        boolean expResult = false;
        for (Recensione s : result) {
            if (s instanceof Recensione) {
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    @Test
    public void testFoundByEmail() throws Exception {
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2006, 3, 17);
        Film f = new Film(1, Film.tipo.FILM, TITOLO, LOCANDINA, REGIA, CAST, GENERE, DURATA, dataUscita, Film.vistoCensura.T, DISTRIBUZIONE, PRODUZIONE, TRAMA, TRAILER);
        Recensione r = new Recensione(emailUtente, f, valutazione, testo, dataUscita);
        recensioneDAO.createRecensione(r);
        List<Recensione> result = recensioneDAO.foundByEmail(emailUtente);
        boolean expResult = false;
        for (Recensione x : result) {
            if (r instanceof Recensione) {
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    @Test
    public void testFoundByFilm() throws Exception {
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2006, 3, 17);
        Film f = new Film(1, Film.tipo.FILM, TITOLO, LOCANDINA, REGIA, CAST, GENERE, DURATA, dataUscita, Film.vistoCensura.T, DISTRIBUZIONE, PRODUZIONE, TRAMA, TRAILER);
        Recensione r = new Recensione(emailUtente2, f, valutazione, testo, dataUscita);
        recensioneDAO.createRecensione(r);
        List<Recensione> result = recensioneDAO.foundByFilm(1);
        boolean expResult = false;
        for (Recensione x : result) {
            if (r instanceof Recensione) {
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    @Test
    public void testCreateRecensione() throws Exception {
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2006, 3, 17);
        Film f = new Film(1, Film.tipo.FILM, TITOLO, LOCANDINA, REGIA, CAST, GENERE, DURATA, dataUscita, Film.vistoCensura.T, DISTRIBUZIONE, PRODUZIONE, TRAMA, TRAILER);
        Recensione r = new Recensione(emailUtente3, f, valutazione, testo, dataUscita);
        boolean expResult = true;
        assertEquals(expResult, recensioneDAO.createRecensione(r));
        List<Recensione> listaRecensioni = recensioneDAO.getAllRecensioni();
        Recensione x = null;
        for (Recensione s : listaRecensioni) {
            if (s.getTesto().equals(testo)) {
                x = r;
            }
        }
    }

    /*    
    @Test
    public void testUpdateRecensione() throws Exception {
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2006, 3, 17);
        Film f = new Film(1, Film.tipo.FILM, TITOLO, LOCANDINA, REGIA, CAST, GENERE, DURATA, dataUscita, Film.vistoCensura.T, DISTRIBUZIONE, PRODUZIONE, TRAMA, TRAILER);
        Recensione r = new Recensione(emailUtente4, f, valutazione, testo, dataUscita);
        recensioneDAO.createRecensione(r);
        String newTesto = "Testo di prova";
        boolean expResult = true;
        Calendar data2 = Calendar.getInstance();
        data2.set(2010, 3, 17);
        r.setDataImmissione(data2);
        boolean result = recensioneDAO.updateRecensione(r);
        assertEquals(expResult, result);
        List<Recensione> lista = recensioneDAO.getAllRecensioni();
        Recensione x = null;
        for (Recensione y : lista) {
            if (y.getTesto().equals(testo)) {
                x = y;
            }
        }
    }*/
    @Test
    public void testDeleteFilm() throws Exception {
        Calendar dataUscita = Calendar.getInstance();
        dataUscita.set(2006, 3, 17);
        Film f = new Film(1, Film.tipo.FILM, TITOLO, LOCANDINA, REGIA, CAST, GENERE, DURATA, dataUscita, Film.vistoCensura.T, DISTRIBUZIONE, PRODUZIONE, TRAMA, TRAILER);
        Recensione r = new Recensione(emailUtente4, f, valutazione, testo, dataUscita);
        recensioneDAO.createRecensione(r);
        boolean expResult = true;
        List<Recensione> lista = recensioneDAO.getAllRecensioni();
        Recensione x = null;
        for (Recensione y : lista) {
            if (y.getTesto().equals(testo)) {
                x = y;
            }
        }
        assertEquals(expResult, recensioneDAO.deleteRecensione(x));

    }


}
