/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.naming.NamingException;
import model.Sconto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe che testa tutti i metodi della classe ScontoDAO
 * @author Antonio
 */
public class ScontoDAOTest {
    
    /**
     * Variabili che utilizziamo per istanziare l'oggetto Sconto per i Test.
     */
    private static final String NOME = "Sconto testing";
    private static final int PERCENTUALE = 50;
    private static final float PREZZO = 0.00f;
    private static final String PARAMETROTIPOLOGIA = "St. Patriks day";
    
    private static java.sql.Connection connection;
    private static ScontoDAO scontoDAO;
    private static Sconto sconto, mySconto;
    private static int idReale;
    
    /**
     * Costruttore.
     */
    public ScontoDAOTest() {
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
     * inizializza l'oggetto Sconto che utilizzeremo per i test e ne ricava l'id autogenerato dal DB.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException
     */
    @BeforeClass
    public static void setUpClass() throws SQLException, NamingException, ParseException {
        connection = getTestConnection();
        connection.setAutoCommit(false);
        scontoDAO = new ScontoDAO(connection);
        setMySconto();
        setIdReale();
    }
    
    /**
     * Metodo che elimina dal DB il nostro Sconto e chiude la connessione.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    @AfterClass
    public static void tearDownClass() throws SQLException, ParseException, NamingException {
        scontoDAO.deleteSconto(idReale);
        connection.rollback();
        connection.close();
    }
    
    /**
     * Metodo che viene eseguito prima di ogni metodo Test
     */
    @Before
    public void setUp() {
    }
    
    /**
     * Metodo che viene eseguito dopo di ogni metodo Test
     */
    @After
    public void tearDown() {
    }
    
    /**
     * Metodo che istanzia l'oggetto Sconto che utilizzeremo per effettuare i test.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setMySconto() throws SQLException, ParseException, NamingException {
        sconto = new Sconto(NOME, PERCENTUALE, PREZZO, PARAMETROTIPOLOGIA, Sconto.verificabile.FALSE, Sconto.tipo.PERCENTUALE, Sconto.disponibile.TRUE, Sconto.tipologia.ALTRO);
        scontoDAO.createSconto(sconto);
    }
    
    /**
     * Metodo che ricava l'id autogenerato dal DB quando inseriamo l'oggetto Sconto.
     * @throws NamingException
     * @throws SQLException
     * @throws ParseException 
     */
    private static void setIdReale() throws SQLException, ParseException, NamingException{
        List<Sconto> listaSconti = scontoDAO.getAllSconti();
        mySconto = null;
        idReale = 0;
        for(Sconto s: listaSconti){
            if(s.getNome().equals(sconto.getNome())){
                mySconto = s;
            }
        }
        idReale = mySconto.getIdSconto();
    }

    /**
     * Test del metodo getAllSconti, della classe ScontoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllSconti() throws Exception {
        System.out.println("getAllSconti");
        boolean expResult = false;
        List<Sconto> result = scontoDAO.getAllSconti();
        for(Sconto s: result){
            if(s instanceof Sconto){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test del metodo foundByID, della classe ScontoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundByID() throws Exception {
        System.out.println("foundByID");
        int expResult = idReale;
        int result = scontoDAO.foundByID(idReale).getIdSconto();
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo foundByNome, della classe ScontoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundByNome() throws Exception {
        System.out.println("foundByNome");
        String expResult = NOME;
        String result = scontoDAO.foundByNome(NOME).getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo foundByDisponibilità, della classe ScontoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundByDisponibilità() throws Exception {
        System.out.println("foundByDisponibilit\u00e0");
        boolean expResult = false;
        List<Sconto> result = scontoDAO.foundByDisponibilità();
        for(Sconto s: result){
            if(s instanceof Sconto){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test del metodo foundByTipo, della classe ScontoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundByTipo() throws Exception {
        System.out.println("foundByTipo");
        boolean expResult = false;
        List<Sconto> result = scontoDAO.foundByTipo(Sconto.tipo.PERCENTUALE);
        for(Sconto s: result){
            if(s instanceof Sconto && s.getTipo().equals(Sconto.tipo.PERCENTUALE)){
                expResult = true;
            }
            assertEquals(expResult, true);
        }
        boolean expResult1 = false;
        List<Sconto> result1 = scontoDAO.foundByTipo(Sconto.tipo.FISSO);
        for(Sconto s: result){
            if(s instanceof Sconto && s.getTipo().equals(Sconto.tipo.FISSO)){
                expResult1 = true;
            }
            assertEquals(expResult, true);
        }
    }

    /**
     * Test del metodo foundByTipologia, della classe ScontoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFoundByTipologia() throws Exception {
        System.out.println("foundByTipologia");
        List<Sconto> result1 = scontoDAO.foundByTipologia(Sconto.tipologia.GIORNO_SETTIMANA);
        boolean expResult1 = false;
        for(Sconto s: result1){
            if(s instanceof Sconto && s.getTipologia().equals(Sconto.tipologia.GIORNO_SETTIMANA)){
                expResult1 = true;
            }
            assertEquals(expResult1, true);
        }
        
        List<Sconto> result2 = scontoDAO.foundByTipologia(Sconto.tipologia.SPETTACOLO);
        boolean expResult2 = false;
        for(Sconto s: result2){
            if(s instanceof Sconto && s.getTipologia().equals(Sconto.tipologia.SPETTACOLO)){
                expResult2 = true;
            }
            assertEquals(expResult2, true);            
        }
        
        List<Sconto> result3 = scontoDAO.foundByTipologia(Sconto.tipologia.SESSO);
        boolean expResult3 = false;
        for(Sconto s: result3){
            if(s instanceof Sconto && s.getTipologia().equals(Sconto.tipologia.SESSO)){
                expResult3 = true;
            }
            assertEquals(expResult3, true);
        }
        
        List<Sconto> result4 = scontoDAO.foundByTipologia(Sconto.tipologia.GENERE);
        boolean expResult4 = false;
        for(Sconto s: result4){
            if(s instanceof Sconto && s.getTipologia().equals(Sconto.tipologia.GENERE)){
                expResult4 = true;
            }
            assertEquals(expResult4, true);
        }
        
        List<Sconto> result5 = scontoDAO.foundByTipologia(Sconto.tipologia.FILM);
        boolean expResult5 = false;
        for(Sconto s: result5){
            if(s instanceof Sconto && s.getTipologia().equals(Sconto.tipologia.FILM)){
                expResult5 = true;
            }
            assertEquals(expResult5, true);
        }
        
        List<Sconto> result6 = scontoDAO.foundByTipologia(Sconto.tipologia.ETA);
        boolean expResult6 = false;
        for(Sconto s: result6){
            if(s instanceof Sconto && s.getTipologia().equals(Sconto.tipologia.ETA)){
                expResult6 = true;
            }
            assertEquals(expResult6, true);
        }
        
        List<Sconto> result7 = scontoDAO.foundByTipologia(Sconto.tipologia.DATA);
        boolean expResult7 = false;
        for(Sconto s: result7){
            if(s instanceof Sconto && s.getTipologia().equals(Sconto.tipologia.DATA)){
                expResult7 = true;
            }
            assertEquals(expResult7, true);
        }
        
        List<Sconto> result8 = scontoDAO.foundByTipologia(Sconto.tipologia.ALTRO);
        boolean expResult8 = false;
        for(Sconto s: result8){
            if(s instanceof Sconto && s.getTipologia().equals(Sconto.tipologia.ALTRO)){
                expResult8 = true;
            }
            assertEquals(expResult8, true);
        }       
    }

    /**
     * Test del metodo updateSconto, della classe ScontoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateSconto() throws Exception {
        System.out.println("updateSconto");
        int newPerc = 30;
        mySconto.setPercentuale(newPerc);
        boolean expResult = true;
        boolean result = scontoDAO.updateSconto(mySconto);
        assertEquals(expResult, result);
        
    }

    /**
     * Test del metodo createSconto, della classe ScontoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateSconto() throws Exception {
        System.out.println("createSconto");
        scontoDAO.deleteSconto(idReale);
        boolean expResult = true;
        assertEquals(expResult, scontoDAO.createSconto(sconto));
        setIdReale();
    }

    /**
     * Test del metodo deleteSconto, della classe ScontoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteSconto() throws Exception {
        System.out.println("deleteSconto");
        boolean expResult = true;
        assertEquals(expResult, scontoDAO.deleteSconto(idReale));
        setMySconto();
        setIdReale();
    }

}
