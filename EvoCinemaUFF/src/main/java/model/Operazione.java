
package model;

import java.util.Calendar;


public abstract class Operazione {
    
    public enum prenotato{TRUE, FALSE};
    public enum acquistato{TRUE, FALSE};
    
    private int idOperazione;
    private String email;
    private int idSpettacolo;
    private int posto;
    private int offset;
    private Sala sala;
    private prenotato prenotato;
    private acquistato acquistato;
    private float prezzoFinale;
    private Calendar data;
    private Sconto sconto;
    
    

    public Operazione(int idOperazione, String email, int idSpettacolo, int posto, int offset, Sala sala, prenotato myPrenotato, acquistato myAcquistato, float prezzoFinale, Calendar data, Sconto sconto) {
        this.idOperazione = idOperazione;
        this.email= email;
        this.idSpettacolo= idSpettacolo;
        this.posto= posto;
        this.offset= offset;
        this.sala= sala;
        this.prenotato = myPrenotato;
        this.acquistato= myAcquistato;
        this.prezzoFinale= prezzoFinale;
        this.data = data;
        this.sconto = sconto;
    }
    
    public Operazione( String email, int idSpettacolo, int posto, int offset, Sala sala, prenotato myPrenotato, acquistato myAcquistato, float prezzoFinale, Calendar data, Sconto sconto) {
        
        this.email= email;
        this.idSpettacolo= idSpettacolo;
        this.posto= posto;
        this.offset= offset;
        this.sala= sala;
        this.prenotato = myPrenotato;
        this.acquistato= myAcquistato;
        this.prezzoFinale= prezzoFinale;
        this.data = data;
        this.sconto = sconto;
    }
    
    public Operazione(){}

    public int getIdOperazione() {
        return idOperazione;
    }

    public String getEmail() {
        return email;
    }

    public int getIdSpettacolo() {
        return idSpettacolo;
    }

    public int getPosto() {
        return posto;
    }

    public int getOffset() {
        return offset;
    }

    public Sala getSala() {
        return sala;
    }

    public prenotato getPrenotato() {
        return prenotato;
    }

    public acquistato getAcquistato() {
        return acquistato;
    }

    public float getPrezzoFinale() {
        return prezzoFinale;
    }

    public Calendar getData() {
        return data;
    }

    public Sconto getSconto() {
        return sconto;
    }

    public void setIdOperazione(int idOperazione) {
        this.idOperazione = idOperazione;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdSpettacolo(int idSpettacolo) {
        this.idSpettacolo = idSpettacolo;
    }

    public void setPosto(int posto) {
        this.posto = posto;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setPrenotato(prenotato prenotato) {
        this.prenotato = prenotato;
    }

    public void setAcquistato(acquistato acquistato) {
        this.acquistato = acquistato;
    }

    public void setPrezzoFinale(float prezzoFinale) {
        this.prezzoFinale = prezzoFinale;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public void setSconto(Sconto sconto) {
        this.sconto = sconto;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+"{" + "idOperazione=" + idOperazione + ", email=" + email + ", idSpettacolo=" + idSpettacolo + ", posto=" + posto + ", offset=" + offset + ", sala=" + sala + ", prenotato=" + prenotato + ", acquistato=" + acquistato + ", prezzoFinale=" + prezzoFinale + ", data=" + data + ", sconto=" + sconto + '}';
    }
    
}
