
package model;

import java.util.Date;
import java.util.GregorianCalendar;

public class Acquisto {
    private String idAcquisto;
    private Float sconto;
    private GregorianCalendar data;

    public Acquisto(String idAcquisto, Float sconto, GregorianCalendar data) {
        this.idAcquisto = idAcquisto;
        this.sconto = sconto;
        this.data = data;
    }

    public String getIdAcquisto() {
        return idAcquisto;
    }

    public Float getSconto() {
        return sconto;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public void setIdAcquisto(String idAcquisto) {
        this.idAcquisto = idAcquisto;
    }

    public void setSconto(Float sconto) {
        this.sconto = sconto;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }
    
     
}
