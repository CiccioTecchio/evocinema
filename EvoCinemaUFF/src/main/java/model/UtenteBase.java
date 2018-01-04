
package model;

import java.util.Calendar;

public class UtenteBase  extends UtenteRegistrato{
   
    private float saldo;

    public UtenteBase(Float saldo, String email,String nomeUtente,String password,ruolo ruolo,String nome, String cognome, Calendar dataNascita, sesso sesso, String cellulare, String città, String indirizzo) {
        super(email, nomeUtente, password, ruolo, nome, cognome, dataNascita, sesso, cellulare, città, indirizzo);
       
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
