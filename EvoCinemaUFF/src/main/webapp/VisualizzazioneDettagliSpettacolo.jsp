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
    int offset = 0;//(int) request.getAttribute("offset");
    RappresentazioneSala rs = new RappresentazioneSala(spettacolo, sala, offset);
    int endRiga = rs.getEndRiga();
    int endColonna = rs.getEndColonna();
    char[][] matSala = rs.getMatSala();
    char[] matricePostiSpettacolo = rs.getMatricePostiSpettacolo();
%>
        <div class="container-fluid">
            <div id ="titolo" class="row">
                <div class ="col">Seleziona i Posti</div>
                <div class ="col">Riepilogo-Ordine</div>
            </div> 
            <div class="row">
                <div class ="col">
                    <div id="seats" class="container">
<%   
    //Stampa della sala e dei posti in base al relativo stato
    String url = null, classe = null;
    int y = 0;

    offset = 0;//offset * sala.getNumeroPosti() - 1;
    for(int i = rs.getBeginRiga(); i <= endRiga; i++){
%>
               <div class="row">
<%
        for(int j = rs.getBeginColonna(); j <= endColonna; j++){
            switch(matSala[i][j]){
                case '0' :
                    url = "images/trasparente.png";
                    break;
                case '2' :
                    url = "images/poltrona_non_disponibile_v2.png";
                    classe = "vds-posto-non-disponibile";
                    y++;
                    break;
                case '1' :
                    switch(matricePostiSpettacolo[offset + y++]){
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
            <img class="<%= classe %> cell" src="<%= url %>" />
<%
        }
 %>
                    </div>
<%
    }
%>
                    </div>
                </div>
                <div class ="col">RiepilogoOrdine</div>


<jsp:include page="Footer.jsp" />