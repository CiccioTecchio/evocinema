
package model;

import java.util.GregorianCalendar;

public class Recensione {
    private String idRecensione;
    private float valutazione;
    private String testo;
    private GregorianCalendar dataImmissione;

    public Recensione(String idRecensione, float valutazione, String testo, GregorianCalendar dataImmissione) {
        this.idRecensione = idRecensione;
        this.valutazione = valutazione;
        this.testo = testo;
        this.dataImmissione = dataImmissione;
    }

    public String getIdRecensione() {
        return idRecensione;
    }

    public float getValutazione() {
        return valutazione;
    }

    public String getTesto() {
        return testo;
    }

    public GregorianCalendar getDataImmissione() {
        return dataImmissione;
    }

    public void setIdRecensione(String idRecensione) {
        this.idRecensione = idRecensione;
    }

    public void setValutazione(float valutazione) {
        this.valutazione = valutazione;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public void setDataImmissione(GregorianCalendar dataImmissione) {
        this.dataImmissione = dataImmissione;
    }
    
    
}
