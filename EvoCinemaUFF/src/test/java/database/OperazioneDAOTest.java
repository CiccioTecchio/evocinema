/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import javax.naming.NamingException;
import model.Acquisto;
import model.Operazione;
import model.Prenotazione;
import model.Sala;
import model.Sconto;
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
public class OperazioneDAOTest {
    
    private static final String EMAIL = "ABergamaschi@gmail.com ";
    private static final int IDSPETTACOLO = 54;
    private static final int POSTO = 16;
    private static final int OFFSET = 3;
//    private static final int IDSALA = 100;
//    private static final int NUMEROPOSTI = 82;
//    private static final String CONFIGPOSTI = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111100000000000000001111111111111100000000000000001111111111111100000000000000000000000000000000000000000011101111111111111101110000000011101111111111111101110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    private static  Sala SALA;
    private static final float PREZZOFINALE = 5.0f;
    private static  Sconto SCONTO; // = new Sconto(57, "mioSconto", 20, 0.0f, "SATURDAY", Sconto.verificabile.FALSE, Sconto.tipo.PERCENTUALE, Sconto.disponibile.TRUE, Sconto.tipologia.GIORNO_SETTIMANA);
    
    
    private static java.sql.Connection connection;
    private static OperazioneDAO operazioneDAO;
    private static ScontoDAO scontoDAO;
    private static SalaDAO salaDAO;
    
    public OperazioneDAOTest() {
    }
    
