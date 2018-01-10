
package model;


import java.util.Calendar;

public class Spettacolo {
    private int idSpettacolo;
    private int idSala;
    private int idFilm;
    private Calendar dataInizio;
    private Calendar dataFine;
    private float prezzo;
    private Calendar oraInizio;
    private Calendar oraFine;

    public Spettacolo(int sala, int idFilm, Calendar dataInizio, Calendar dataFine, float prezzo, Calendar oraInizio, Calendar oraFine) {
        this.idSala= sala;
        this.idFilm= idFilm;
        this.dataInizio= dataInizio;
        this.dataFine= dataFine;
        this.prezzo= prezzo;
        this.oraInizio= oraInizio;
        this.oraFine= oraFine;
    }       
    
    public Spettacolo(){
        
    }

    public int getIdSpettacolo() {
        return idSpettacolo;
    }

    public int getIdSala() {
        return idSala;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public Calendar getDataInizio() {
        return dataInizio;
    }

    public Calendar getDataFine() {
        return dataFine;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public Calendar getOraInizio() {
        return oraInizio;
    }

    public Calendar getOraFine() {
        return oraFine;
    }

    public void setIdSpettacolo(int idSpettacolo) {
        this.idSpettacolo = idSpettacolo;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public void setDataInizio(Calendar dataInizio) {
        this.dataInizio = dataInizio;
    }

    public void setDataFine(Calendar dataFine) {
        this.dataFine = dataFine;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public void setOraInizio(Calendar oraInizio) {
        this.oraInizio = oraInizio;
    }

    public void setOraFine(Calendar oraFine) {
        this.oraFine = oraFine;
    }

    @Override
    public String toString() {
        return getClass().getName()+"{" + "idSpettacolo=" + idSpettacolo + ", idSala=" + idSala + ", idFilm=" + idFilm + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine + ", prezzo=" + prezzo + ", oraInizio=" + oraInizio + ", oraFine=" + oraFine + '}';
    }
}
