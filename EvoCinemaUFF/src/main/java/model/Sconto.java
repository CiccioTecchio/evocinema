
package model;

import java.util.GregorianCalendar;

public class Sconto {
    private String idSconto;
    private String nome;
    private GregorianCalendar dataInizio;
    private GregorianCalendar dataFine;
    private boolean attivazione;
    private GregorianCalendar dataAttivazione;
    private GregorianCalendar dataDisattivazione;
    private String politica;

    public Sconto(String idSconto, String nome, GregorianCalendar dataInizio, GregorianCalendar dataFine, boolean attivazione, GregorianCalendar dataAttivazione, GregorianCalendar dataDisattivazione, String politica) {
        this.idSconto = idSconto;
        this.nome = nome;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.attivazione = attivazione;
        this.dataAttivazione = dataAttivazione;
        this.dataDisattivazione = dataDisattivazione;
        this.politica = politica;
    }

    public String getIdSconto() {
        return idSconto;
    }

    public String getNome() {
        return nome;
    }

    public GregorianCalendar getDataInizio() {
        return dataInizio;
    }

    public GregorianCalendar getDataFine() {
        return dataFine;
    }

    public boolean isAttivazione() {
        return attivazione;
    }

    public GregorianCalendar getDataAttivazione() {
        return dataAttivazione;
    }

    public GregorianCalendar getDataDisattivazione() {
        return dataDisattivazione;
    }

    public String getPolitica() {
        return politica;
    }

    public void setIdSconto(String idSconto) {
        this.idSconto = idSconto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataInizio(GregorianCalendar dataInizio) {
        this.dataInizio = dataInizio;
    }

    public void setDataFine(GregorianCalendar dataFine) {
        this.dataFine = dataFine;
    }

    public void setAttivazione(boolean attivazione) {
        this.attivazione = attivazione;
    }

    public void setDataAttivazione(GregorianCalendar dataAttivazione) {
        this.dataAttivazione = dataAttivazione;
    }

    public void setDataDisattivazione(GregorianCalendar dataDisattivazione) {
        this.dataDisattivazione = dataDisattivazione;
    }

    public void setPolitica(String politica) {
        this.politica = politica;
    }
    
}
