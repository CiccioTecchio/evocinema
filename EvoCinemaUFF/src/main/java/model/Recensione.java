
package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Recensione {
    private int idRecensione;
    private String emailUtente;
    private int idFilm;
    private float valutazione;
    private String testo;
    private Calendar dataImmissione;

    public Recensione(int idRecensione,String emailUtente, int idOpera, float valutazione, String testo, Calendar dataImmissione) {
        this.idRecensione = idRecensione;
        this.emailUtente= emailUtente;
        this.idFilm= idOpera;
        this.valutazione = valutazione;
        this.testo = testo;
        this.dataImmissione = dataImmissione;
    }

    public Recensione(){
        
    }
    
    public int getIdRecensione() {
        return idRecensione;
    }

    public float getValutazione() {
        return valutazione;
    }

    public String getTesto() {
        return testo;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getEmailUtente() {
        return emailUtente;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public Calendar getDataImmissione() {
        return dataImmissione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public void setValutazione(float valutazione) {
        this.valutazione = valutazione;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    public void setDataImmissione(Calendar dataImmissione) {
        this.dataImmissione = dataImmissione;
    }

    @Override
    public String toString() {
        return getClass().getName()+"{" + "idRecensione=" + idRecensione + ", emailUtente=" + emailUtente + ", idFilm=" + idFilm + ", valutazione=" + valutazione + ", testo=" + testo + ", dataImmissione=" + dataImmissione + '}';
    }

    
}
