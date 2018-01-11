<%-- 
    Document   : VisualizzazioneDettagliSpettacolo
    Created on : 11-gen-2018, 11.17.16
    Author     : luca
--%>

<%@page import="control.programmazioneCNT.VisualizzazioneDettagliSpettacoloCNT"%>
<%@page import="model.Spettacolo"%>
<%@page import="model.Sala"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="VisualizzazioneDettagliSpettacoloCNT" />
<jsp:include page="Header.jsp" />
<%
    Spettacolo spettacolo = (Spettacolo) request.getAttribute("spettacolo");
    Sala sala = (Sala) request.getAttribute("sala");
    
    char[] matricePostiSala = (sala.getConfigPosti()).toCharArray();
    char[] matricePostiSpettacolo = (spettacolo.getMatricePosti()).toCharArray();
%>
        <div class="container-fluid">
            <div id ="titolo" class="row">
                <div class ="col">Seleziona i Posti</div>
                <div class ="col">Riepilogo Ordine</div>
            </div> 
            <div class="row">
                <div class ="col">
                    <div id="seats" class="container">
<%
    char[][] matSala = new char[30][30];
    int z = 0;
    for(int i = 0; i < 30; i++){
        for(int j = 0; i < 30; i++){
            matSala[i][j] = matricePostiSala[z];
            z++;
        }
    }

%>
                    </div>
                </div>
                <div class ="col">RiepilogoOrdine</div>


<jsp:include page="Footer.jsp" />