<%-- 
    Document   : VisualizzazioneDettagliSpettacolo
    Created on : 11-gen-2018, 11.17.16
    Author     : luca
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Film"%>
<%@page import="model.UtenteRegistrato"%>
<%@page import="control.programmazioneCNT.RappresentazioneSala"%>
<%@page import="control.programmazioneCNT.VisualizzazioneDettagliSpettacoloCNT"%>
<%@page import="model.Spettacolo"%>
<%@page import="model.Sala"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="VisualizzazioneDettagliSpettacoloCNT" />
<jsp:include page="Header.jsp" />

<%  
    Spettacolo spettacolo = (Spettacolo) request.getAttribute("spettacolo");
    Film film = (Film) request.getAttribute("film");
    Sala sala = (Sala) request.getAttribute("sala");
    int offset = (int) request.getAttribute("offset");
    RappresentazioneSala rs = new RappresentazioneSala(spettacolo, sala, offset);
    int endRiga = rs.getEndRiga();
    int endColonna = rs.getEndColonna();
    char[][] matSala = rs.getMatSala();
    char[] matricePostiSpettacolo = rs.getMatricePostiSpettacolo();
    UtenteRegistrato utente = (UtenteRegistrato) request.getSession().getAttribute("utente");
%>
        <div class="container-fluid">
            <div class="row">
                <div class="col-8">
                    <div class="card">
                        <div class="card-header" ><h5>Dettagli Spettacolo</h5>
                        <%if((utente != null) && (utente.getRuolo() == UtenteRegistrato.ruolo.GESTORE)) { %>
                            <a href="/gestore/ModificaSpettacolo?idSpettacolo=<%=spettacolo.getIdSpettacolo()%>" ><!--<button class="btn btn-primary">Modifica Spettacolo</button>-->gdfigh</a>
                        <%} %>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-3">
                                      <img class="img-fluid" src="<%= film.getLocandina()%>" />
                                  </div>
                                <div data-table class="col">
                                    <div class="row">
                                        <h5><%=spettacolo.getTitolo()%></h5>
                                    </div>
                                    <div class="row">
                                        <div data-label class="col">Genere:</div><div class="col"><%=film.getGenere()%></div>
                                    </div>
                                    <div class="row">
                                        <div data-label class="col">Regia:</div><div class="col"><%=film.getRegia()%></div>
                                    </div>
                                    <div class="row">
                                        <div data-label class="col">Cast:</div><div class="col"><%=film.getCast()%></div>
                                    </div>
                                    <div class="row">
                                        <div data-label class="col">Durata:</div><div class="col"><%=film.getDurata()%></div>
                                    </div>
                                    <div class="row">
                                        <div data-label class="col">Visto Censura</div><div class="col"><%=film.getVistoCensura()%></div>
                                    </div>
                                    <div class="row">
                                        <div data-label class="col">Orario:</div><div class="col"><%=new SimpleDateFormat("HH:mm").format(spettacolo.getOraInizio().getTime())%></div>
                                    </div>
                                </div>
                            </div>
                        </div>         
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <h5 class="card-header">Legenda</h5>
                        <div data-table class="card-body">
                            <div class="row">
                                <div class="col-3">
                                    <img src="images/poltrona_disponibile_v2.png"/>
                                </div>
                                <div class="col">Poltrona disponibile</div>
                            </div>
                            <div class="row">
                                <div class="col-3">
                                    <img src="images/poltrona_prenotata_v2.png"/>
                                </div>
                                <div class="col">Poltrona prenotata</div>
                            </div>
                            <div class="row">
                                <div class="col-3">
                                    <img src="images/poltrona_occupata_v2.png"/>
                                </div>
                                <div class="col">Poltrona occupata</div>
                            </div>
                            <div class="row">
                                <div class="col-3">
                                    <img src="images/poltrona_non_disponibile_v2.png"/>
                                </div>
                                <div class="col">Poltrona non disponibile</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class ="col">
                    <div data-num-posti="<%= sala.getNumeroPosti() %>" data-prezzo="<%= spettacolo.getPrezzo() %>" id="seats" class="card">
                        <h5 class="card-header">Seleziona i Posti</h5>
                        <div class="card-body">
                            
<%   
    //Stampa della sala e dei posti in base al relativo stato
    String url = null, classe = null;
    int y = 0;

    offset = offset * sala.getNumeroPosti();
    for(int i = rs.getBeginRiga(); i <= endRiga; i++){
%>
               <div class="row">
<%
        for(int j = rs.getBeginColonna(); j <= endColonna; j++){
            switch(matSala[i][j]){
                case '0' :
                    url = "images/trasparente.png";
                    classe = "vds-trasparente";
                    break;
                case '2' :
                    url = "images/poltrona_non_disponibile_v2.png";
                    classe = "vds-posto-non-disponibile";
                    y++;
                    break;
                case '1' :
                    switch(matricePostiSpettacolo[offset + y]){
                        case 'd':
                            url = "images/poltrona_disponibile_v2.png";
                            classe = "vds-posto-disponibile";
                            break;
                        case 'p':
                            url = "images/poltrona_prenotata_v2.png";
                            classe = "vds-posto-prenotato";
                            break;
                        case 'o':
                            url = "images/poltrona_occupata_v2.png";
                            classe = "vds-posto-occupato";
                            break;
                        default :
                            url = "xyz";
                    }
                    y++;
                    break;
                default :
                    url = String.valueOf(matSala[i][j]);
            }
%>
            <img data-pos ="" class="vds-posto <%= classe %> cell" src="<%= url %>" />
<%
        }
 %>
                    </div>
<%
    }
%>
                        
                        </div>
                    </div>
                </div>
                <div class ="col">
                    <div id="riepilogo-ordine" class="card">
                        <h5 class="card-header">Riepilogo Ordine</h5>
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col">
                                        <form id="target" method="POST" action="FormAcquisto.jsp">
                                            <input name="idSpettacolo" type="hidden" value="<%= spettacolo.getIdSpettacolo() %>"/>
                                            <input id="p" type="hidden" name="posti" value=""/>
                                            <button class="btn btn-primary" id="clickable" disabled >Procedi con l'ordine</button>
                                        </form>
                                    </div>
                                    <div class="col">
                                        <div id="totale" class="btn btn-dark">Totale = 0.00&euro;</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
<script src="javascript/visualizzazione_dettagli_spettacolo.js"></script>
<jsp:include page="Footer.jsp" />