
package model;

import java.util.Calendar;
public class Gestore extends UtenteRegistrato{

    public Gestore(String email,String nomeUtente,String password,ruolo ruolo,String nome, String cognome, Calendar dataNascita, sesso sesso, String cellulare, String città, String indirizzo) {
        super(email, nomeUtente, password, ruolo, nome, cognome, dataNascita, sesso, cellulare, città, indirizzo);
    }

}
