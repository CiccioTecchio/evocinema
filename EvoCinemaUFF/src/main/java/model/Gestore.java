
package model;

import java.util.Calendar;
public class Gestore extends UtenteRegistrato{

    public Gestore(String nomeUtente, String password, String nome, String cognome, String email, String cellulare, String indirizzo, Calendar dataNascita, ruolo ruolo, sesso sesso) {
        super(nomeUtente, password, nome, cognome, email, cellulare, indirizzo, dataNascita, ruolo, sesso);
    }

}
