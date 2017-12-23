
package model;


import java.util.Calendar;

public class Acquisto extends Operazione{
    
    public Acquisto(int idOperazione, String email, int idSpettacolo, int postoColonna, int postoRiga, int sala, prenotato myPrenotato, acquistato myAcquistato, float prezzoFinale, Calendar data, int sconto) {
        super(idOperazione, email, idSpettacolo, postoColonna, postoRiga, sala, myPrenotato, myAcquistato, prezzoFinale, data, sconto);
    }
    
    public Acquisto(){}
}