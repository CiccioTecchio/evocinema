/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.programmazioneCNT;

import java.text.SimpleDateFormat;
import model.Film;
import model.Spettacolo;

/**
 *Una classe per la generazione dell'html necessario a rappresentare spettacoli e film in programmazione.
 * @author luca
 */
public class StampaSpettacolo {
    /**
     * Setta la variabile statica "str" assegnandole l'html necessario a raffigurare i dettagli del film e il bottone
     * relativo al primo spettacolo (in ordine di orario) che lo rappresenta.
     * @param s lo spettacolo
     * @param f il film
     * @param offsetGiorno la differenza in giorni tra il primo giorno di rappresentazione dello spettacolo 
     * e quello per cui si sta stampando
     */
    public static void prepare(Spettacolo s, Film f, int offsetGiorno){
        str =  "<div id='"+f.getIdFilm()+"'class=\"row mt-2\">"
                        + "<div class=\"col-3\">"
                        +  "<img class=\"img-fluid\" src=\""+ f.getLocandina() +"\" />\n"
                        + "</div>"
                        + "<div data-table class=\"col\">"
                        + "<div class=\"row\">"
                        +   "<h5>" + s.getTitolo() +"</h5>"
                        + "</div>"
                        + "<div class=\"row\">"
                        +   "<div data-label class=\"col\">Genere:</div><div class=\"col\">" + f.getGenere() + "</div>"
                        + "</div>"
                        + "<div class=\"row\">"
                        +   "<div data-label class=\"col\">Regia:</div><div class=\"col\">" + f.getRegia() + "</div>"
                        + "</div>"
                        + "<div class=\"row\">"
                        +   "<div data-label class=\"col\">Cast:</div><div class=\"col\">" + f.getCast() + "</div>"
                        + "</div>"
                        + "<div class=\"row\">"
                        +   "<div data-label class=\"col\">Durata:</div><div class=\"col\">" + f.getDurata()+ "</div>"
                        + "</div>"
                        + "<div class=\"row\">"
                        +   "<div data-label class=\"col\">Visto Censura:</div><div class=\"col\">" + f.getVistoCensura()+ "</div>"
                        + "</div>"
                        + "<div class=\"row\">"
                        +   "<div data-label class=\"col\">Spettacoli:<br />&nbsp;</div>"
                        + "</div>"
                        + "<div id=\"orari\" class=\"row\">"
                        +    "<form method=\"POST\" action=\"VisualizzazioneDettagliSpettacolo.jsp\" data-label class=\"col\"><input name=\"idSpettacolo\" value=\""+ s.getIdSpettacolo() +"\" type=\"hidden\"><input type=\"hidden\" name=\"offset-giorno\" value=\"" + offsetGiorno + "\" /><button class=\"btn btn-primary\" type=\"submit\">"+ sdf.format(s.getOraInizio().getTime()) +"</button></form>";

    }
    
    /**
     * Concatena alla stringa "str" l'html necessario a rappresentare il bottone associato al successivo spettacolo
     * che rappresenta il film.
     * @param s lo spettacolo per cui aggiungere il bottone
     * @param offsetGiorno la differenza in giorni tra il primo giorno di rappresentazione dello spettacolo 
     * e quello per cui si sta stampando
     */
    public static void aggiungiSpettacolo(Spettacolo s, int offsetGiorno){
        str = str + "<form method=\"POST\" action=\"VisualizzazioneDettagliSpettacolo.jsp\" data-label class=\"col\"><input name=\"idSpettacolo\" value=\""+ s.getIdSpettacolo() +"\" type=\"hidden\"><input type=\"hidden\" name=\"offset-giorno\" value=\"" + offsetGiorno + "\" /><button class=\"btn btn-primary\" type=\"submit\">"+ sdf.format(s.getOraInizio().getTime())  +"</button></form>";
    }
    
    /**
     * Concatena ad "str" la chiusura di tutti i tag aperti nei metodi precedenti e restituisce il risultato.
     * @return una stringa costituita dall'html che formatta i dati di un film e degli spettacolo che lo rappresentano.
     */
    public static String getResult(){
        return str+close;
    } 
    
    static String str;
    static String close = "</div></div></div><hr/>";
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
}

/* pure html code
                  <div class="row">
                    <div class="col-3">
                        <img class="img-fluid" src="https://images-na.ssl-images-amazon.com/images/M/MV5BZGE3ZjZiMTgtYmY1ZS00YTBjLWI2Y2MtOTgxNGY4ZDg4ZTE3XkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_SY1000_CR0,0,772,1000_AL_.jpg" />
                    </div>
                    <div data-table class="col">
                        <div class="row">
                            <h5>Title</h5>
                        </div>
                        <div class="row">
                            <div data-label class="col">Genere:</div><div class="col">Bim</div>
                        </div>
                        <div class="row">
                            <div data-label class="col">Regia:</div><div class="col">Bum</div>
                        </div>
                        <div class="row">
                            <div data-label class="col">Cast:</div><div class="col">Ba</div>
                        </div>
                        <div class="row">
                            <div data-label class="col">Durata:</div><div class="col">TXTXT</div>
                        </div>
                        <div class="row">
                            <div data-label class="col">Visto Censura</div><div class="col">TXTXT</div>
                        </div>
                        <div class="row">
                            <div data-label class="col">Spettacoli:<br />&nbsp;</div>
                        </div>
                        <div id="orari" class="row">
                            <form method="GET" data-label class="col"><input name="idSpettacolo" value="3" hidden><button class="btn btn-primary" type="submit">12:30</button></form>
                        </div>
                    </div>
                </div>

*/