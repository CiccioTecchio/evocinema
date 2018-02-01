/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Date;
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
public class SpettacoloTest {
    
    public SpettacoloTest() {
    }
    
    @Test
    public void getterSetterTest() {
        Spettacolo u = new Spettacolo();
        assertNotNull(u);
        u.setTitolo(TITOLO);
        assertEquals(TITOLO, u.getTitolo());
        Calendar dataInizio = Calendar.getInstance();
        dataInizio.set(2018, 1, 20);
        u.setDataInizio(dataInizio);
        Calendar dataFine = Calendar.getInstance();
        dataFine.set(2018, 2, 5);
        u.setDataFine(dataFine);
        u.setPrezzo(PREZZO);
        assertEquals(PREZZO, u.getPrezzo(), 0);
        Calendar oraInizio = Calendar.getInstance();
        oraInizio.set(2018, 1, 20, 10, 0);
        u.setOraInizio(oraInizio);
        Calendar oraFine = Calendar.getInstance();
        oraFine.set(2000, 1, 20, 12, 0);
        u.setOraFine(oraFine);
    }
    
    
    private int idSpettacolo;
    private int idSala;
    private Integer idFilm;
    private String titolo;
    private Calendar dataInizio;
    private Calendar dataFine;
    private float prezzo;
    private Calendar oraInizio;
    private Calendar oraFine;
    
    private static final String TITOLO = "titoloProva";
    private static final float PREZZO = 5.5f;
        
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
