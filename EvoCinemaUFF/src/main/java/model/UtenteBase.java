
package model;

import java.util.GregorianCalendar;

public class UtenteBase  extends UtenteRegistrato{
   
    private float saldo;

    public UtenteBase(String idUtenteBase, float saldo, String nomeUtente, String password, String nome, String cognome, String email, String cellulare, String indirizzo, GregorianCalendar dataNascita, String sesso) {
        super(nomeUtente, password, nome, cognome, email, cellulare, indirizzo, dataNascita, sesso);
       
        this.saldo = saldo;
    }


    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }
    
    @Override
    public String toString(){
        return(super.toString()+"; Saldo corrente - "+saldo);
    }
}
