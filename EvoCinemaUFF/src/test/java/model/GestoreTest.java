/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test della classe model Gestore
 *
 * @author Giuseppe
 */
public class GestoreTest {

    public GestoreTest() {
    }

    /*
    * Test dei metodi GET e SET
     */
    @Test
    public void getterSetterTest() {
        Gestore u = new Gestore();
        assertNotNull(u);
        u.setEmail(EMAIL);
        assertEquals(EMAIL, u.getEmail());
        u.setNomeUtente(NOMEUTENTE);
        assertEquals(NOMEUTENTE, u.getNomeUtente());
        u.setPassword(PASSWORD);
        assertEquals(PASSWORD, u.getPassword());
        u.setNome(NOME);
        assertEquals(NOME, u.getNome());
        u.setCognome(COGNOME);
        assertEquals(COGNOME, u.getCognome());
        Calendar dataNascita = Calendar.getInstance();
        dataNascita.set(2000, 1, 1);
        u.setDataNascita(dataNascita);
        assertEquals(dataNascita, u.getDataNascita());
        u.setRuolo(UtenteRegistrato.ruolo.UTENTE);
        assertEquals(UtenteRegistrato.ruolo.UTENTE, u.getRuolo());
        u.setSesso(UtenteRegistrato.sesso.M);
        assertEquals(UtenteRegistrato.sesso.M, u.getSesso());
        u.setCellulare(CELLULARE);
        assertEquals(CELLULARE, u.getCellulare());
        u.setCittà(CITTA);
        u.setIndirizzo(INDIRIZZO);
        assertEquals(INDIRIZZO, u.getIndirizzo());

    }

    /*
    * Varianbili utilizzate per il test
     */
    private static final String EMAIL = "email@email.it";
    private static final String NOMEUTENTE = "nomeUtente";
    private static final String PASSWORD = "password";
    private static final String NOME = "nome";
    private static final String COGNOME = "cognome";
    private static final String CELLULARE = "1234567890";
    private static final String CITTA = "citta";
    private static final String INDIRIZZO = "via Roma 7";

}
