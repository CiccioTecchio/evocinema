<%-- 
    Document   : VisualizzazioneDettagliSpettacolo
    Created on : 11-gen-2018, 11.17.16
    Author     : luca
--%>

<%@page import="control.programmazioneCNT.RappresentazioneSala"%>
<%@page import="control.programmazioneCNT.VisualizzazioneDettagliSpettacoloCNT"%>
<%@page import="model.Spettacolo"%>
<%@page import="model.Sala"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="VisualizzazioneDettagliSpettacoloCNT" />
<jsp:include page="Header.jsp" />

<%  
    Spettacolo spettacolo = (Spettacolo) request.getAttribute("spettacolo");
    Sala sala = (Sala) request.getAttribute("sala");
    int offset = (int) request.getAttribute("offset");
    RappresentazioneSala rs = new RappresentazioneSala(spettacolo, sala, offset);
    int endRiga = rs.getEndRiga();
    int endColonna = rs.getEndColonna();
    char[][] matSala = rs.getMatSala();
    char[] matricePostiSpettacolo = rs.getMatricePostiSpettacolo();
%>
        <div class="container-fluid">
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
                    break;
                default :
                    url = String.valueOf(matSala[i][j]);
            }
%>
            <img data-pos ="" class="vds-posto <%= classe %> cell" src="<%= url %>" />
<%          y++;
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
                                            <button class="btn btn-primary" >Procedi con l'ordine</button>
                                        </form>
                                    </div>
                                    <div class="col">
                                        <form>
                                            <p id="totale" class="btn btn-dark">Totale = 0.00&euro;</p>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
    
                </div>
            </div>
<script src="javascript/visualizzazione_dettagli_spettacolo.js"></script>
<jsp:include page="Footer.jsp" />