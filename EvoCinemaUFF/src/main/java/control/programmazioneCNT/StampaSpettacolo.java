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
 *
 * @author luca
 */
public class StampaSpettacolo {
    public static void prepare(Spettacolo s, Film f){
        str =  "<div class=\"row\">"
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
                        +    "<form method=\"POST\" action=\"VisualizzazioneDettagliSpettacolo.jsp\" data-label class=\"col\"><input name=\"idSpettacolo\" value=\""+ s.getIdSpettacolo() +"\" hidden><button class=\"btn btn-primary\" type=\"submit\">"+ sdf.format(s.getOraInizio().getTime()) +"</button></form>";

    }
    
    public static void aggiungiSpettacolo(Spettacolo s){
        str = str + "<form method=\"POST\" action=\"VisualizzazioneDettagliSpettacolo.jsp\" data-label class=\"col\"><input name=\"idSpettacolo\" value=\""+ s.getIdSpettacolo() +"\" hidden><button class=\"btn btn-primary\" type=\"submit\">"+ sdf.format(s.getOraInizio().getTime())  +"</button></form>";
    }
    
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