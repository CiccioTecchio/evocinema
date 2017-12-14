
package model;

import java.util.GregorianCalendar;

public class Spettacolo {
    private String idSpettacolo;
    private GregorianCalendar orario;
    private GregorianCalendar dataInizio;
    private GregorianCalendar dataFine;
    private float prezzo;
    private Sala sala;

    public Spettacolo(String idSpettacolo, GregorianCalendar orario, GregorianCalendar dataInizio, GregorianCalendar dataFine, float prezzo, Sala sala) {
        this.idSpettacolo = idSpettacolo;
        this.orario = orario;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.prezzo = prezzo;
        this.sala=sala;
    }

    public String getIdSpettacolo() {
        return idSpettacolo;
    }

    public GregorianCalendar getOrario() {
        return orario;
    }

    public GregorianCalendar getDataInizio() {
        return dataInizio;
    }

    public GregorianCalendar getDataFine() {
        return dataFine;
    }

    public float getPrezzo() {
        return prezzo;
    }
    
    public Sala getSala(){
        return sala;
    }

    public void setIdSpettacolo(String idSpettacolo) {
        this.idSpettacolo = idSpettacolo;
    }

    public void setOrario(GregorianCalendar orario) {
        this.orario = orario;
    }

    public void setDataInizio(GregorianCalendar dataInizio) {
        this.dataInizio = dataInizio;
    }

    public void setDataFine(GregorianCalendar dataFine) {
        this.dataFine = dataFine;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    
    public void setSala(Sala sala){
        this.sala = sala;
    }

    @Override
    public String toString() {
        return (this.getClass().getName()+"{" + "idSpettacolo=" + idSpettacolo + ", orario=" + orario + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine + ", prezzo=" + prezzo + ", sala=" + sala + '}');
    }
            
    
    
}
