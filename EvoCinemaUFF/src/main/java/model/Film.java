
package model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Film {
    private String idFilm;
    private String titolo;
    private String descrizione;
    private String genere;
    private GregorianCalendar annoUscita;
    private String locandina;

    public Film(String idFilm, String titolo, String descrizione, String genere, GregorianCalendar annoUscita, String locandina) {
        this.idFilm = idFilm;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.genere = genere;
        this.annoUscita = annoUscita;
        this.locandina = locandina;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getGenere() {
        return genere;
    }

    public GregorianCalendar getAnnoUscita() {
        return annoUscita;
    }

    public String getLocandina() {
        return locandina;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public void setAnnoUscita(GregorianCalendar annoUscita) {
        this.annoUscita = annoUscita;
    }

    public void setLocandina(String locandina) {
        this.locandina = locandina;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy");
        
        return(getClass().getName()+"{"+"id film="+idFilm+", titolo="+titolo+", descrizione="+descrizione+", genere="+genere+", anno uscita="+fmt.format(annoUscita.getTime())+", locandina="+locandina);
    } 
    
}
