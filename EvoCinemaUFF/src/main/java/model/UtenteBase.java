
package model;

import java.util.Calendar;

public class UtenteBase  extends UtenteRegistrato{
   
    private float saldo;

    public UtenteBase(String idUtenteBase, float saldo, String nomeUtente, String password, String nome, String cognome, String email, String cellulare, String indirizzo, Calendar dataNascita,ruolo ruolo, sesso sesso) {
        super(nomeUtente, password, nome, cognome, email, cellulare, indirizzo, dataNascita, ruolo,sesso);
       
        this.saldo = saldo;
    }
    
    public UtenteBase(){
        
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }
    
    @Override
    public String toString(){
        return(super.toString()+", saldo corrente="+String.format("%1$.2f",saldo));
    }
}
