/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.model;

import java.util.GregorianCalendar;

/**
 *
 * @author francescodefeo
 */
public class Gestore extends UtenteRegistrato{

    public Gestore(String nomeUtente, String password, String nome, String cognome, String email, String cellulare, String indirizzo, GregorianCalendar dataNascita, String sesso) {
        super(nomeUtente, password, nome, cognome, email, cellulare, indirizzo, dataNascita, sesso);
    }
}
