<%-- 
    Document   : ModificaSpettacolo
    Created on : 11-gen-2018, 12.20.08
    Author     : Giuseppe
--%>

<%@page import="model.*;"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script
            src="...//javascript/modificaSpettacolo.js"
        type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica dello spettacolo</title>
    </head>
    <%@ include file="Header.jsp"%>
    <body>
        <%
            Spettacolo spettacolo = (Spettacolo) request.getAttribute("spettacolo");
            Film film = (Film) request.getAttribute("film");
        %>

        <h2 id="titolo">Spettacolo da modificare</h2>
        <br> Titolo:
        <input type="text" name="titolo" id="titolo"
               value="<%=film.getTitolo()%>">
        <span id="spanTitolo"></span>

        <br> Locandina:
        <input type="text" name="locandina" id="locandina"
               value="<%=film.getLocandina()%>">
        <span id="spanLocandina"></span>

        <br> Durata:
        <input type="text" name="durata" id="durata"
               value="<%=film.getDurata()%>">
        <span id="spanDurata"></span>

        <br> Trama:
        <input type="text" name="trama" id="trama"
               value="<%=film.getTrama()%>">
        <span id="spanTrama"></span>

        <br> Genere:
        <input type="text" name="genere" id="genere"
               value="<%=film.getGenere()%>">
        <span id="spanGenere"></span>

        <br> Anno:
        <input type="text" name="anno" id="anno"
               value="<%=film.getAnno()%>">
        <span id="spanAnno"></span>

        <br> Prezzo:
        <input type="text" name="prezzo" id="prezzo"
               onchange='controlloCaratteriSpettacolo()'
               value="<%=spettacolo.getPrezzo()%>">
        <span id="spanPrezzo"></span>

        <br> Data di inizio:
        <input type="text" name="dataInizio" id="dataInizio"
               onchange='controlloCaratteriSpettacolo()'
               value="<%=spettacolo.getDataInizio()%>">
        <span id="spanDataInizio"></span>

        <br> Data di fine:
        <input type="text" name="dataFine" id="dataFine"
               onchange='controlloCaratteriSpettacolo()'
               value="<%=spettacolo.getDataFine()%>">
        <span id="spanDataFine"></span>

        <br> Sala/Orario/Tipo:
        <input type="text" name="tabella" id="tabella"
               value="<%=%>">
        <span id="spanTabella"></span>

        <input type="submit" id="bottoneSalva" name="bottoneSalva"
               value="Salva" disabled>
        <input type="submit" id="bottoneAnnulla" name="bottoneAnnulla"
               value="Annulla" disabled>

    </body>
    <%@ include file="Footer.jsp"%>
</html>
