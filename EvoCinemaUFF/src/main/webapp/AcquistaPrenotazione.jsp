<%-- 
    Document   : AcquistaPrenotazione
    Created on : 19-gen-2018, 12.12.42
    Author     : giuseppeapuzzo
--%>

<%@page import="database.SpettacoloDAO"%>
<%@page import="database.OperazioneDAO"%>
<%@page import="model.Operazione"%>
<%@page import="model.Film"%>
<%@page import="java.util.Collection"%>
<%@page import="database.FilmDAO"%>
<%@page import="model.Spettacolo"%>
<%@page import="model.Prenotazione"%>
<%@page import="model.Operazione.acquistato"%>
<%@page import="model.Operazione.prenotato"%>
<%@page import="model.Sala"%>
<%@page import="model.Sconto"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Header.jsp"/>
<% request.setAttribute("title", "Acquista prenotazione"); %>
<!DOCTYPE html>


<%
    int IdPrenotazione = Integer.parseInt(request.getParameter("IdPrenotazione"));
    OperazioneDAO opDAO = new OperazioneDAO();
    SpettacoloDAO spettDAO = new SpettacoloDAO();
    Operazione x = opDAO.foundByID(IdPrenotazione);
    Spettacolo spett = spettDAO.foundByID(x.getIdSpettacolo());
%>

<form action="/AcquistoConPrenotazioneCNT" method="POST">
    <fieldset class="fieldsetCustom">

        <legend class="legendCustom">Acquista biglietto da prenotazione </legend>

        <div class="ml-4">
            <label>Spettacolo prenotato:</label>
            <label class="float-right" id="idSpet" name="titolo"><%= spett.getTitolo() %> </label>
        </div>
        <br><br>
                    <%
                        Calendar cal = spett.getDataInizio();
                        String data = cal.get(Calendar.YEAR) + "-";
                        int mese = cal.get(Calendar.MONTH);
                        if ((mese >= 0) && (mese < 9)) {
                            data = data + "0" + (mese + 1);
                        } else {
                            data = data + (mese + 1);
                        }
                        data = data + "-" + cal.get(Calendar.DAY_OF_MONTH);
                    %>
        <div class="ml-4">
            <label>Data programmazione:</label>
            <label class="float-right" id="idData" name="data"><%= data %></label>
        </div>
        <br><br>
                    <%
                        cal = spett.getOraInizio();
                        String orario = "";
                        int ora = cal.get(Calendar.HOUR_OF_DAY);
                        if ((ora <= 0) && (ora >= 9)) {
                            orario = "0" + ora;
                        } else {
                            orario += ora;
                        }
                        if (cal.get(Calendar.MINUTE) == 0) {
                            orario += ":00";
                        } else {
                            orario += ":" + cal.get(Calendar.MINUTE);
                        }
                    %>
        <div class="ml-4">
            <label>Orario prenotazione:</label>
            <label class="float-right" id="idPrenotazione" name="orario"><%= orario %></label>
        </div>
        <br><br>
        
        <div class="ml-4">
            <label>N° biglietti prenotati:</label>
            <label class="float-right" id="idNumber" name="numero"></label>
        </div>
        <br><br>
                    <%
                        int riga = x.getPostoRiga();
                        int colonna = x.getPostoColonna();
                        String posto = "Riga: "+riga+" Colonna: "+colonna;
                    %>
        <div class="ml-4">
            <label>Dettagli posto:</label>
            <label class="float-right" id="idDettagli" name="dettagli"><%= posto %></label>
        </div>
        <br><br>
        
        <div class="ml-4">
            <label>Importo da pagare</label>
            <label class="float-right" id="idImporto" name="importo"><%= x.getPrezzoFinale() %></label>
        </div>
        <br><br>

        </br></br>
        <input type="submit" name="Indietro" value="Indietro" />
        <div class="float-right">
            <input class="mr-3" type="submit" name="Conferma" value="Paga" />  
            <input type="submit" name="Annulla" value="Annulla" />
        </div>

    </fieldset>
</form>
<jsp:include page="Footer.jsp" />
