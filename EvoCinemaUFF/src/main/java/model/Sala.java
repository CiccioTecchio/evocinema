
package model;

public class Sala {
    private String sala;
    private int numeroPosti;
    private String statoSala;

    public Sala(String sala, int numeroPosti, String statoSala) {
        this.sala = sala;
        this.numeroPosti = numeroPosti;
        this.statoSala = statoSala;
    }

    public String getSala() {
        return sala;
    }

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public String getStatoSala() {
        return statoSala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    public void setStatoSala(String statoSala) {
        this.statoSala = statoSala;
    }
    
    
}
