
package model;

import java.util.Calendar;


public abstract class Operazione {
    
    public enum prenotato{TRUE, FALSE};
    public enum acquistato{TRUE, FALSE};
    
    private int idOperazione;
    private String email;
    private int idSpettacolo;
    private int postoColonna;
    private int postoRiga;
    private Sala sala;
    private prenotato prenotato;
    private acquistato acquistato;
    private float prezzoFinale;
    private Calendar data;
    private Sconto sconto;
    
    

    public Operazione(int idOperazione, String email, int idSpettacolo, int postoColonna, int postoRiga, Sala sala, prenotato myPrenotato, acquistato myAcquistato, float prezzoFinale, Calendar data, Sconto sconto) {
        this.idOperazione = idOperazione;
        this.email= email;
        this.idSpettacolo= idSpettacolo;
        this.postoColonna= postoColonna;
        this.postoRiga= postoRiga;
        this.sala= sala;
        this.prenotato = myPrenotato;
        this.acquistato= myAcquistato;
        this.prezzoFinale= prezzoFinale;
        this.data = data;
        this.sconto = sconto;
    }
    
    public Operazione( String email, int idSpettacolo, int postoColonna, int postoRiga, Sala sala, prenotato myPrenotato, acquistato myAcquistato, float prezzoFinale, Calendar data, Sconto sconto) {
        
        this.email= email;
        this.idSpettacolo= idSpettacolo;
        this.postoColonna= postoColonna;
        this.postoRiga= postoRiga;
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

    public int getPostoColonna() {
        return postoColonna;
    }

    public int getPostoRiga() {
        return postoRiga;
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

    public void setIdOperazione(int Operazione) {
        this.idOperazione = idOperazione;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdSpettacolo(int idSpettacolo) {
        this.idSpettacolo = idSpettacolo;
    }

    public void setPostoColonna(int postoColonna) {
        this.postoColonna = postoColonna;
    }

    public void setPostoRiga(int postoRiga) {
        this.postoRiga = postoRiga;
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
        return this.getClass().getName()+"{" + "idOperazione=" + idOperazione + ", email=" + email + ", idSpettacolo=" + idSpettacolo + ", postoColonna=" + postoColonna + ", postoRiga=" + postoRiga + ", sala=" + sala + ", prenotato=" + prenotato + ", acquistato=" + acquistato + ", prezzoFinale=" + prezzoFinale + ", data=" + data + ", sconto=" + sconto + '}';
    }
    
}
