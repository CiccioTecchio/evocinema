
package model;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Film {
    public enum tipo{film, teatro, altro};
    public enum vistoCensura{ T, VM14, VM16, VM18};
    
    private int idFilm;
    private tipo tipo;
    private String titolo;
    private String locandina;
    private String regia;
    private String cast;
    private String genere;
    private Time durata;
    private Calendar dataUscita;
    private vistoCensura vistoCensura;
    private String distribuzione;
    private String produzione;
    private String trama;
    private String trailer;
    
    
    public Film(){}
    
    public Film(int idFilm, tipo myTipo, String titolo, String locandina, String regia, String cast, String genere, Time durata, Calendar dataUscita, vistoCensura myVistoCensura, String distribuzione, String produzione, String trama, String trailer) {
        this.idFilm = idFilm;
        tipo = myTipo;
        this.titolo= titolo;
        this.locandina = locandina;
        this.regia=regia;
        this.cast=cast;
        this.genere=genere;
        this.durata=durata;
        this.dataUscita=dataUscita;
        vistoCensura= myVistoCensura;
        this.distribuzione=distribuzione;
        this.produzione=produzione;
        this.trama=trama;
        this.trailer=trailer;
        
    }
    
    public Film( tipo myTipo, String titolo, String locandina, String regia, String cast, String genere, Time durata, Calendar dataUscita, vistoCensura myVistoCensura, String distribuzione, String produzione, String trama, String trailer) {
        
     
        tipo = myTipo;
        this.titolo= titolo;
        this.locandina = locandina;
        this.regia=regia;
        this.cast=cast;
        this.genere=genere;
        this.durata=durata;
        this.dataUscita=dataUscita;
        vistoCensura= myVistoCensura;
        this.distribuzione=distribuzione;
        this.produzione=produzione;
        this.trama=trama;
        this.trailer=trailer;
        
    }

    public int getIdFilm() {
        return idFilm;
    }

    public tipo getTipo() {
        return tipo;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getLocandina() {
        return locandina;
    }

    public String getRegia() {
        return regia;
    }

    public String getCast() {
        return cast;
    }

    public String getGenere() {
        return genere;
    }

    public Time getDurata() {
        return durata;
    }

    public Calendar getDataUscita() {
        return dataUscita;
    }

    public vistoCensura getVistoCensura() {
        return vistoCensura;
    }

    public String getDistribuzione() {
        return distribuzione;
    }

    public String getProduzione() {
        return produzione;
    }

    public String getTrama() {
        return trama;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public void setTipo(tipo tipo) {
        this.tipo = tipo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setLocandina(String locandina) {
        this.locandina = locandina;
    }

    public void setRegia(String regia) {
        this.regia = regia;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public void setDurata(Time durata) {
        this.durata = durata;
    }

    public void setDataUscita(Calendar dataUscita) {
        this.dataUscita = dataUscita;
    }

    public void setVistoCensura(vistoCensura vistoCensura) {
        this.vistoCensura = vistoCensura;
    }

    public void setDistribuzione(String distribuzione) {
        this.distribuzione = distribuzione;
    }

    public void setProduzione(String produzione) {
        this.produzione = produzione;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+"{" + "idFilm=" + idFilm + ", tipo=" + tipo + ", titolo=" + titolo + ", locandina=" + locandina + ", regia=" + regia + ", cast=" + cast + ", genere=" + genere + ", durata=" + durata + ", dataUscita=" + dataUscita + ", vistoCensura=" + vistoCensura + ", distribuzione=" + distribuzione + ", produzione=" + produzione + ", trama=" + trama + ", trailer=" + trailer + '}';
    }
    
    


    
    
}
