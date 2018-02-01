
package model;

import java.sql.Time;
import java.util.Calendar;

public class FilmConValutazioneMedia extends Film {
    float valutazioneMedia;
    
    public FilmConValutazioneMedia( int idFilm, tipo myTipo, String titolo, String locandina, String regia, String cast, String genere, Time durata, Calendar dataUscita, vistoCensura myVistoCensura, String distribuzione, String produzione, String trama, String trailer, float valutazione){
        
        super( idFilm , myTipo , titolo , locandina , regia , cast , genere , durata , dataUscita , myVistoCensura , distribuzione , produzione , trama , trailer  ); 
        
        this.valutazioneMedia=valutazione;
    }
    
    public FilmConValutazioneMedia( tipo myTipo, String titolo, String locandina, String regia, String cast, String genere, Time durata, Calendar dataUscita, vistoCensura myVistoCensura, String distribuzione, String produzione, String trama, String trailer, float valutazione){
        
        super( myTipo , titolo , locandina , regia , cast , genere , durata , dataUscita , myVistoCensura , distribuzione , produzione , trama , trailer  ); 
        
        this.valutazioneMedia=valutazione;
    }

    public FilmConValutazioneMedia() {
    }


    public float getValutazioneMedia() {
        return valutazioneMedia;
    }

    public void setValutazioneMedia(float valutazioneMedia) {
        this.valutazioneMedia = valutazioneMedia;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+"{" + "valutazioneMedia=" + valutazioneMedia + '}';
    }
    
    
    
}
