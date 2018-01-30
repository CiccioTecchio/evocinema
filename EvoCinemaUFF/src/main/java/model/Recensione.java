
package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Recensione {
    
    private String emailUtente;
    private String nomeUtente;
    private Film film;
    private float valutazione;
    private String testo;
    private Calendar dataImmissione;

    public Recensione( String emailUtente, Film film, float valutazione, String testo, Calendar dataImmissione) {
        
        this.emailUtente= emailUtente;
        this.film= film;
        this.valutazione = valutazione;
        this.testo = testo;
        this.dataImmissione = dataImmissione;
    }

    public Recensione(){
        
    }
    
    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }
    
    public float getValutazione() {
        return valutazione;
    }

    public String getTesto() {
        return testo;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getEmailUtente() {
        return emailUtente;
    }

    public Film getFilm() {
        return film;
    }

    public Calendar getDataImmissione() {
        return dataImmissione;
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
        return getClass().getName()+"{" + "emailUtente=" + emailUtente + ", film=" + film + ", valutazione=" + valutazione + ", testo=" + testo + ", dataImmissione=" + dataImmissione + '}';
    }

    
}
