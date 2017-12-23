
package model;

public class Posto {
   private int riga;
   private int colonna;
   private int sala;
   private enum stato{ libero, occupato};

    public Posto(int riga, int colona, int sala, stato myStato, stato stato) {
        this.riga = riga;
        this.colonna = colonna;
        this.sala = sala;
        stato = myStato;
    }

    public int getRiga() {
        return riga;
    }

    public int getColonna() {
        return colonna;
    }

    public int getSala() {
        return sala;
    }

    public void setRiga(int riga) {
        this.riga = riga;
    }

    public void setColonna(int colonna) {
        this.colonna = colonna;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+"{" + "riga=" + riga + ", colonna=" + colonna + ", sala=" + sala + '}';
    }

    
}
