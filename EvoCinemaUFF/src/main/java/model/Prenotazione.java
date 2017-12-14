
package model;


public class Prenotazione {
    private String idPrenotazione;
    private float caparra;

    public Prenotazione(String idPrenotazione, float caparra) {
        this.idPrenotazione = idPrenotazione;
        this.caparra = caparra;
    }

    public String getIdPrenotazione() {
        return idPrenotazione;
    }

    public float getCaparra() {
        return caparra;
    }

    public void setIdPrenotazione(String idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public void setCaparra(float caparra) {
        this.caparra = caparra;
    }
    
    @Override
    public String toString(){
        return(getClass().getName()+"{id prenotazione="+idPrenotazione+", caparra="+String.format("%1$.2f",caparra));
    }
    
}
