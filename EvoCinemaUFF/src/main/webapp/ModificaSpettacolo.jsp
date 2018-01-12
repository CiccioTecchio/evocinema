<%-- 
    Document   : ModificaSpettacolo
    Created on : 11-gen-2018, 12.20.08
    Author     : Giuseppe
--%>

<%@page import="java.util.Collection"%>
<%@page import="model.*;"%>
<%@page import="database.*;"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script
            src="...//javascript/modificaSpettacolo.js"
        type="text/javascript"></script>
        <script
            src="...//javascript/invioModificaSpettacolo.js"
        type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica dello spettacolo</title>
    </head>
    <%@ include file="Header.jsp"%>
    <body>
        <%
            Spettacolo spettacolo = (Spettacolo) request.getAttribute("spettacolo");
            Film film = (Film) request.getAttribute("film");
            SalaDAO salaDao = new SalaDAO();
            Collection<Sala> sale = salaDao.getAllSale();
        %>

        <h2 id="titolo">Spettacolo da modificare</h2>
        <br> Titolo:
        <input type="text" name="titolo" id="titolo"
               value="<%=film.getTitolo()%>">
        <span id="spanTitolo"></span>

        <br> Prezzo:
        <input type="text" name="prezzo" id="prezzo"
               onchange='controlloCaratteriSpettacolo()'
               value="<%=spettacolo.getPrezzo()%>">
        <span id="spanPrezzo"></span>

        <br> Orario:
        <input type="time" name="orario" id="orario"
               value="<%=spettacolo.getOraInizio()%>">
        <span id="spanOrario"></span>

        <br> Sala:
        <select
            name="sala" id="sala">
            <%
                for (Sala x : sale) {
            %>
            <option value="<%=x.getIdSala()%>"></option>
            <%
                }
            %>
        </select>
        <span id="spanSala"></span>

        <br> Data di inizio:
        <input type="date" name="dataInizio" id="dataInizio"
               value="<%=spettacolo.getDataInizio()%>">
        <span id="spanDataInizio"></span>

        <br> Data di fine:
        <input type="date" name="dataFine" id="dataFine"
               value="<%=spettacolo.getDataFine()%>">
        <span id="spanDataFine"></span>


        <input type="submit" id="bottoneSalva" name="bottoneSalva"
               value="Salva" disabled>
        <input type="submit" id="bottoneAnnulla" name="bottoneAnnulla"
               value="Annulla" disabled>

    </body>
    <%@ include file="Footer.jsp"%>
</html>
