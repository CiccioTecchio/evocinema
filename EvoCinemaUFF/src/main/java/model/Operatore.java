
package model;

import java.util.GregorianCalendar;

public class Operatore extends UtenteRegistrato {
    
    public Operatore(String nomeUtente, String password, String nome, String cognome, String email, String cellulare, String indirizzo, GregorianCalendar dataNascita, String sesso) {
        super(nomeUtente, password, nome, cognome, email, cellulare, indirizzo, dataNascita, sesso);
    }
  
}
