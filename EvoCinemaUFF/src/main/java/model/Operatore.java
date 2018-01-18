
package model;

import java.util.Calendar;

public class Operatore extends UtenteRegistrato {
    
    public Operatore(){}
    
    public Operatore(String email,String nomeUtente,String password,ruolo ruolo,String nome, String cognome, Calendar dataNascita, sesso sesso, String cellulare, String città, String indirizzo) {
        super(email, nomeUtente, password, ruolo, nome, cognome, dataNascita, sesso, cellulare, città, indirizzo);
    }
  
}