    private static java.sql.Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco");        
    }
    
    @BeforeClass
    public static void setUpClass() throws NamingException, SQLException, ParseException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        operazioneDAO = new OperazioneDAO((Connection) connection);
        salaDAO = new SalaDAO((Connection) connection);
        SALA = salaDAO.foundByID(6);
        scontoDAO = new ScontoDAO((Connection) connection);
        SCONTO = scontoDAO.foundByID(41);
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException, ParseException, NamingException {
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
     * Test of getAllOperazioni method, of class OperazioneDAO.
     */
    @Test
    public void testGetAllOperazioni() throws Exception {
        System.out.println("getAllOperazioni");
        List<Operazione> result = operazioneDAO.getAllOperazioni();
        boolean expResult = false;
        for(Operazione o: result){
            if(o instanceof Operazione){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test of getPrenotazioni method, of class OperazioneDAO.
     */
    @Test
    public void testGetPrenotazioni() throws Exception {
        System.out.println("getPrenotazioni");
        List<Prenotazione> result = operazioneDAO.getPrenotazioni();
        boolean expResult = false;
        for(Prenotazione p: result){
            if(p instanceof Prenotazione){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
        
    }

    /**
     * Test of getPrenotazioniUtente method, of class OperazioneDAO.
     */
    @Test
    public void testGetPrenotazioniUtente() throws Exception {
        System.out.println("getPrenotazioniUtente");
        String email = "ABergamaschi@gmail.com";
        List<Prenotazione> result = operazioneDAO.getPrenotazioniUtente(email);
        boolean expResult = false;
        for(Prenotazione p: result){
            if(p instanceof Prenotazione){
                expResult = true;
            }
        }
    }

    /**
     * Test of getAcquisti method, of class OperazioneDAO.
     */
    @Test
    public void testGetAcquisti() throws Exception {
        System.out.println("getAcquisti");
        List<Acquisto> result = operazioneDAO.getAcquisti();
        boolean expResult = false;
        for(Acquisto a: result){
            if(a instanceof Acquisto){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test of getAcquistiUtente method, of class OperazioneDAO.
     */
    @Test
    public void testGetAcquistiUtente() throws Exception {
        System.out.println("getAcquistiUtente");
        String email = "ABergamaschi@gmail.com";
        List<Acquisto> result = operazioneDAO.getAcquistiUtente(email);
        boolean expResult = false;
        for(Acquisto a: result){
            if(a instanceof Acquisto){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test of foundByID method, of class OperazioneDAO.
     */
    @Test
    public void testFoundByID() throws Exception {
        System.out.println("foundByID");
        Calendar data = Calendar.getInstance();
        data.set(2018, 02, 05);
        Operazione o = new Acquisto(EMAIL, IDSPETTACOLO, POSTO, OFFSET, SALA, Operazione.prenotato.FALSE, Operazione.acquistato.TRUE, PREZZOFINALE, data, SCONTO);
        operazioneDAO.createOperazione(o);
        //adesso devo trovare l'id reale del mio acquisto
        List<Acquisto> listaAcquisti = operazioneDAO.getAcquistiUtente(EMAIL);
        Acquisto mioAcquisto = null;
        for(Acquisto a: listaAcquisti){
            if(a.getIdSpettacolo() == IDSPETTACOLO && 
               a.getSala().getIdSala() == SALA.getIdSala() &&
               a.getPosto() == POSTO){
                mioAcquisto = a;
            }
        }
        int expResult = mioAcquisto.getIdOperazione();
        int result = operazioneDAO.foundByID(expResult).getIdOperazione();
        assertEquals(expResult, result);
        operazioneDAO.deleteOperazione(result);
    }

    /**
     * Test of createOperazione method, of class OperazioneDAO.
     */
    @Test
    public void testCreateOperazione() throws Exception {
        System.out.println("createOperazione");
        Calendar data = Calendar.getInstance();
        data.set(2018, 02, 05);
        Operazione o = new Acquisto(EMAIL, IDSPETTACOLO, POSTO, OFFSET, SALA, Operazione.prenotato.FALSE, Operazione.acquistato.TRUE, PREZZOFINALE, data, SCONTO);
        boolean expResult = true;
        assertEquals(expResult, operazioneDAO.createOperazione(o));
        //adesso devo trovare l'id reale del mio acquisto
        List<Acquisto> listaAcquisti = operazioneDAO.getAcquistiUtente(EMAIL);
        Acquisto mioAcquisto = null;
        for(Acquisto a: listaAcquisti){
            if(a.getIdSpettacolo() == IDSPETTACOLO && 
               a.getSala().getIdSala() == SALA.getIdSala() &&
               a.getPosto() == POSTO){
                mioAcquisto = a;
            }
        }
        operazioneDAO.deleteOperazione(mioAcquisto.getIdOperazione());
    }

    /**
     * Test of updateOperazione method, of class OperazioneDAO.
     */
    @Test
    public void testUpdateOperazione() throws Exception {
        System.out.println("updateOperazione");
        Calendar data = Calendar.getInstance();
        data.set(2018, 02, 05);
        Operazione o = new Acquisto(EMAIL, IDSPETTACOLO, POSTO, OFFSET, SALA, Operazione.prenotato.FALSE, Operazione.acquistato.TRUE, PREZZOFINALE, data, SCONTO);
        operazioneDAO.createOperazione(o);
        int newPosto = 17;
        boolean expResult = true;
        o.setPosto(newPosto);
        boolean result = operazioneDAO.updateOperazione(o);
        assertEquals(expResult, result);
        //adesso devo trovare l'id reale del mio acquisto
        List<Acquisto> listaAcquisti = operazioneDAO.getAcquistiUtente(EMAIL);
        Acquisto mioAcquisto = null;
        for(Acquisto a: listaAcquisti){
            if(a.getIdSpettacolo() == IDSPETTACOLO && 
               a.getSala().getIdSala() == SALA.getIdSala() &&
               a.getPosto() == POSTO){
                mioAcquisto = a;
            }
        }
        operazioneDAO.deleteOperazione(mioAcquisto.getIdOperazione());        
    }

    /**
     * Test of deleteOperazione method, of class OperazioneDAO.
     */
    @Test
    public void testDeleteOperazione() throws Exception {
        System.out.println("deleteOperazione");
        Calendar data = Calendar.getInstance();
        data.set(2018, 02, 05);
        Operazione o = new Acquisto(EMAIL, IDSPETTACOLO, POSTO, OFFSET, SALA, Operazione.prenotato.FALSE, Operazione.acquistato.TRUE, PREZZOFINALE, data, SCONTO);
        operazioneDAO.createOperazione(o);
        boolean expResult = true;
        //adesso devo trovare l'id reale del mio acquisto
        List<Acquisto> listaAcquisti = operazioneDAO.getAcquistiUtente(EMAIL);
        Acquisto mioAcquisto = null;
        for(Acquisto a: listaAcquisti){
            if(a.getIdSpettacolo() == IDSPETTACOLO && 
               a.getSala().getIdSala() == SALA.getIdSala() &&
               a.getPosto() == POSTO){
                mioAcquisto = a;
            }
        }
        assertEquals(expResult, operazioneDAO.deleteOperazione(mioAcquisto.getIdOperazione()));
    }

    /**
     * Test of analyticsGetDatiOperazioni method, of class OperazioneDAO.
     */
    @Test
    public void testAnalyticsGetDatiOperazioni() throws Exception {
        System.out.println("analyticsGetDatiOperazioni");
        String scelta = "Operazioni";
        boolean expResult = true;
        String s = operazioneDAO.analyticsGetDatiOperazioni(scelta);
        boolean result = false;
        if(s instanceof String){
            result = true;
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of analyticsGetDatiAffluenzeSpettacolo method, of class OperazioneDAO.
     */
    @Test
    public void testAnalyticsGetDatiAffluenzeSpettacolo() throws Exception {
        System.out.println("analyticsGetDatiAffluenzeSpettacolo");
        String minAffluenza = "1";
        boolean expResult = true;
        String s = operazioneDAO.analyticsGetDatiAffluenzeSpettacolo(minAffluenza);
        boolean result = false;
        if(s instanceof String){
            result = true;
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of analyticsGetDatiIncassi method, of class OperazioneDAO.
     */
    @Test
    public void testAnalyticsGetDatiIncassi() throws Exception {
        System.out.println("analyticsGetDatiIncassi");
        String minIncasso = "5.0";
        boolean expResult = true;
        String s = operazioneDAO.analyticsGetDatiIncassi(minIncasso);
        boolean result = false;
        if(s instanceof String){
            result = true;
        }
        assertEquals(expResult, result);
    }
    
}
