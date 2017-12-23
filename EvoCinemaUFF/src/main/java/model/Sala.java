
package model;

public class Sala {
    private int idSala;
    private int numeroPosti;
    private String configPosti;

    public Sala(int idSala, int numeroPosti, String configPosti) {
        this.idSala = idSala;
        this.numeroPosti = numeroPosti;
        this.configPosti = configPosti;
    }
    
    public Sala(){
    
    }

    public int getIdSala() {
        return idSala;
    }

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public String getConfigPosti() {
        return configPosti;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    public void setConfigPosti(String configPosti) {
        this.configPosti = configPosti;
    }

    @Override
    public String toString() {
        return getClass().getName()+"{" + "idSala=" + idSala + ", numeroPosti=" + numeroPosti + ", configPosti=" + configPosti + '}';
    }
    
    
    
}
