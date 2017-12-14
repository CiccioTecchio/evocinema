
package model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Acquisto {
    private String idAcquisto;
    private Sconto sconto;
    private GregorianCalendar data;

    public Acquisto(String idAcquisto, Sconto sconto, GregorianCalendar data) {
        this.idAcquisto = idAcquisto;
        this.sconto = sconto;
        this.data = data;
    }

    public String getIdAcquisto() {
        return idAcquisto;
    }

    public Sconto getSconto() {
        return sconto;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public void setIdAcquisto(String idAcquisto) {
        this.idAcquisto = idAcquisto;
    }

    public void setSconto(Sconto sconto) {
        this.sconto = sconto;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        
        return(getClass().getName()+"{"+"id acquisto="+idAcquisto+", "+sconto.toString()+", data="+fmt.format(data.getTime()));
    } 
}
