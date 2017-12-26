
package model;

public class Posto {
   public enum stato{ libero, occupato};
    
   private int riga;
   private int colonna;
   private Sala sala;
   private stato stato;
   

    public Posto(int riga, int colonna, Sala sala, stato myStato) {
        this.riga = riga;
        this.colonna = colonna;
        this.sala = sala;
        stato = myStato;
    }
    
    public Posto(){}

    public int getRiga() {
        return riga;
    }

    public int getColonna() {
        return colonna;
    }

    public Sala getSala() {
        return sala;
    }

    public stato getStato() {
        return stato;
    }

    public void setRiga(int riga) {
        this.riga = riga;
    }

    public void setColonna(int colonna) {
        this.colonna = colonna;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setStato(stato stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+"{" + "riga=" + riga + ", colonna=" + colonna + ", sala=" + sala + ", stato=" + stato + '}';
    }

   

    
}
