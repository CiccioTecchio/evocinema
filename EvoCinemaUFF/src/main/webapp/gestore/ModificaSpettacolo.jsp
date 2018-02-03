<%-- 
    Document   : ModificaSpettacolo
    Created on : 2-feb-2018, 14.25.04
    Author     : luca
--%>

<%@page import="model.Spettacolo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Film"%>
<%@page import="model.Sala"%>
<%@page import="java.util.List"%>
<% request.setAttribute("title", "Modifica Spettacolo"); %>
<jsp:include page="Header.jsp" />
<jsp:include page="PopolamentoListe"/>
<% List<Sala> sale = (List<Sala>) request.getAttribute("sale"); 
   List<Film> film = (List<Film>) request.getAttribute("film");
   Spettacolo spettacolo = (Spettacolo) request.getAttribute("spettacolo");
   SimpleDateFormat sdf = (SimpleDateFormat) request.getAttribute("sdf");
   SimpleDateFormat sdfOra = new SimpleDateFormat("HH:mm");
%>
<div class="card">
    <h6 class="card-header">Form Modifica Spettacolo</h6>
    <div class="card-body">

        <form accept-charset="utf-8" method="POST" action="ModificaSpettacolo">
          <input id="id-film" type="hidden" name="id-film" value="<%=spettacolo.getIdFilm() %>" />
          <input id="id-spettacolo" type="hidden" name="id-spettacolo" value="<%=spettacolo.getIdSpettacolo()%>" />
          <div class="form-group">
            <label for="select-film" class="col-form-label">Opera: </label>
            <select class="form-control" id="select-film" required>
                <% 
                for(Film f : film){
                    if(f.getIdFilm() == spettacolo.getIdFilm())
                        out.print("<option data-id=\"" + f.getIdFilm() +"\" value=\"" + f.getTitolo() +"\" selected >" + f.getTitolo() + " - " + sdf.format(f.getDataUscita().getTime()) + "</option>");
                    else 
                        out.print("<option data-id=\"" + f.getIdFilm() +"\" value=\"" + f.getTitolo() +"\">" + f.getTitolo() + " - " + sdf.format(f.getDataUscita().getTime()) + "</option>");
                }
                %>
            </select>
            <small id="select-film-help" class="form-text text-muted">Seleziona uno dei film presenti in libreria, o <a href="inserisciFilmLibreria.jsp">aggiungine uno prima.</a></small>

          </div>
          <div class="form-group">
            <label for="titolo-spettacolo" >Titolo dello spettacolo:</label>
            <input name="titolo"type="text" class="form-control" value="<%= spettacolo.getTitolo()%>" id="titolo-spettacolo" required>
          </div>
          <div class="form-group">
            <label for="select-sala" >Sala </label>
            <select class="form-control" name="sala" id="select-sala" required>
                <% 
                for(Sala s : sale){
                    if(s.getIdSala() == spettacolo.getIdSala())
                        out.print("<option value=\"" + s.getIdSala()+ "\" selected>" + s.getIdSala() + "</option>");
                    else
                        out.print("<option value=\"" + s.getIdSala()+ "\">" + s.getIdSala() + "</option>");
                }
                %>
            </select>
          </div>
          <div class="form-group">
            <label for="data-inizio" >Data della prima proiezione:</label>
            <input type="date" name="data-inizio" class="form-control" id="data-inizio" value="<%= sdf.format(spettacolo.getDataInizio().getTime())%>" required>
            <small id="data-inizio-help" class="form-text text-muted">Seleziona la data della prima proiezione dello spettacolo.</small>
          </div>
          <div class="form-group">
            <label for="data-fine" >Data dell'ultima proiezione:</label>
            <input type="date" name="data-fine" class="form-control" id="data-fine" value="<%= sdf.format(spettacolo.getDataFine().getTime())%>" required>
            <small id="data-fine-help" class="form-text text-muted">Seleziona la data dell'ultima proiezione dello spettacolo. Tieni presente che lo spettacolo sarà proiettato ogni giorni a partire dalla data di inizio a quella di fine.</small>
          </div>
          <div class="form-group">
            <label for="ora-inizio" >Ora Inizio:</label>
            <input type="time" name="ora-inizio" class="form-control" id="ora-inizio" value="<%= sdfOra.format(spettacolo.getOraInizio().getTime())%>" required>
            <small id="ora-inizio-help" class="form-text text-muted">Seleziona l'orario in cui è consentito l'ingresso in sala.</small>
          </div>
          <div class="form-group">
            <label for="ora-fine" >Ora Fine:</label>
            <input type="time" name="ora-fine" class="form-control" id="ora-fine" value="<%= sdfOra.format(spettacolo.getOraFine().getTime())%>" required>
            <small id="ora-fine-help" class="form-text text-muted">Seleziona l'orario in cui lo spettacolo terminerà.</small>
          </div>
          <div class="form-group">
            <label for="prezzo" >Prezzo:</label>
            <input type="number" name="prezzo" class="form-control" id="prezzo" step="any" value="<%= spettacolo.getPrezzo() %>" required>
          </div>
          <button type="submit" class="btn btn-primary mb-2">Applica le modifiche</button>
        </form>
    </div>
</div>
<script src="aggiungi_spettacolo.js"></script>

<jsp:include page="Footer.jsp" />
