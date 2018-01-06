
package model;

public class FilmConValutazioneMedia {
    Film film;
    int valutazioneMedia;
    
    public FilmConValutazioneMedia(Film film, int valutazione){
        this.film=film;
        valutazioneMedia=valutazione;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getValutazioneMedia() {
        return valutazioneMedia;
    }

    public void setValutazioneMedia(int valutazioneMedia) {
        this.valutazioneMedia = valutazioneMedia;
    }
    
    @Override
    public String toString() {
        return getClass().getName()+"{" + film.toString() + valutazioneMedia + "}";
    }
    
}
