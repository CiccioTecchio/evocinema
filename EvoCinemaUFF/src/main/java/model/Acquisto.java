
package model;


import java.util.Calendar;

public class Acquisto extends Operazione{
    
    public Acquisto(int idOperazione, String email, int idSpettacolo, int posto, int offset, Sala sala, prenotato myPrenotato, acquistato myAcquistato, float prezzoFinale, Calendar data, Sconto sconto) {
        super(idOperazione, email, idSpettacolo, posto, offset, sala, myPrenotato, myAcquistato, prezzoFinale, data, sconto);
    }
    
    public Acquisto( String email, int idSpettacolo, int posto, int offset, Sala sala, prenotato myPrenotato, acquistato myAcquistato, float prezzoFinale, Calendar data, Sconto sconto) {
        super( email, idSpettacolo, posto, offset, sala, myPrenotato, myAcquistato, prezzoFinale, data, sconto);
    }
    
    public Acquisto(){}
}